package org.application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.data.DatabaseHandler;

public class Controller {

    // DatabaseHandler instance
    private DatabaseHandler databaseHandler = DatabaseHandler.getInstance();

    //Controller
    private static Controller instance;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    // Window Action Events
    public void closeButtonAction(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    // Logic for Button events

    public void addProgram(String title, TableView<Program> programTable) {
        assert title != null;

        // adding to database if production with given title doesnt exists
        if (databaseHandler.getProgramID(title) < 0) {
            databaseHandler.insertProgram(new Program(title));
            System.out.println("The production '" + title + "' added to database!");
        } else {
            System.out.println("The production already exists!");
        }

        programTable.setItems((ObservableList<Program>) databaseHandler.getPrograms()); // refreshing data
    }

    public void addCredits(String selectedProgram, String occupation, String person, TableView<Credits> creditTable) {
        assert selectedProgram != null;
        assert occupation != null;
        assert person != null;

        // checking if credit for selected production doesnt exists in the database
        if (databaseHandler.getCreditID(databaseHandler.getProgramID(selectedProgram), occupation, person) < 0) {
            databaseHandler.insertCredit(new Credits(databaseHandler.getProgramID(selectedProgram), occupation, person));
        } else {
            System.out.println("Credit already exists in database");
        }

        creditTable.setItems((ObservableList<Credits>) databaseHandler.getProgramCredits(databaseHandler.getProgramID(selectedProgram))); // refreshing data
    }

    public void deleteSelectedProgram(String selectedProgram, String title, TableView<Program> programTable, TextField titleField){
        if (selectedProgram != null) {
            // extra check to make sure the production to be deleted ACTUALLY exists in database
            if (databaseHandler.getProgramID(title) > 0) {
                databaseHandler.deleteProgram(databaseHandler.getProgramID(title));
                System.out.println("Production removed.");
            } else {
                System.out.println("The production: '" + title + "' is not a valid production.");
            }
            programTable.setItems((ObservableList<Program>) databaseHandler.getPrograms()); // refreshing data
            titleField.clear();
        } else {
            System.out.println("No production selected!");
        }
    }

    public void deleteSelectedCredit(String selectedProgram, String title, String occupation, String person, TableView<Credits> creditTable){
        databaseHandler.deleteCredit(databaseHandler.getProgramID(title), occupation, person);
        creditTable.setItems((ObservableList<Credits>) databaseHandler.getProgramCredits(databaseHandler.getProgramID(selectedProgram))); // refreshing data
    }

    public void updateSelectedProgram(/*String selectedProgram,*/ String title, TableView<Program> programTable, TableView<Credits> creditTable) {
        if (getCurrentlySelectedProduction(programTable, creditTable) != null && !title.equals("")) {
            String selectedItem = programTable.getSelectionModel().getSelectedItem().toString();

            // We will first check if the new production name isn't already defined in the database.
            // If the name is not already used, we will proceed to update the production title!
            if (databaseHandler.getProgramID(title) > 0) {
                System.out.println("The production '" + title + "' is already in the database!\n"
                        + "Choose another production title...");
            } else {
                databaseHandler.updateProgram(databaseHandler.getProgramID(getCurrentlySelectedProduction(programTable, creditTable)), title);

                // Creating update pop-up window
                Dialog dialog = new Alert(Alert.AlertType.INFORMATION, "The item " + selectedItem + " has updated to " + title);
                dialog.show();
            }
            programTable.setItems((ObservableList<Program>) databaseHandler.getPrograms()); // refreshing data
            //clearText();
            programTable.refresh(); // maybe useless???
        }
    }

    public String getCurrentlySelectedProduction(TableView<Program> programTable, TableView<Credits> creditTable) {
        int index = programTable.getSelectionModel().getSelectedIndex();
        Program program = programTable.getItems().get(index);
        creditTable.setItems((ObservableList<Credits>) databaseHandler.getProgramCredits(program.getID())); // refresh credit data based on selected program
        System.out.println("Program: '" + program.getTitle() + "' is selected");
        return program.getTitle();
    }
}
