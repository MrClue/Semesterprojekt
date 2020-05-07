package org.presentation;

import org.application.Program;
import org.data.DatabaseHandler;

public class Main {

    public static void main(String[] args) {
        /** TESTING START */
        DatabaseHandler databaseHandler;
        databaseHandler = DatabaseHandler.getInstance();

        // adding users

        // getting roles
        System.out.println("Role: "+databaseHandler.getRole("AdMin")); // testing that getRole() ignores case-sensitivity
        /** TESTING END */

        // starting the application
        App.launcher(args);
    }
}
