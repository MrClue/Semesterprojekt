package org.presentation;

import org.application.Credits;
import org.application.Program;
import org.data.DatabaseHandler;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        /**
         * -------------------------------------------------------------------------------------------------------------
         * TESTING START
         * -------------------------------------------------------------------------------------------------------------
         **/
        DatabaseHandler databaseHandler;
        databaseHandler = DatabaseHandler.getInstance();

        // getting roles
        System.out.println("Role: " + databaseHandler.getRole("aDmIn")); // testing that getRole() ignores case-sensitivity

        // getting credits in database
        for (Credits credit : databaseHandler.getCredits()) {
            System.out.println("The database contains credit: " + credit.toString());
        }

        /**
         * -------------------------------------------------------------------------------------------------------------
         * TESTING END
         * -------------------------------------------------------------------------------------------------------------
         **/

        // starting the application thread
        App.launcher(args);
    }
}
