package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


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
    private TableColumn finalMark;
    @FXML
    private TableColumn letterGrade;

    private TableView<StudentRecord> students;

    @FXML
    public void initialize(){
        studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        midterm.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        assignments.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        finalMark.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        letterGrade.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        finalExam.setCellValueFactory(new PropertyValueFactory<>("finalExam"));

        tableView.setItems(DataSource.getAllMarks());
    }

}
