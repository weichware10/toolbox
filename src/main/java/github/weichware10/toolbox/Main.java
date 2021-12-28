package github.weichware10.toolbox;

import github.weichware10.toolbox.gui.ConfirmBox;
import github.weichware10.toolbox.gui.Startbildschirm;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Toolbox GUI.
 *
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        boolean debug = false;
        if (debug) {
            Stage logStage = new Stage();
            TextArea textArea = new TextArea();
            Console console = new Console(textArea);
            PrintStream ps = new PrintStream(console, true);
            System.setOut(ps);
            System.setErr(ps);
            Scene terminal = new Scene(textArea);
            logStage.setScene(terminal);
            logStage.show();
        }


        //startet die erste Szene
        Startbildschirm.display(primaryStage);

        // Event welches beim schließen eines Fensters aufgerufen wird
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // Wir kümmern uns selber um das schließen
                event.consume();
                // Fenster schließen, ja oder nein?
                closeProgramm(primaryStage);
            }
        });

        //Zeigt das Fenster
        primaryStage.getIcons().add(new Image("app-icon.png"));
        primaryStage.show();
    }

    //Nachfrage ob Programm wirklich beendet werden soll
    private void closeProgramm(Stage window) {
        boolean answer = ConfirmBox.display();
        if (answer) {
            window.close();
        }
    }

    /**
     * Loggen in Konsole.
     */
    public static class Console extends OutputStream {

        private TextArea output;

        public Console(TextArea ta) {
            this.output = ta;
        }

        @Override
        public void write(int i) throws IOException {
            output.appendText(String.valueOf((char) i));
        }
    }
}
