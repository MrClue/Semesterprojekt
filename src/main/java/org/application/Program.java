package org.application;

public class Program {
    private String title;
    private String description;
    private int ext_ID;
    //private Kreditering credit;

    public Program(String title, String description, int ext_ID/*, Kreditering credit*/) {
        this.title = title;
        this.description = description;
        this.ext_ID = ext_ID;
        //this.credit = credit
    }

    public void addCredit(/* kreditering credit*/) {
        //this.credit = credit
    }

    public boolean findCredit(/*Kreditering*/) {
        boolean exist = false;
        //if it exists, then "exist" = true;
        return exist;
    }
}
