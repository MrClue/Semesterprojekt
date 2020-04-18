package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Video brugt til FXML inspiration:
 * https://www.youtube.com/watch?v=wIuqKYhGaa8
 * */

public class LoginScreen {
    public Label returnButton;
    public Button loginButton;
    public Button closeButton;

    @FXML
    public void loginBtnAction(ActionEvent actionEvent) throws IOException {
        App.setRoot("mainMenu");
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
