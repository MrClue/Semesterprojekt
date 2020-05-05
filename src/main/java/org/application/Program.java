package org.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableArray;

import java.io.Serializable;
import java.util.ArrayList;

public class Program implements Serializable {
    private SimpleStringProperty title;
    private String description;
    private int ext_ID;
    private Credits credit;

    public static ArrayList<String> productionsList = new ArrayList<>();


    public Program(int ID, String title, String description, Credits credit) {
        this.ext_ID = ID;
        this.title = new SimpleStringProperty(title);
        this.description = description;
        this.credit = credit;

        productionsList.add(title);
    }

    // temporary
    public Program(String title){
        this.title = new SimpleStringProperty(title);
        productionsList.add(title);
    }

    public static void addProduction(String title) {
        productionsList.add(title);
    }

    public static void removeProduction(String title){
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

    public void addCredit(Credits credit) {
        this.credit = credit;
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
