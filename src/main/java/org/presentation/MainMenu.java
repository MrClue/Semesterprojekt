package org.presentation;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.application.Controller;
import org.application.Credits;
import org.application.Program;
import org.data.DatabaseHandler;

import java.io.IOException;
import java.io.Serializable;

/**
 * ToDO List:
 * Make it possible to type Production title and Credit information at the same time.
 * Make a search bar for the Credit table.
 * Fix errors and optimize logic.
 */

public class MainMenu implements Serializable {
    public Button signOut, closeButton, helpButton, addButton, deleteButton, updateButton;
    public TextField searchProductions, titleField, occupationField, personField;

    // production table
    public TableView<Program> productionTable;
    public TableColumn<Program, String> productionTitle;

    // credits table
    public TableView<Credits> creditTable;
    public TableColumn<Credits, String> occupation, person;

    private DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private Controller controller = Controller.getInstance();

    public void initialize() {
        productionTitle.setCellValueFactory(new PropertyValueFactory<Program, String>("title"));
        occupation.setCellValueFactory(new PropertyValueFactory<Credits, String>("occupation"));
        person.setCellValueFactory(new PropertyValueFactory<Credits, String>("person"));

        // retrieving production & credit data from database
        productionTable.setItems((ObservableList<Program>) databaseHandler.getPrograms());
        //creditTable.setItems((ObservableList<Credits>) databaseHandler.getCredits());
    }

    public void buttonAction(ActionEvent event) {
        /**
         * Defining textField states for code-reusability.
         * Also asserting that the textFields cant be null.
         * */
        assert titleField != null;
        assert occupationField != null;
        assert personField != null;

        boolean titleDefined = !titleField.getText().trim().isEmpty();
        boolean occupationDefined = !occupationField.getText().trim().isEmpty();
        boolean personDefined = !personField.getText().trim().isEmpty();

        String title = titleField.getText();
        String occupation = occupationField.getText();
        String person = personField.getText();

        /**
         *  Button "ADD"
         */
        if (event.getSource() == addButton) {
            /** Lets check if something is actually written inside the "production title" textfield... */
            if (titleDefined && !occupationDefined || titleDefined && !personDefined) {
                controller.addProgram(title, productionTable);
                clearText();
            }
            // adding credits for the selected production
            else if (getCurrentlySelectedProduction() != null && occupationDefined && personDefined) {
                controller.addCredits(getCurrentlySelectedProduction(), occupation, person, creditTable);
                clearText();
            } else {
                System.out.println("Something went wrong");
            }
        }
        /**
         *  Button "DELETE"
         */
        else if (event.getSource() == deleteButton) {
            try {
                // make sure credit table is empty
                if (creditTable.getItems().isEmpty()) {
                    // now lets delete the selected program
                    controller.deleteSelectedProgram(getCurrentlySelectedProduction(), title, productionTable, titleField);
                } else {
                    // we must delete the credits first!
                    if (getCurrentlySelectedCreditOccupation() != null && getCurrentlySelectedCreditPerson() != null) {
                        controller.deleteSelectedCredit(getCurrentlySelectedProduction(), title, occupation, person, creditTable);
                        clearText();
                    } else {
                        System.out.println("No credit selected!");
                    }

                }
            } catch (Exception e) {
                System.out.println("IndexOutOfBoundsException");
                System.out.println("(!) Always delete the associated credits first!");
                e.printStackTrace();
            }
        }
        /**
         *  Button "UPDATE"
         */
        else if (event.getSource() == updateButton) {
            // updating selected program
            controller.updateSelectedProgram(/*getCurrentlySelectedProduction(),*/ title, productionTable, creditTable);
            clearText();

            // todo: update selected credit

        }
    }

    public void searchFieldProduction(KeyEvent event) {
        FilteredList<Program> filteredList = new FilteredList<>((ObservableList<Program>) databaseHandler.getPrograms()/*productionsList*/, b -> true);
        searchProductions.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(program -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String Filter = newValue.toLowerCase();

                if (program.getTitle().toLowerCase().contains(Filter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Program> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(productionTable.comparatorProperty());
        productionTable.setItems(sortedList);
    }

    public String getCurrentlySelectedCreditOccupation() {
        int index = creditTable.getSelectionModel().getSelectedIndex();
        Credits credit = creditTable.getItems().get(index);
        return credit.getOccupation();
    }

    public String getCurrentlySelectedCreditPerson() {
        int index = creditTable.getSelectionModel().getSelectedIndex();
        Credits credit = creditTable.getItems().get(index);
        return credit.getPerson();
    }

    public void selectedCreditEvent(MouseEvent mouseEvent) {
        try {
            this.occupationField.setText(getCurrentlySelectedCreditOccupation());
            this.personField.setText(getCurrentlySelectedCreditPerson());
        } catch (Exception e) {
            System.out.println("No credit selected.");
        }
    }

    public String getCurrentlySelectedProduction() {
        int index = productionTable.getSelectionModel().getSelectedIndex();
        Program program = productionTable.getItems().get(index);
        creditTable.setItems((ObservableList<Credits>) databaseHandler.getProgramCredits(program.getID())); // refresh credit data based on selected program
        System.out.println("Program: '" + program.getTitle() + "' is selected");
        return program.getTitle();
    }

    public void selectedProductionEvent(MouseEvent mouseEvent) {
        try {
            this.titleField.setText(getCurrentlySelectedProduction());
        } catch (Exception e) {
            System.out.println("No production selected.");
        }
    }

    public void clearSearchText() {
        searchProductions.clear();
    }

    public void clearText() {
        titleField.clear();
        personField.clear();
        occupationField.clear();
    }

    public void signOut(ActionEvent actionEvent) {
        try {
            App.setRoot("loginScreen");
            //throw new UnsupportedOperationException("Not implemented");
        } catch (Exception ex) {
            //System.out.println("Action not implemented.");
            ex.printStackTrace();
        }
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        controller.closeButtonAction(closeButton);
    }

    public void switchToHelpPopup(ActionEvent actionEvent) {
        App.HelpPopUp.display();
    }

    public void refreshPrograms(){
        productionTable.setItems((ObservableList<Program>) databaseHandler.getPrograms()); // refreshing data
    }

    public void refreshCredits(){
        creditTable.setItems((ObservableList<Credits>) databaseHandler.getProgramCredits(databaseHandler.getProgramID(getCurrentlySelectedProduction()))); // refreshing data
    }
}
