package org.application;

import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.data.DatabaseHandler;

import java.io.*;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginSystem {

    private DatabaseHandler databaseHandler = DatabaseHandler.getInstance();

    public boolean login(String username, String password) {
        Role role = databaseHandler.getRole(username);
        boolean valid = false;
        try {
            if (role.getName().toLowerCase().compareTo(username.toLowerCase()) == 0 && role.getPassword().compareTo(password) == 0) {
                valid = true;
            } else {
                System.out.println("Username or password is incorrect");
            }
        } catch (NullPointerException ex) {
            System.out.println("Login does not exist");
        }
        return valid;
    }

    public int authorityLevel(String username) {
        Role role = databaseHandler.getRole(username);
        return role.getAuthLvl();
    }

}
