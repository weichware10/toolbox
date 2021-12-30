package github.weichware10.toolbox;

import github.weichware10.toolbox.gui.App;
import github.weichware10.toolbox.gui.dialogs.ConfirmDialog;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
        String location = Dotenv.configure().directory(".").load().get("LOGS");
        PrintStream ps = new PrintStream(new Console(location, System.out), true);
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

    // TODO: in util verschieben
    /**
     * Loggen in Konsole.
     */
    public static class Console extends OutputStream {

        private PrintStream log;
        private PrintStream out;

        /**
         * Erstellt eine neue Konsole um in eine Datei und in die Konsole zu loggen.
         *
         * @param logfile - Speicherort des Logfiles.
         */
        public Console(String logfile, PrintStream out) {
            this.out = out;
            if (logfile == null) {
                return;
            }
            logfile = String.format(logfile + "/%s.log", DateTime.now().toString("yMMdd-HHmmss"));
            try {
                File file = new File(logfile);
                file.createNewFile();
                log = new PrintStream(file);
            } catch (IOException e) {
                log = null;
            }
        }

        @Override
        public void write(int i) throws IOException {
            this.out.append((char) i);
            if (log != null) {
                log.append((char) i);
            }
        }
    }
}
