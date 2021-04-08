package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Controller {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField messageField;

    @FXML
    public void sendMessage() throws IOException {
        String message = usernameField.getText() + ": " + messageField.getText();
        var socket = new Socket("localhost", 25505);
        var out = new PrintWriter(socket.getOutputStream(), true);
        out.println(message);
        out.flush();
    }
    @FXML
    public void exit(){
        System.exit(0);
    }
    @FXML
    public void initialize() throws IOException {

    }


}
