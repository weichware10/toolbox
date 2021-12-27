package github.weichware10.toolbox.zoommaps;

import github.weichware10.util.config.ConfigClient;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Kümmert sich um das Design des Tests Zoommaps.
 */
public class ZoomMapsBildschirm {

    /**
     * Die eigentliche Funktion, die den Zoombildschirm darstellt.
     *
     * @param primaryStage - Hauptfenster um Änderungen vorzunehmen
     * @param configClient - Informationen aus der Configfile holen
     */
    public static void display(Stage primaryStage, ConfigClient configClient) {
        Label message = new Label("ZommMaps Test");

        VBox layout = new VBox(10);
        layout.getChildren().add(message);
        layout.setAlignment(Pos.CENTER);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        Scene scene = new Scene(layout, screenBounds.getWidth() / 2, screenBounds.getHeight() / 2);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
    }
}
