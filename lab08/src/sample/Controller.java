package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    @FXML
    public void initialize(){

        //menu button handlers
        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked newBtn");
            }
        });
        openBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked openBtn");
            }
        });
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked saveBtn");
            }
        });
        saveAsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked saveAsBtn");
            }
        });
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked exitBtn");
            }
        });


    }
}
