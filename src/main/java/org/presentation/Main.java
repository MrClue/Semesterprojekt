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

        //System.out.println(databaseHandler.getProgramID("Kalbman"));
        //System.out.println(databaseHandler.getProgramID("123"));

        //System.out.println("Credit_ID: "+databaseHandler.getCreditID(1,"Spise bananer", "Kalb"));

        // adding users

        // getting roles
        System.out.println("Role: "+databaseHandler.getRole("aDmIn")); // testing that getRole() ignores case-sensitivity
        /** TESTING END */

        //getProductionsCredits

        for (Credits credit: databaseHandler.getCredits()) {
            System.out.println("The database contains credit: "+credit.toString());
        }

        /*System.out.println();

        Program program = new Program(1, "123");
        List<Credits> listOfCredits = databaseHandler.getProgramCredits(program.getID());
        for (Credits credit: listOfCredits) {
            System.out.println("Program "+program.getTitle()+" contains: "+credit.toString());
        }
        System.out.println();
        */

        //Updating credit
        //databaseHandler.updateCredit(20,"retæjreæt", "werwerwer");

        // starting the application
        App.launcher(args);
    }
}
