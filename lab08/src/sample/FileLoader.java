package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

public class FileLoader {
    private String filename;
    private ObservableList<StudentRecord> data = FXCollections.observableArrayList();


    public FileLoader(String filename){
        this.filename = filename;
    }
    public void loadFile(){
        String line = "";

        try{
            BufferedReader br = new BufferedReader(new FileReader(this.filename));
            while ((line = br.readLine())!=null){
                String[] columns = line.split(",");
                StudentRecord tempStudent = new StudentRecord(
                        columns[0],
                        Float.parseFloat(columns[1]),
                        Float.parseFloat(columns[2]),
                        Float.parseFloat(columns[3]));
                data.add(tempStudent);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<StudentRecord> getData(){
        return data;
    }
}
