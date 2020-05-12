package org.presentation;

import org.application.Credits;
import org.application.Program;
import org.data.DatabaseHandler;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        /** TESTING START */
        DatabaseHandler databaseHandler;
        databaseHandler = DatabaseHandler.getInstance();

        // adding users

        // getting roles
        System.out.println("Role: "+databaseHandler.getRole("AdMin")); // testing that getRole() ignores case-sensitivity
        /** TESTING END */

        //getProductionsCredits

        for (Credits credit: databaseHandler.getCredits()) {
            System.out.println("The database contains credit: "+credit.toString());
        }

        System.out.println();

        Program program = new Program(1, "123");
        List<Credits> listOfCredits = databaseHandler.getProductionCredits(program);
        for (Credits credit: listOfCredits) {
            System.out.println("Program "+program.getTitle()+" contains: "+credit.toString());
        }

        // starting the application
        App.launcher(args);
    }
}
