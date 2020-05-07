package org.application;

import javafx.beans.property.SimpleStringProperty;
import java.io.Serializable;
import java.util.ArrayList;

public class Program implements Serializable {
    private SimpleStringProperty title;
    private String description;
    private int ID;

    public static ArrayList<String> productionsList = new ArrayList<>();

    public Program(int ID, String title, String description) {
        this.ID = ID;
        this.title = new SimpleStringProperty(title);
        this.description = description;

        productionsList.add(title);
    }

    public Program(int ID, String title){
        this.ID = ID;
        this.title = new SimpleStringProperty(title);
        productionsList.add(title);
    }

    public Program(String title){
        this.title = new SimpleStringProperty(title);
        productionsList.add(title);
    }

    public static void addProduction(String title) {
        productionsList.add(title);
    }

    public static void removeProduction(String title) {
        productionsList.remove(title);
    }

    public static boolean checkProductionExist(String title) {
        return productionsList.contains(title);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getTitle() {
        return this.title.get();
    }

    public boolean findCredit(/*Kreditering*/) {
        boolean exist = false;
        //if it exists, then "exist" = true;
        return exist;
    }

    public static boolean checkDuplicateProduction(String title) {
        boolean check = false;
        if (productionsList.contains(title)){
            check = true;
        }
        return check;
    }

    @Override
    public String toString(){
        return getTitle();
    }
}
