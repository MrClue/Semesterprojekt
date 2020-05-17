package org.presentation;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.application.Controller;
import org.application.Credits;
import org.application.Program;
import org.data.DatabaseHandler;

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
                controller.addProgram(title, programTable);
                controller.clearText(titleField, occupationField, personField);
            }
            // adding credits for the selected production
            else if (occupationDefined && personDefined) {
                controller.addCredits(programTable, occupation, person, creditTable);
                controller.clearText(titleField, occupationField, personField);
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
                    controller.deleteSelectedProgram(programTable, creditTable, title, titleField);
                } else {
                    // we must delete the credits first!
                    controller.deleteSelectedCredit(programTable, creditTable, title, occupation, person);
                    controller.clearText(titleField, occupationField, personField);

                }
            } catch (Exception e) {
                System.out.println("IndexOutOfBoundsException");
                System.out.println("(!) Always delete the associated credits first!");
                //e.printStackTrace();
            }
        }
        /**
         *  Button "UPDATE"
         */
        else if (event.getSource() == updateButton) {
            // updating selected program
            controller.updateSelectedProgram(programTable, creditTable, title);
            controller.clearText(titleField, occupationField, personField);

            // updating selected credit
            // todo: update selected credit

        }
    }

    public void programSearchField(KeyEvent event) {
        controller.programSearchField(programTable, searchProductions);
    }

    public void selectedCreditEvent(MouseEvent mouseEvent) {
        try {
            this.occupationField.setText(controller.getSelectedOccupation(creditTable));
            this.personField.setText(controller.getSelectedPerson(creditTable));
        } catch (Exception e) {
            System.out.println("No credit selected.");
        }
    }

    public void selectedProductionEvent(MouseEvent mouseEvent) {
        try {
            this.titleField.setText(controller.getSelectedProduction(programTable, creditTable));
        } catch (Exception e) {
            System.out.println("No production selected.");
        }
    }

    public void signOut(ActionEvent actionEvent) {
        try {
            App.setRoot("loginScreen");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        controller.closeButtonAction(closeButton);
    }

    public void switchToHelpPopup(ActionEvent actionEvent) {
        App.HelpPopUp.display();
    }
}
