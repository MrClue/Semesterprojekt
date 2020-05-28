package org.presentation;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.application.Controller;
import org.application.Credits;
import org.application.Program;

import java.io.IOException;

public class User_mainMenu {
    public Button closeButton, loginButton, helpButton;
    public TextField searchProductions, searchCredits;

    // production table
    public TableView<Program> programTable;
    public TableColumn<Program, String> programTitle;

    // credits table
    public TableView<Credits> creditTable;
    public TableColumn<Credits, String> occupation, person;

    // get Controller instance
    private Controller controller = Controller.getInstance();

    public void initialize() {
        programTitle.setCellValueFactory(new PropertyValueFactory<Program, String>("title"));
        occupation.setCellValueFactory(new PropertyValueFactory<Credits, String>("occupation"));
        person.setCellValueFactory(new PropertyValueFactory<Credits, String>("person"));

        // retrieving production data from database
        controller.refreshProgramData(programTable);
    }

    public void programSearchField(KeyEvent event) {
        controller.programSearchField(programTable, searchProductions);
    }

    public void creditSearchField(KeyEvent keyEvent) {
        controller.creditSearchField(programTable, creditTable, searchCredits);
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        controller.closeButtonAction(closeButton);
    }

    public void switchToLoginScreen(ActionEvent actionEvent) throws IOException {
        App.setRoot("loginScreen");
    }

    public void switchToHelpPopup(ActionEvent actionEvent) {
        App.helpPopUp.display();
    }

    public void selectedProductionEvent(MouseEvent mouseEvent) {
        controller.refreshCreditData(programTable, creditTable);
    }
}
