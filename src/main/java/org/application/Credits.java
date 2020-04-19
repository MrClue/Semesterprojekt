package org.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Credits {
    private SimpleStringProperty title;
    private SimpleStringProperty occupation;
    private SimpleStringProperty person;

    public static ArrayList<String> currentTitles = new ArrayList<>();

    public Credits (String title, String occupation, String person){
        this.title = new SimpleStringProperty(title);
        this.occupation = new SimpleStringProperty(occupation);
        this.person = new SimpleStringProperty(person);
        currentTitles.add(title);
        currentTitles.add(occupation);
        currentTitles.add(person);
    }

    public String getTitle() {
        return this.title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getOccupation() {
        return this.occupation.get();
    }

    public void setOccupation(String occupation) {
        this.occupation.set(occupation);
    }

    public String getPerson() {
        return this.person.get();
    }

    public void setPerson(String name) {
        this.person.set(name);
    }

    public static void addTitle(String title) {
        currentTitles.add(title);
    }

    public static void removeTitle(String title) {
        currentTitles.remove(title);
    }

    public static boolean checkTitleExist(String title) {
        return currentTitles.contains(title);
    }

    public static boolean checkDuplicateEntry(String title, String occupation, String name) {
        boolean check = false;
        if (currentTitles.contains(title) && currentTitles.contains(occupation) && currentTitles.contains(name)){
            check = true;
        }
        return check;
    }
}
