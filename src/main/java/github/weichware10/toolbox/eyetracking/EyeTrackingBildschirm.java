package github.weichware10.toolbox.eyetracking;

import github.weichware10.toolbox.gui.Endbildschirm;
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
 * Kümmert sich um das Design des Tests Eyetracking.
 */
public class EyeTrackingBildschirm {

    /**
     * Die eigentliche Funktion, die den Zoombildschirm darstellt.
     *
     * @param primaryStage - Hauptfenster um Änderungen vorzunehmen
     * @param configClient - Informationen aus der Configfile holen
     */
    public static void display(Stage primaryStage, ConfigClient configClient) {
        primaryStage.setTitle("EyeTracking Test");

        Button startButton = new Button("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Endbildschirm.display(primaryStage, configClient);
            }
        });

        Button endButton = new Button("Abschließen");
        endButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Endbildschirm.display(primaryStage, configClient);
            }
        });

        HBox layoutButtons = new HBox(10);
        layoutButtons.setPadding(new Insets(10, 10, 10, 10));
        layoutButtons.getChildren().addAll(startButton, endButton);
        layoutButtons.setAlignment(Pos.CENTER);

        Label testMessage = new Label("Sie können den EyeTracking-Test starten");

        VBox layoutFinal = new VBox(10);
        layoutFinal.setPadding(new Insets(10, 10, 10, 10));
        layoutFinal.getChildren().addAll(testMessage, layoutButtons);
        layoutFinal.setAlignment(Pos.CENTER);

        Scene toolBoxEyeTracking = new Scene(layoutFinal, 400, 400);

        primaryStage.setScene(toolBoxEyeTracking);
    }
}
