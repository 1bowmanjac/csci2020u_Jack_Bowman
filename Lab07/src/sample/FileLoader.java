package sample;
import java.io.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class FileLoader {
    private String filename;
    private Map<String, Integer> wordCounts = new TreeMap<>();
    private int totalWords;


    public FileLoader(String filename){
        this.filename = filename;
    }
    public void loadFile(){
        String line = "";

        try{
            BufferedReader br = new BufferedReader(new FileReader(this.filename));
            while ((line = br.readLine())!=null){
                String[] columns = line.split(",");
//                System.out.println(line);
                if(wordCounts.containsKey(columns[5])){
                    int oldValue = wordCounts.get(columns[5]);
                    wordCounts.put(columns[5],oldValue + 1);
                } else {
                    wordCounts.put(columns[5],1);
                    }

                totalWords++;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<String, Integer> getWordCounts(){
        return wordCounts;
    }

    public Map<String, Double> getPercentageMap(){
        loadFile();
        Map<String, Double> percentageMap = new TreeMap<>();
        for(Map.Entry<String,Integer> entry : wordCounts.entrySet()) {
            String word = entry.getKey();
            Double value = new Double(entry.getValue());
            percentageMap.put(word, value/totalWords);
        }
        return  percentageMap;
    }
}
