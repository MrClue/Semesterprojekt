package org.application;

import java.io.Serializable;

public class Program implements Serializable {
    private String title;
    private String description;
    private int ext_ID;
    private Credits credit;

    public Program(String title, String description, int ext_ID, Credits credit) {
        this.title = title;
        this.description = description;
        this.ext_ID = ext_ID;
        this.credit = credit;
    }

    public String getTitle() {
        return title;
    }

    public void addCredit(Credits credit) {
        this.credit = credit;
    }

    public boolean findCredit(/*Kreditering*/) {
        boolean exist = false;
        //if it exists, then "exist" = true;
        return exist;
    }
}
