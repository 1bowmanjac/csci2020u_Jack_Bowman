package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn studentID;
    @FXML
    private TableColumn midterm;
    @FXML
    private TableColumn assignments;
    @FXML
    private TableColumn finalExam;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem newBtn;
    @FXML
    private MenuItem openBtn;
    @FXML
    private MenuItem saveBtn;
    @FXML
    private MenuItem saveAsBtn;
    @FXML
    private MenuItem exitBtn;

    File currentFile = new File("unnamed.csv");

    @FXML
    public void initialize(){

        //setCellValueFactory(don't know exactly what that does)
        studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        midterm.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        assignments.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        finalExam.setCellValueFactory(new PropertyValueFactory<>("finalExam"));

        //menu button handlers
        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked newBtn");
                ObservableList<StudentRecord> empty = FXCollections.observableArrayList();
                tableView.setItems(empty);
            }
        });
        openBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked openBtn");
                load();
            }
        });
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked saveBtn");
                save();
            }
        });
        saveAsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File("."));
                Stage dialogStage = new Stage();
                currentFile = fileChooser.showOpenDialog(dialogStage);
                save();
                System.out.println("clicked saveAsBtn");
            }
        });
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked exitBtn");
                Platform.exit();
            }
        });


    }
    public void save(){
        StudentRecord record;
        List <List<String>> arrayList = new ArrayList<>();
        for (int i = 0; i < tableView.getItems().size(); i++){
            record = (StudentRecord) tableView.getItems().get(i);
            arrayList.add(new ArrayList<>());
            arrayList.get(i).add(record.getStudentID());
            arrayList.get(i).add(String.valueOf(record.getAssignments()));
            arrayList.get(i).add(String.valueOf(record.getMidterm()));
            arrayList.get(i).add(String.valueOf(record.getFinalExam()));
        }
        String string = "";
        for (int i = 0; i < arrayList.size(); i++){
            for (int j = 0; j < arrayList.get(i).size(); j++){
                string += arrayList.get(i).get(j);
                string +=',';
            }
            string +='\n';

        }
        try (PrintWriter writer = new PrintWriter(currentFile)){
            writer.append(string);
        }
        catch (FileNotFoundException err){
            System.out.println(err.getMessage());
        }
    }
    public void load(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        Stage dialogStage = new Stage();
        File file = fileChooser.showOpenDialog(dialogStage);
        System.out.println(file.getName());
        FileLoader fl = new FileLoader(file.getAbsolutePath());
        fl.loadFile();
        tableView.setItems(fl.getData());
    }

}
