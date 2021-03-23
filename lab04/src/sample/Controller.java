package sample;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Controller {
    @FXML
    private TextField nameField;
    @FXML
    private TextField password;
    @FXML
    private TextField fullName;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;
    @FXML
    private Button enterBtn;

    private DateTimeFormatter dateTimeFormatter;
    @FXML
    private DatePicker datePicker;

    @FXML
    public void initialize(){
        final String datePattern = "M/dd/yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
        datePicker.setConverter(new StringConverter<LocalDate>(){
            @Override
            public String toString(LocalDate date){
                String finalDate = null;
                if (date != null){
                    finalDate = dateTimeFormatter.format(date);
                }
                return finalDate;
            }

            @Override
            public LocalDate fromString(String string) {
                LocalDate date = null;
                if(string != null){
                    date = LocalDate.parse(string, dateTimeFormatter);
                }
                return date;
            }
        });
    }

    public void btnOnPress(ActionEvent event){
        if(nameField.getText().length() > 0){
            System.out.println(nameField.getText());
            System.out.println(password.getText());
            System.out.println(fullName.getText());
            System.out.println(email.getText());
            System.out.println(phoneNumber.getText());

            System.out.println(datePicker.getValue());
        }
    }

    public void onKeyPress(javafx.scene.input.KeyEvent keyEvent) {
        System.out.println("user pressed" + keyEvent);
    }
}
