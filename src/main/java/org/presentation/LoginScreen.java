package org.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;
import org.application.LoginSystem;


/**
 * Video brugt til FXML inspiration:
 * https://www.youtube.com/watch?v=wIuqKYhGaa8
 * */

public class LoginScreen {
    public Label noLoginButton;
    public Button loginButton;
    public Button closeButton;
    public Label helpButton;
    public RadioButton rememberMeButton;
    @FXML
    private TextField usernameTextArea;
    @FXML
    private TextField passwordTextArea;

    private final File file = new File("userLogin.txt");

    public void initialize() {
        autoFillLogin();
    }

    @FXML
    public void loginBtnAction(ActionEvent actionEvent) throws IOException {
        LoginSystem ls = new LoginSystem();
        String username = usernameTextArea.getText();
        String password = passwordTextArea.getText();
        if (ls.login(username, password)){
            if (ls.authorityLevel(username) > 1){
                //implement the code if the user is systemadministrator
                App.setRoot("mainMenu");
            }
            else {
                //implement the code if the user is producer
                App.setRoot("mainMenu");
            }
        }

    }

    @FXML
    public void returnUserMainMenu(MouseEvent actionEvent) throws IOException {
        App.setRoot("user_mainMenu");
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void switchToHelpPopup(MouseEvent actionEvent) {
        App.HelpPopUp.display();
    }

    public void rememberLogin(ActionEvent event) {
        try {
            if(!file.exists()) {
                file.createNewFile();  //if the file !exist create a new one
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            bw.write(usernameTextArea.getText()); //write the name
            bw.newLine(); //leave a new Line
            bw.write(passwordTextArea.getText()); //write the password
            bw.close(); //close the BufferedWriter

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void autoFillLogin() {
        try {
            if(file.exists()) {
                Scanner scan = new Scanner(file);   //Use Scanner to read the File

                usernameTextArea.setText(scan.nextLine());  //append the text to name field
                passwordTextArea.setText(scan.nextLine()); //append the text to password field
                scan.close();

                rememberMeButton.selectedProperty().set(true); // the remember me button is selected if file exists
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
