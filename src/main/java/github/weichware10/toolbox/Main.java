package github.weichware10.toolbox;

import github.weichware10.toolbox.gui.App;
import github.weichware10.util.Files;
import github.weichware10.util.Logger;
import github.weichware10.util.gui.Log;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.joda.time.DateTime;

/**
 * Toolbox GUI.
 */
public class Main extends Application {

    public static final double MINWIDTH = 900;
    public static final double MINHEIGHT = 500;

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
        // temporären Ordner löschen
        Runtime.getRuntime().addShutdownHook(Files.deleteTempDir());
        launch(args);
    }

    /**
     * setzt allgemeine Werte der primaryStage und startet die App.
     *
     * @param primaryStage - das Hauptfenster
     */
    @Override
    public void start(Stage primaryStage) {

        Log.start("app-icon.png");

        // Icon und Titel
        primaryStage.getIcons().add(new Image("app-icon.png"));

        // Fenstergröße
        primaryStage.setMinWidth(MINWIDTH);
        primaryStage.setMinHeight(MINHEIGHT);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        primaryStage.setHeight(screenBounds.getHeight() / 2);
        primaryStage.setWidth(screenBounds.getWidth() / 2);
        // Hauptfenster schließt komplettes Programm
        primaryStage.setOnCloseRequest(e -> Platform.exit());

        // ersten Bildschirm starten
        new App(primaryStage, null);
        // Anzeigen
        primaryStage.show();
    }
}
