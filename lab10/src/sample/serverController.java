package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class serverController {
    @FXML
    private TextArea textArea;

    @FXML
    public void setServerTextField(String message){
        String oldText = textArea.getText();
        textArea.setText( oldText + "\n" + message);
    }
    @FXML
    public void initialize() throws IOException {
        ThreadPoolExecutor pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
        pool.execute(new NetCode());
    }
    @FXML
    public void exit(){
        System.exit(0);
    }
    class NetCode implements Runnable{
        NetCode(){
        }
        @Override
        public void run() {
            ThreadPoolExecutor pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(20);
            try (var listener = new ServerSocket(25505)) {
                System.out.println("The server is running...");
                while (true) {
                    pool.execute(new instance(listener.accept()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    class instance implements Runnable {
        private Socket socket;

        instance(Socket socket) {
            System.out.println("new thread created");
            this.socket = socket;
        }
        @Override
        public void run() {
            System.out.println("Connected: " + socket);
            try {


                var in = new Scanner(socket.getInputStream());
                var out = new PrintWriter(socket.getOutputStream(), true);
                String message = in.nextLine();
                setServerTextField(message);

            } catch (Exception e) {
                System.out.println(e);

            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println("Closed: " + socket);
            }
        }
    }

}
