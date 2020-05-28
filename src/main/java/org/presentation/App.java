package org.presentation;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.application.HelpPopUp;
import org.data.DatabaseHandler;

import java.io.IOException;

/**
 * Project: JavaFX Application
 * Author: Armin, Daniel, Sebastian, Villy & Yusaf.
 */

public class App extends Application {

    private static Scene scene;
    public static HelpPopUp helpPopUp;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("loginScreen"));
        System.out.println(DatabaseHandler.getInstance().getRole("John"));
        stage.initStyle(StageStyle.UNDECORATED); //removes default top panel of application

        helpPopUp = new HelpPopUp();
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

    public static void launcher() {
        launch();
    }

}