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

import org.application.Controller;
import org.application.LoginSystem;
import org.data.DatabaseHandler;


/**
 * Video brugt til FXML inspiration:
 * https://www.youtube.com/watch?v=wIuqKYhGaa8
 */

public class LoginScreen {
    @FXML
    public Label helpButton, noLoginButton;
    @FXML
    public Button closeButton, loginButton;
    @FXML
    public RadioButton rememberMeButton;
    @FXML
    private TextField usernameTextArea, passwordTextArea;

    private Controller controller = Controller.getInstance();

    public void initialize() {
        controller.autoFillLogin(usernameTextArea, passwordTextArea, rememberMeButton);
    }

    @FXML
    public void loginBtnAction(ActionEvent actionEvent) throws IOException {
        LoginSystem ls = new LoginSystem();
        String username = usernameTextArea.getText();
        String password = passwordTextArea.getText();
        if (ls.login(username, password)) {
            if (ls.authorityLevel(username) > 1) {
                //implement the code if the user is systemadministrator
                App.setRoot("mainMenu");
            } else {
                //implement the code if the user is producer
                App.setRoot("mainMenu");
            }
        }

    }

    @FXML
    public void returnUserMainMenu(MouseEvent actionEvent) throws IOException {
        App.setRoot("user_mainMenu");
    }

    public void closeButtonAction(ActionEvent actionEvent){
        controller.closeButtonAction(closeButton);
    }

    public void switchToHelpPopup(MouseEvent actionEvent) {
        App.HelpPopUp.display();
    }

    public void rememberLogin(ActionEvent event) {
        controller.rememberLogin(usernameTextArea, passwordTextArea);
    }
}
