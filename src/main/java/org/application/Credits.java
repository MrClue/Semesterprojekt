package org.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Credits extends Program {
    private SimpleStringProperty productionTitle;
    private SimpleStringProperty occupation;
    private SimpleStringProperty person;

    public static ArrayList<String> creditsList = new ArrayList<>();

    public Credits (String ID, String occupation, String person){
        super(ID);
        this.productionTitle = new SimpleStringProperty(ID); //test
        this.occupation = new SimpleStringProperty(occupation);
        this.person = new SimpleStringProperty(person);
        creditsList.add(ID); // test
        creditsList.add(occupation);
        creditsList.add(person);
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

    public static boolean checkDuplicateCredit(String ID, String occupation, String name) {
        boolean check = false;
        if (creditsList.contains(ID) && creditsList.contains(occupation) && creditsList.contains(name)){
            check = true;
        }
        return check;
    }

    public String getProductionTitle() {
        return productionTitle.get();
    }
}
