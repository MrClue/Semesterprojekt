package org.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.application.Role;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.application.LoginSystem;

/**
 * Video brugt til FXML inspiration:
 * https://www.youtube.com/watch?v=wIuqKYhGaa8
 * */

public class LoginScreen {
    public Label returnButton;
    public Button loginButton;
    public Button closeButton;

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
            App.setRoot("mainMenu");
        }

    }

    @FXML
    public void returnMainMenu(MouseEvent actionEvent) throws IOException {
        App.setRoot("mainMenu");
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


}
