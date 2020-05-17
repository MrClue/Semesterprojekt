package org.application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelpPopUp {

    public void display() {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("ECMS - Help");


        Label label1 = new Label(
                "ECMS is a tool to help you find credits for TV2 productions.\n"
                        + "To find a production, simply type your searchword into the\n"
                        + "searchfield and click search.\n"
                        + "From the list, click a production to view the credits. \n"
                        + "\n"
                        + "\n"
                        + "Producers should log in to add, edit and delete productions."
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1);
        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 400, 200);

        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }
}
