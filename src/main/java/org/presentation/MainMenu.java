package org.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.application.Credits;

import java.io.IOException;
import java.io.Serializable;

public class MainMenu implements Serializable {
    public Button loginButton;
    public Button closeButton;
    public Button helpButton;
    public Button searchButton;
    public Button addButton;
    public Button deleteButton;
    public Button updateButton;
    public TextField searchField;
    public TextField setTitleBox;
    public TextField setOccupationBox;
    public TextField setPersonBox;
    public TableView creditTable;
    public TableColumn title;
    public TableColumn occupation;
    public TableColumn person;
    public ListView test;

    final ObservableList<Credits> credits = FXCollections.observableArrayList();

    public void initialize() {
        title.setCellValueFactory(new PropertyValueFactory<Credits, String>("title"));
        occupation.setCellValueFactory(new PropertyValueFactory<Credits, String>("occupation"));
        person.setCellValueFactory(new PropertyValueFactory<Credits, String>("person"));
    }

    public void addButton(ActionEvent event) {
        if (setTitleBox.getText().trim().isEmpty() || setOccupationBox.getText().trim().isEmpty() || setPersonBox.getText().trim().isEmpty()) {
            System.out.println("1 or more textBoxes are empty!");
            return;
        } else {
            String title = setTitleBox.getText();
            String occupation = setOccupationBox.getText();
            String person = setPersonBox.getText();
            if (!Credits.checkTitleExist(title)) {
                credits.add(new Credits(title, occupation, person));
                creditTable.setItems(credits);
                clearText();
            } else {
                System.out.println("Credit already exists");
                return;
            }
        }
    }

    public void deleteButton(ActionEvent event) {
        Credits.removeTitle(getCurrentlySelectedTitle());
        credits.remove(creditTable.getSelectionModel().getSelectedItem());
        System.out.println("Credit succesfully deleted");
    }

    public void updateButton(ActionEvent event) {
        if (setTitleBox.getText().trim().isEmpty() || setOccupationBox.getText().trim().isEmpty() || setPersonBox.getText().trim().isEmpty()) {
            System.out.println("1 or more textBoxes are empty!");
            return;
        } else {
            Credits.removeTitle(getCurrentlySelectedTitle());
            String updatedTitle = setTitleBox.getText();
            String updatedOccupation = setOccupationBox.getText();
            String updatedPerson = setPersonBox.getText();
            Credits.addTitle(updatedTitle);
            credits.get(creditTable.getSelectionModel().getSelectedIndex()).setTitle(updatedTitle);
            credits.get(creditTable.getSelectionModel().getSelectedIndex()).setOccupation(updatedOccupation);
            credits.get(creditTable.getSelectionModel().getSelectedIndex()).setPerson(updatedPerson);
            System.out.println("Credit succesfully updated");
            creditTable.refresh();
        }
    }

    public void searchField(KeyEvent event) {
        FilteredList<Credits> filteredList = new FilteredList<>(credits, b -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(credits -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String Filter = newValue.toLowerCase();

                if (credits.getTitle().toLowerCase().indexOf(Filter) != -1) {
                    return true;
                } else if (credits.getOccupation().toLowerCase().indexOf(Filter) != -1) {
                    return true;
                } else if (credits.getPerson().toLowerCase().indexOf(Filter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Credits> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(creditTable.comparatorProperty());
        creditTable.setItems(sortedList);
    }

    public String getCurrentlySelectedTitle() {
        int index = creditTable.getSelectionModel().getSelectedIndex();
        Credits credit = (Credits)creditTable.getItems().get(index);
        return credit.getTitle();
    }

    public void clearSearchText() {
        searchField.setText("");
    }

    public void clearText() {
        setTitleBox.setText("");
        setPersonBox.setText("");
        setOccupationBox.setText("");
    }

    public void switchToLoginScreen(ActionEvent actionEvent) throws IOException {
        App.setRoot("loginScreen");
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void switchToHelpPopup(ActionEvent actionEvent) {
        App.HelpPopUp.display();
    }

}
