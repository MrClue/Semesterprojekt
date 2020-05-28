package org.application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Credits {
    private final SimpleIntegerProperty productionID;
    private final SimpleStringProperty occupation;
    private final SimpleStringProperty person;

    public Credits(int programID, String occupation, String person) {
        this.productionID = new SimpleIntegerProperty(programID);
        this.occupation = new SimpleStringProperty(occupation);
        this.person = new SimpleStringProperty(person);
    }

    public String getOccupation() {
        return this.occupation.get();
    }

    public String getPerson() {
        return this.person.get();
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
