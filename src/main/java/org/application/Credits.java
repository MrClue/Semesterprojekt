package org.application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Credits {
    private SimpleIntegerProperty productionID;
    private SimpleStringProperty occupation;
    private SimpleStringProperty person;

    public Credits (int programID, String occupation, String person){
        this.productionID = new SimpleIntegerProperty(programID);
        this.occupation = new SimpleStringProperty(occupation);
        this.person = new SimpleStringProperty(person);
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

    public int getProgramID() {
        return productionID.get();
    }
    @Override
    public String toString() {
        return "Credits{" +
                "productionID=" + productionID +
                ", occupation=" + occupation +
                ", person=" + person +
                '}';
    }
}
