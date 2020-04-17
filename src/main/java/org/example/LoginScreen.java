package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

/**
 * Video brugt til FXML inspiration:
 * https://www.youtube.com/watch?v=wIuqKYhGaa8
 * */

public class LoginScreen {
    public Label returnButton;
    public Button loginButton;

    @FXML
    public void loginBtnAction(ActionEvent actionEvent) throws IOException {
        App.setRoot("mainMenu");
    }

    @FXML
    public void returnMainMenu(MouseEvent actionEvent) throws IOException {
        App.setRoot("mainMenu");
    }
}
