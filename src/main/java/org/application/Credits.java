package org.application;

import java.io.Serializable;

public class Credits implements Serializable {
    private String title;
    private String occupation;
    private String person;

    public Credits (String t, String o, String p){
        this.title = t;
        this.occupation = o;
        this.person = p;
    }

    public Boolean checkExistance() {
        boolean exist = false;
        //if it exists, then "exist" = true;
        return exist;
    }
}
