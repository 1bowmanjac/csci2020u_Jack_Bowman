package sample;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static javafx.application.Application.launch;

public class Server extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent nRoot = FXMLLoader.load(getClass().getResource("serverGUI.fxml"));
        primaryStage.setTitle("server");
        Scene mainScene = new Scene(nRoot, 600, 600);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}



