package github.weichware10.toolbox;

import github.weichware10.toolbox.gui.ConfirmBox;
import github.weichware10.toolbox.gui.Startbildschirm;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.db.DataBaseClient;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.event.EventHandler;
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
        //erstellt die Datenbankverbindung
        Dotenv dotenv = Dotenv.load();
        DataBaseClient dataBaseClient = new DataBaseClient(
            dotenv.get("DB_URL"),
            dotenv.get("DB_USERNAME"),
            dotenv.get("DB_PASSWORD"),
            dotenv.get("DB_SCHEMA"));
        //erstellt den Config Client um die Informationen aus der Config zu handeln
        ConfigClient configClient = new ConfigClient(dataBaseClient);

        //startet die erste Szene
        Startbildschirm.display(primaryStage, configClient);

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
        primaryStage.show();
    }

    //Nachfrage ob Programm wirklich beendet werden soll
    private void closeProgramm(Stage window) {
        boolean answer = ConfirmBox.display();
        if (answer) {
            window.close();
        }
    }
}
