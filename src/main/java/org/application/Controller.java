package org.application;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.data.DatabaseHandler;

import java.io.*;
import java.util.Scanner;

public class Controller {

    // Storing login information
    private final File file = new File("userLogin.txt");

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

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * LOGIN SCREEN - (LOGIC)
     * -----------------------------------------------------------------------------------------------------------------
     */
    // remember me functionality:
    public void rememberLogin(TextField usernameTextArea, TextField passwordTextArea) {
        try {
            if (!file.exists()) {
                file.createNewFile();  //if the file !exist create a new one
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            bw.write(usernameTextArea.getText()); // write the name
            bw.newLine(); // leave a new Line
            bw.write(passwordTextArea.getText()); //write the password
            bw.close(); // close the BufferedWriter

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void autoFillLogin(TextField usernameTextArea, TextField passwordTextArea, RadioButton rememberMeButton) {
        try {
            if (file.exists()) {
                Scanner scan = new Scanner(file); // use Scanner to read the File

                usernameTextArea.setText(scan.nextLine()); // append the text to name field
                passwordTextArea.setText(scan.nextLine()); // append the text to password field
                scan.close();

                rememberMeButton.selectedProperty().set(true); // the "remember me" button is selected if file exists
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * MAIN MENU - (LOGIC)
     * -----------------------------------------------------------------------------------------------------------------
     */

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

        refreshProgramData(programTable);

    }

    public void addCredits(TableView<Program> programTable, String occupation, String person, TableView<Credits> creditTable) {
        assert occupation != null;
        assert person != null;

        if (getSelectedProduction(programTable, creditTable) != null) {
            // checking if credit for selected production doesnt exists in the database
            if (databaseHandler.getCreditID(databaseHandler.getProgramID(getSelectedProduction(programTable, creditTable)), occupation, person) < 0) {
                databaseHandler.insertCredit(new Credits(databaseHandler.getProgramID(getSelectedProduction(programTable, creditTable)), occupation, person));
            } else {
                System.out.println("Credit already exists in database");
            }
        }

        refreshCreditData(programTable, creditTable);
    }

    public void deleteSelectedProgram(TableView<Program> programTable, TableView<Credits> creditTable, String title, TextField titleField) {
        if (getSelectedProduction(programTable, creditTable) != null) {
            // extra check to make sure the production to be deleted ACTUALLY exists in database
            if (databaseHandler.getProgramID(title) > 0) {
                databaseHandler.deleteProgram(databaseHandler.getProgramID(title));
                System.out.println("Production removed.");
            } else {
                System.out.println("The production: '" + title + "' is not a valid production.");
            }

            refreshProgramData(programTable);
            titleField.clear();
        } else {
            System.out.println("No production selected!");
        }
    }

    public void deleteSelectedCredit(TableView<Program> programTable, TableView<Credits> creditTable, String title, String occupation, String person) {
        if (getSelectedOccupation(creditTable) != null && getSelectedPerson(creditTable) != null) {
            databaseHandler.deleteCredit(databaseHandler.getProgramID(title), occupation, person);
        } else {
            System.out.println("No credit selected!");
        }

        refreshCreditData(programTable, creditTable);
    }

    public void updateSelectedProgram(TableView<Program> programTable, TableView<Credits> creditTable, String title) {
        if (getSelectedProduction(programTable, creditTable) != null && !title.equals("")) {
            String selectedItem = programTable.getSelectionModel().getSelectedItem().toString();

            // We will first check if the new production name isn't already defined in the database.
            // If the name is not already used, we will proceed to update the production title!
            if (databaseHandler.getProgramID(title) > 0) {
                System.out.println("The production '" + title + "' is already in the database!\n"
                        + "Choose another production title...");
            } else {
                databaseHandler.updateProgram(databaseHandler.getProgramID(getSelectedProduction(programTable, creditTable)), title);

                // Creating update pop-up window
                Dialog dialog = new Alert(Alert.AlertType.INFORMATION, "The item " + selectedItem + " has updated to " + title);
                dialog.show();
            }

            refreshProgramData(programTable);
            //clearText();
            programTable.refresh(); // maybe useless???
        }
    }

    public void updateSelectedCredit(TableView<Program> programTable, TableView<Credits> creditTable, String title, String occupation, String person) {
        try {
            if (getSelectedOccupation(creditTable) != null && !occupation.equals("") && getSelectedPerson(creditTable) != null && !person.equals("")) {
                String selectedItem = creditTable.getSelectionModel().getSelectedItem().toString();

                //int creditID = databaseHandler.getCreditID(databaseHandler.getProgramID(title), occupation, person);
                int creditID = databaseHandler.getCreditID(databaseHandler.getProgramID(title), getSelectedOccupation(creditTable), getSelectedPerson(creditTable));
                // Checking if the credit details doesnt already exist in database
                if (creditID > 0) {
                    databaseHandler.updateCredit(creditID, occupation, person);

                    // Creating update pop-up window
                    Dialog dialog = new Alert(Alert.AlertType.INFORMATION,
                            "The item: " + selectedItem +
                                    "\n" + "has updated to: " +
                                    "\n" + title + ", " + occupation + ", " + person
                    );
                    dialog.show();
                } else {
                    System.out.println("The credit '" + title + ": " + occupation + ", " + person + "' already exist!");
                }
                refreshCreditData(programTable, creditTable);
                creditTable.refresh();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }

    public String getSelectedProduction(TableView<Program> programTable, TableView<Credits> creditTable) {
        int index = programTable.getSelectionModel().getSelectedIndex();
        Program program = programTable.getItems().get(index);
        refreshCreditData(programTable, creditTable);
        System.out.println("Program: '" + program.getTitle() + "' is selected");
        return program.getTitle();
    }

    public String getSelectedOccupation(TableView<Credits> creditTable) {
        int index = creditTable.getSelectionModel().getSelectedIndex();
        Credits credit = creditTable.getItems().get(index);
        return credit.getOccupation();
    }

    public String getSelectedPerson(TableView<Credits> creditTable) {
        int index = creditTable.getSelectionModel().getSelectedIndex();
        Credits credit = creditTable.getItems().get(index);
        return credit.getPerson();
    }

    public void clearText(TextField titleField, TextField personField, TextField occupationField) {
        titleField.clear();
        personField.clear();
        occupationField.clear();
    }

    public void programSearchField(TableView<Program> programTable, TextField searchProductions) {
        FilteredList<Program> filteredList = new FilteredList<>((ObservableList<Program>) databaseHandler.getPrograms(), b -> true);
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
        sortedList.comparatorProperty().bind(programTable.comparatorProperty());
        programTable.setItems(sortedList);
    }

    public void creditSearchField(TableView<Program> programTable, TableView<Credits> creditTable, TextField searchCredits) {
        FilteredList<Credits> filteredList = new FilteredList<>((ObservableList<Credits>) databaseHandler.getProgramCredits(databaseHandler.getProgramID(getSelectedProduction(programTable, creditTable))), b -> true);
        searchCredits.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(credits -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String Filter = newValue.toLowerCase();

                if (credits.getOccupation().toLowerCase().contains(Filter) || credits.getPerson().toLowerCase().contains(Filter)) {
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

    public void refreshProgramData(TableView<Program> programTable) {
        programTable.setItems((ObservableList<Program>) databaseHandler.getPrograms());
    }

    public void refreshCreditData(TableView<Program> programTable, TableView<Credits> creditTable) {
        int index = programTable.getSelectionModel().getSelectedIndex();
        Program program = programTable.getItems().get(index);
        creditTable.setItems((ObservableList<Credits>) databaseHandler.getProgramCredits(program.getID())); // refresh credit data based on selected program
    }


}
