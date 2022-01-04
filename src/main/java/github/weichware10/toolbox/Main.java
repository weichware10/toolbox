package github.weichware10.toolbox;

import github.weichware10.toolbox.gui.App;
import github.weichware10.toolbox.gui.util.Log;
import github.weichware10.util.Logger;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.joda.time.DateTime;

/**
 * Toolbox GUI.
 */
public class Main extends Application {
    /**
     * Einstiegspunkt der Toolbox - Einführungsbildschirm.
     *
     * @param args - args
     */
    public static void main(String[] args) {

        // in Datei und Konsole loggen
        String logfile = String.format(
                Dotenv.load().get("LOGS") + "/%s.log", DateTime.now().toString("yMMdd-HHmmss"));
        Logger.setLogfile(logfile);
        launch(args);
    }

    /**
     * setzt allgemeine Werte der primaryStage und startet die App.
     *
     * @param primaryStage - das Hauptfenster
     */
    @Override
    public void start(Stage primaryStage) {

        Log.start();

        // ICON und TITLE
        primaryStage.getIcons().add(new Image("app-icon.png"));

        // FENSTERGRÖẞE
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        primaryStage.setHeight(screenBounds.getHeight() / 2);
        primaryStage.setWidth(screenBounds.getWidth() / 2);

        // ersten Bildschirm starten
        new App(primaryStage, null);
        // ANZEIGEN
        primaryStage.show();
    }
}
