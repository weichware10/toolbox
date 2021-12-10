package github.weichware10.toolbox.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Kümmert sich um das Design des Endbildschirms.
 */
public class Endbildschirm {
    static Scene toolBoxEndscreen;

    /**
     * Die eigentliche Funktion, die den Endbildschirm darstellt.
     *
     * @param primaryStage - Hauptfenster um Änderungen vorzunehmen
     * @param toolBoxHome - Fenster um zurück zum Anfang zu kommen
     */
    public static void display(Stage primaryStage, Scene toolBoxHome) {
        Button endProgram = new Button("Beenden");
        endProgram.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        Button backToStart = new Button("Neuer Test");
        backToStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(toolBoxHome);
            }
        });

        HBox layoutEndscreenButtons = new HBox(10);
        layoutEndscreenButtons.setPadding(new Insets(10, 10, 10, 10));
        layoutEndscreenButtons.getChildren().addAll(endProgram, backToStart);

        Label endMessage = new Label("Danke für ihre Teilnahme");

        VBox layoutEndscreen = new VBox(10);
        layoutEndscreen.setPadding(new Insets(10, 10, 10, 10));
        layoutEndscreen.getChildren().addAll(endMessage, layoutEndscreenButtons);

        toolBoxEndscreen = new Scene(layoutEndscreen, 400, 400);

        primaryStage.setScene(toolBoxEndscreen);
    }
}
