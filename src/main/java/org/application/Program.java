package org.application;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Program implements Serializable {
    private final SimpleStringProperty title;
    private int ID;

    public int getID() {
        return ID;
    }

    public Program(int ID, String title) {
        this.ID = ID;
        this.title = new SimpleStringProperty(title);
    }

    public Program(String title) {
        this.title = new SimpleStringProperty(title);
    }

    public String getTitle() {
        return this.title.get();
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
