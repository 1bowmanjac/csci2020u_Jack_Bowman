package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Controller {
    public static File mainDirectory;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn filename;
    @FXML
    private TableColumn actualClass;
    @FXML
    private TableColumn spamProbability;
    @FXML
    private Label accuracyLabel;
    @FXML
    private Label precisionLabel;
    @FXML
    public void initialize() throws IOException {
        tableView.prefHeight(400);
        tableView.prefWidth(400);
        filename.setCellValueFactory(new PropertyValueFactory<>("filename"));
        actualClass.setCellValueFactory(new PropertyValueFactory<>("actualClass"));
        spamProbability.setCellValueFactory(new PropertyValueFactory<>("spamProbability"));

        String path = mainDirectory.getAbsolutePath();
        System.out.println("path = " + path);

        File ham = new File(path +  "/train/ham"); // make a file to the train folder
        File ham2 = new File(path + "/train/ham2"); // make a file to the train folder
        File spam = new File(path + "/train/spam"); // make a file to the train folder
        WordCounter hamCounter = new WordCounter(); // make an instance of Wordcounter
        WordCounter ham2Counter = new WordCounter(); // make an instance of Wordcounter
        WordCounter spamCounter = new WordCounter(); // make an instance of Wordcounter
        Map<String, Integer> trainHamFreq;
        Map<String, Integer> trainHam2Freq;
        Map<String, Integer> trainSpamFreq;

        //parse ham & spam i am and store the wordmaps
        hamCounter.parseFile(ham); // 2501 files
        trainHamFreq = hamCounter.getMap();
        ham2Counter.parseFile(ham2); // 2501 files
        trainHam2Freq = ham2Counter.getMap();

        //combine ham and ham 2
        for(Map.Entry<String,Integer> entry : trainHam2Freq.entrySet()) {
            String word2 = entry.getKey();
            Integer value2 = entry.getValue();
            if(trainHamFreq.containsKey(word2)){
                Integer newValue = trainHamFreq.get(word2);
                newValue += value2;
                trainHamFreq.put(word2,newValue);
            }
        }


        spamCounter.parseFile(spam);// 501 files
        trainSpamFreq = spamCounter.getMap();
//        System.out.println(spamMap);
//        System.out.println(trainSpamFreq);
//        System.out.println(trainHamFreq);

        System.out.println("totalFiles = " + (hamCounter.getNumFiles() + ham2Counter.getNumFiles()));

        Map<String, Double> hamProbability = probabilityMap(trainHamFreq,hamCounter.getNumFiles() + ham2Counter.getNumFiles());
        Map<String, Double> spamProbability = probabilityMap(trainSpamFreq, spamCounter.getNumFiles());

        Map<String, Double>  finalSpam = aGivenBMap(hamProbability,spamProbability);

        File testHamFolder = new File(path + "/test/ham");
        ArrayList<TestFile> testFiles =  loopFolder(testHamFolder,finalSpam);
        File testSpamFolder = new File(path + "/test/spam");
        ArrayList<TestFile> testFiles1=  loopFolder(testSpamFolder,finalSpam);

        testFiles.addAll(testFiles1);

        //calculate and print the accuracy and precision
        double numTrue = 0;
        double numFalsePos = 0;
        double numTruePos = 0;
        for (TestFile entry :
                testFiles) {
            String actualClass = entry.getActualClass();
            double prob = entry.getRawProb();
            if(actualClass.equals("ham") && prob < 0.5){
                numTrue++;
            } if (actualClass.equals("spam") && prob > 0.5){
                numTrue++;
                numTruePos++;
            } if(actualClass.equals("ham") && prob > 0.5){
                numFalsePos++;
            }
        }
        double accuracy = numTrue / testFiles.size();
        System.out.print("accuracy = ");
        System.out.println(accuracy);
        double precision = numTruePos/(numFalsePos + numTruePos);
        System.out.print("precision = ");
        System.out.println(precision);

        accuracyLabel.setText("accuracy = " + accuracy);
        precisionLabel.setText("precision = " + precision);

        ObservableList<TestFile> oTestFiles = FXCollections.observableArrayList(testFiles);
        tableView.setItems(oTestFiles);


    }
    /**
     * Converts a map of word occurrence to one of probability
     * @param wordMap map of words and their occurrence
     * @param totalFiles the total number of files from which the map was pulled
     * @return a map of the probability of words in wordMap
     */
    public static Map<String, Double> probabilityMap(Map<String, Integer> wordMap, int totalFiles){
        Map<String, Double> probabilityMap = new TreeMap<>();
        for(Map.Entry<String,Integer> entry : wordMap.entrySet()) {
            String word = entry.getKey();
            Double value = new Double(entry.getValue());
            probabilityMap.put(word, value/totalFiles);
        }
        return probabilityMap;
    }
    public static  Map<String, Double> aGivenBMap(Map<String, Double> hamProb, Map<String, Double> spamProb){
        Map<String, Double>  aGivinB = new TreeMap<>();

        Double hamValue;
        String spamWord;
        Double spamValue;

        for(Map.Entry<String,Double> entry : spamProb.entrySet()) {
            spamWord = entry.getKey();
            spamValue = entry.getValue();
            if(hamProb.containsKey(spamWord)){
                hamValue = hamProb.get(spamWord);
            } else {
                hamValue = 0.0;
            }
            if(!aGivinB.containsKey(spamWord)){
                aGivinB.put(spamWord, (spamValue)/(spamValue+hamValue));
            }

        }
        return  aGivinB;
    }

    /**
     * makes a testFile from a file
     * @param file the file
     * @param map probability map
     * @param actualClass is is actually spam or not
     * @return a testFile
     * @throws IOException
     */
    public TestFile test(File file,Map<String, Double> map,String actualClass) throws IOException {

        WordCounter counter = new WordCounter(); // make an instance of Wordcounter
        Scanner scanner = new Scanner(file);

        ArrayList<String> wordsInFile = new ArrayList<String>();
        double n = 0;
        // scanning token by token
        while (scanner.hasNext()){
            String  token = scanner.next();
            if (counter.isValidWord(token)){
                // ignore case
                token = token.toLowerCase();
                //check if word has been counted, if not: skip word
                if(map.containsKey(token)){
                    n += Math.log(1-map.get(token)-Math.log(map.get(token)));
                }
            }
        }
        double probSF = 1/(1+Math.pow(Math.E,n));
        Path path = Paths.get(file.getAbsolutePath());
        String fileName = path.getFileName().toString();
        TestFile testFile = new TestFile(fileName,probSF,actualClass);
        return testFile;
    }

    /**
     * loops through a folder of emails and runs test() on each item
     * and adds the testFile to an ArrayList
     * @param folder the File of the folder to loop through
     * @param map the probability map of a file being spam given in contains a certain word
     * @return an ArrayList of TestFiles
     * @throws IOException
     */
    public ArrayList<TestFile> loopFolder(File folder, Map<String, Double> map) throws IOException {
        ArrayList<TestFile> testFileList = new ArrayList<>();
        String actualClass = folder.getName();
        if(folder.isDirectory()){
            //parse each file inside the directory
            File[] content = folder.listFiles();
//            System.out.println(content.length);
            for(File current: content){
                testFileList.add(test(current,map,actualClass));
            }
        } else {
            return null;
        }
        return testFileList;
    }
}
