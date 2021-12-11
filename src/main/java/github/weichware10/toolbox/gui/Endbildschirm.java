package github.weichware10.toolbox.gui;

import github.weichware10.util.config.ConfigClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
     */
    public static void display(Stage primaryStage, ConfigClient configClient) {
        primaryStage.setTitle("Test beendet");

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
                Startbildschirm.display(primaryStage, configClient);
            }
        });

        HBox layoutEndscreenButtons = new HBox(10);
        layoutEndscreenButtons.setPadding(new Insets(10, 10, 10, 10));
        layoutEndscreenButtons.getChildren().addAll(endProgram, backToStart);
        layoutEndscreenButtons.setAlignment(Pos.CENTER);

        Label endMessage = new Label("Danke für ihre Teilnahme");

        VBox layoutEndscreen = new VBox(10);
        layoutEndscreen.setPadding(new Insets(10, 10, 10, 10));
        layoutEndscreen.getChildren().addAll(endMessage, layoutEndscreenButtons);
        layoutEndscreen.setAlignment(Pos.CENTER);

        toolBoxEndscreen = new Scene(layoutEndscreen, 400, 400);

        primaryStage.setScene(toolBoxEndscreen);
    }
}
