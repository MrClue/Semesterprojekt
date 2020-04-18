package org.example;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {
    public Button loginButton;
    public Button closeButton;

    public void switchToLoginScreen(ActionEvent actionEvent) throws IOException {
        App.setRoot("loginScreen");
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
