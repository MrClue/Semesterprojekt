package org.presentation;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.application.Controller;

import java.io.IOException;

/**
 * Project: JavaFX Application
 * Author: Armin, Daniel, Sebastian, Villy & Yusaf.
 */

public class App extends Application {

    private static Scene scene;
    public static HelpPopUp HelpPopUp;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("loginScreen"));
        //scene.getStylesheets().add("ECMS.css");
        stage.initStyle(StageStyle.UNDECORATED); //removes default top panel of application

        HelpPopUp = new HelpPopUp();
        /**
         * Making the scene drag-able:
         * #1 We must calculate the mouse pressed location.
         * #2 We use this location to calculate the mouse dragging location.
         **/
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getScreenX() - xOffset);
                stage.setY(mouseEvent.getScreenY() - yOffset);
                stage.setOpacity(0.85f); // making it look nice :)
            }
        });
        // restoring opacity on mouse release
        scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setOpacity(1.0f);
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
//        Controller c1 = new Controller();
//        try {
//            c1.createProgram("badehotel", "Hoteler der bader", 1, "Badehotel", "Lydman", "Bob Bobbyson");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            c1.testToSeeIfTextfileWorks();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            c1.createProgram("The bade hotel", "Hoteler der bader", 1, "Badehotel", "Lydman", "Bob Bobbyson");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            c1.testToSeeIfTextfileWorks();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }
    }