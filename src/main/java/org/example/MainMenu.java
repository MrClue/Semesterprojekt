package org.example;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenu {
    public Button loginButton;

    public void switchToLoginScreen(ActionEvent actionEvent) throws IOException {
        App.setRoot("loginScreen");
    }
}
