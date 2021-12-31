package github.weichware10.toolbox;

import github.weichware10.toolbox.gui.App;
import github.weichware10.toolbox.gui.dialogs.ConfirmDialog;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.PrintStream;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
        Console.setLogfile(logfile);
        Console.setOut(System.out);
        PrintStream ps = new PrintStream(new Console(), true);
        System.setOut(ps);
        System.setErr(ps);

        launch(args);
    }

    /**
     * setzt allgemeine Werte der primaryStage und startet die App.
     *
     * @param primaryStage - das Hauptfenster
     */
    @Override
    public void start(Stage primaryStage) {

        // ICON und TITLE
        primaryStage.getIcons().add(new Image("app-icon.png"));

        // FENSTERGRÖẞE
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        primaryStage.setHeight(screenBounds.getHeight() / 2);
        primaryStage.setWidth(screenBounds.getWidth() / 2);

        // Event welches beim schließen eines Fensters aufgerufen wird
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeRequestFilter);

        // ersten Bildschirm starten
        new App(primaryStage, null);
        // ANZEIGEN
        primaryStage.show();
    }

    /**
     * Filtert Anfragen das Fenster zu schließen
     * - wird das Event consumed, wird das Fenster nicht geschlossen.
     *
     * @param event - das WindowEvent mit der Anfrage
     */
    private void closeRequestFilter(WindowEvent event) {
        String icon = getClass().getResource("thonkang.png").toString();
        // Fenster schließen, ja oder nein?
        boolean confirmation = new ConfirmDialog("Do you want to close the window?", icon)
                .getConfirmation();
        // event consumieren -> nicht schließen
        if (!confirmation) {
            event.consume();
        }
    }
}
