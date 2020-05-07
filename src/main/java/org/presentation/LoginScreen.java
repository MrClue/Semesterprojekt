package org.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
                System.out.println("Producer specific menu not created.");
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
}
