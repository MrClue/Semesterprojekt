package org.application;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Program implements Serializable {
    private SimpleStringProperty title;
    private String description;

    public int getID() {
        return ID;
    }

    private int ID;

    public Program(int ID, String title, String description) {
        this.ID = ID;
        this.title = new SimpleStringProperty(title);
        this.description = description;
    }

    public Program(int ID, String title) {
        this.ID = ID;
        this.title = new SimpleStringProperty(title);
    }

    public Program(String title) {
        this.title = new SimpleStringProperty(title);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getTitle() {
        return this.title.get();
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
