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
import org.application.Credits;
import org.application.Program;
import org.data.DatabaseHandler;

import java.io.IOException;
import java.io.Serializable;

/**
 * ToDO List:
 * Make creditsTable only show relevant credits (those whose ID is similar to the selected production name).
 * Make it possible to type Production title and Credit information at the same time.
 * Make a search bar for the Credit table.
 * Fix errors and optimize logic.
 * */

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

    public void initialize() {
        productionTitle.setCellValueFactory(new PropertyValueFactory<Program, String>("title"));
        occupation.setCellValueFactory(new PropertyValueFactory<Credits, String>("occupation"));
        person.setCellValueFactory(new PropertyValueFactory<Credits, String>("person"));

        // retrieving production & credit data from database
        productionTable.setItems((ObservableList<Program>) databaseHandler.getPrograms());
        //creditTable.setItems((ObservableList<Credits>) databaseHandler.getCredits());
    }

    public void buttonAction(ActionEvent event){
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
                // adding to database if production with given title doesnt exists
                if (databaseHandler.getProgramID(title) < 0){
                    databaseHandler.insertProgram(new Program(title));
                    System.out.println("The production '"+title+"' added to database!");
                } else {
                    System.out.println("The production already exists!");
                }
                productionTable.setItems((ObservableList<Program>) databaseHandler.getPrograms()); // refreshing data
                clearText();

            }
            // adding credits for the selected production
            else if (getCurrentlySelectedProduction() != null /*selectedProduction != -1*/ && occupationDefined && personDefined){
                // checking if credit for selected production doesnt exists in the database
                if (databaseHandler.getCreditID(getCurrentlySelectedProduction(), occupation, person) < 0){
                    databaseHandler.insertCredit(new Credits(databaseHandler.getProgramID(getCurrentlySelectedProduction()), occupation, person));
                } else {
                    System.out.println("Credit already exists in database");
                }
                creditTable.setItems((ObservableList<Credits>) databaseHandler.getCredits()); // refreshing data
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
                if (getCurrentlySelectedCreditOccupation() != null && getCurrentlySelectedCreditPerson() != null) {
                    databaseHandler.deleteCredit(databaseHandler.getProgramID(title), occupation, person);
                    creditTable.setItems((ObservableList<Credits>) databaseHandler.getCredits()); // refreshing data
                    return;
                } else {
                    System.out.println("No credit selected!");
                }

                if (getCurrentlySelectedProduction() != null){
                    // extra check to make sure the production to be deleted ACTUALLY exists in database
                    if (databaseHandler.getProgramID(title) > 0){
                        databaseHandler.deleteProgram(getCurrentlySelectedProduction()); //ToDo: call the deleteProgram() method from DatabaseHandler
                        System.out.println("Production removed.");
                    } else {
                        System.out.println("The production: '"+title+"' is not a valid production.");
                    }
                    productionTable.setItems((ObservableList<Program>) databaseHandler.getPrograms()); // refreshing data
                    titleField.clear();
                } else {
                    System.out.println("No production selected!");
                }
            } catch (Exception e) {
                System.out.println("IndexOutOfBoundsException");
            }
        }
        /**
         *  Button "UPDATE"
         */
        else if (event.getSource() == updateButton){
            if (getCurrentlySelectedProduction() != null /*selectedProduction != -1*/ && !title.equals("")){
                String selectedItem = this.productionTable.getSelectionModel().getSelectedItem().toString();
                //Dialog dialog = new Alert(Alert.AlertType.INFORMATION, "The item "+selectedItem+" has updated to "+title);
                //dialog.show();

                // We will first check if the new production name isn't already defined in the database.
                // If the name is not already used, we will proceed to update the production title!
                if (databaseHandler.getProgramID(title) > 0){
                    System.out.println("The production '"+title+"' is already in the database!\n"
                            +"Choose another production title...");
                } else {
                    //todo: We need to update the selected production
                    //      and then we refresh the table.
                    databaseHandler.updateProgram(); // todo

                    // Creating update pop-up window
                    Dialog dialog = new Alert(Alert.AlertType.INFORMATION, "The item "+selectedItem+" has updated to "+title);
                    dialog.show();
                }
                productionTable.setItems((ObservableList<Program>) databaseHandler.getPrograms()); // refreshing data
                clearText();
                productionTable.refresh(); // maybe useless???
            }
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
        creditTable.setItems((ObservableList<Credits>) databaseHandler.getProductionCredits(program)); // refreshing credit data
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

    public void signOut(ActionEvent actionEvent) throws IOException {
        try {
            throw new UnsupportedOperationException("Not implemented");
        } catch (Exception ex) {
            System.out.println("Action not implemented.");
        }
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void switchToHelpPopup(ActionEvent actionEvent) {
        App.HelpPopUp.display();
    }
}
