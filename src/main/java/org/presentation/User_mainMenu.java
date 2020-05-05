package org.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.application.Credits;

import java.io.IOException;

public class User_mainMenu {
    public Button closeButton, loginButton, helpButton;
    public TextField searchProductions, searchCredits;
    public TableView<Credits> creditTable;
    public TableColumn<Credits, String> title, occupation, person;
    public ListView test;

    final ObservableList<Credits> credits = FXCollections.observableArrayList();

    public void searchFieldProduction(KeyEvent event) {
//        FilteredList<Credits> filteredList = new FilteredList<>(credits, b -> true);
//        searchProductions.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredList.setPredicate(credits -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                String Filter = newValue.toLowerCase();
//
//                if (credits.getTitle().toLowerCase().indexOf(Filter) != -1) {
//                    return true;
//                }
//                /** føler at det er useless at lave en generel søgning efter occupation */
//                /*else if (credits.getOccupation().toLowerCase().indexOf(Filter) != -1) {
//                    return true;
//                }*/ else if (credits.getPerson().toLowerCase().indexOf(Filter) != -1) {
//                    return true;
//                } else {
//                    return false;
//                }
//            });
//        });
//        SortedList<Credits> sortedList = new SortedList<>(filteredList);
//        sortedList.comparatorProperty().bind(creditTable.comparatorProperty());
//        creditTable.setItems(sortedList);
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void switchToLoginScreen(ActionEvent actionEvent) throws IOException {
        App.setRoot("loginScreen");
    }

    public void switchToHelpPopup(ActionEvent actionEvent) {
        App.HelpPopUp.display();
    }

    public void searchField(KeyEvent keyEvent) {
        try {
            throw new UnsupportedOperationException("Not implemented");
        } catch (Exception ex) {
            System.out.println("Action not implemented.");
        }
    }
}
