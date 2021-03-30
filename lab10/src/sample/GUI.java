package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {
    public GUI() throws Exception {
        String[] args = new String[] {""};
        main(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent nRoot = FXMLLoader.load(getClass().getResource("serverGUI.fxml"));
        primaryStage.setTitle("server");
        primaryStage.setScene(new Scene(nRoot,400,600));
        primaryStage.show();
    }
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}

