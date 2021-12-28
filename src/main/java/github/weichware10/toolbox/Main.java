package github.weichware10.toolbox;

import github.weichware10.toolbox.gui.ConfirmBox;
import github.weichware10.toolbox.gui.TestVorbildschirm;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.db.DataBaseClient;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.io.OutputStream;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Toolbox GUI.
 *
 */
public class Main extends Application {
    private Stage primaryStage;
    private static ConfigClient configClient;
    private static DataBaseClient dataBaseClient;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        resetDataBaseConnection();

        this.primaryStage = primaryStage;

        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(300);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));

        Parent root = loader.load();

        AppController controller = loader.getController();
        controller.setMain(this);

        // Event welches beim schließen eines Fensters aufgerufen wird
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // Wir kümmern uns selber um das schließen
                event.consume();
                // Fenster schließen, ja oder nein?
                ConfirmBox.display(primaryStage);
            }
        });

        Scene scene = new Scene(root, 300, 275);

        primaryStage.getIcons().add(new Image("app-icon.png"));
        primaryStage.setTitle("Toolbox");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Konsole zum Debuggen
    /*
     * boolean debug = false;
     * if (debug) {
     * Stage logStage = new Stage();
     * TextArea textArea = new TextArea();
     * Console console = new Console(textArea);
     * PrintStream ps = new PrintStream(console, true);
     * System.setOut(ps);
     * System.setErr(ps);
     * Scene terminal = new Scene(textArea);
     * logStage.setScene(terminal);
     * logStage.show();
     * }
     */

    /**
     * lädt Config aus JSON und versucht den Test zu starten.
     *
     * @param warnText - Fehlernachricht
     */
    protected void loadJsonConfig(Text warnText) {

        warnText.setVisible(false);

        // Datei auswählen setup
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("JSON Config auswählen");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("JSON Dateien", "*.json"));

        warnText.setText("JSON Config not valid");
        // Dateipfad als String speichern und json laden
        String location = fileChooser.showOpenDialog(primaryStage).getAbsolutePath();
        if (!configClient.loadFromJson(location)) {
            warnText.setVisible(true);
            return;
        }

        // Tutorial noch hinzufügen an dieser Stelle

        try {
            TestVorbildschirm.display(primaryStage, configClient);
        } catch (Exception e) {
            warnText.setVisible(true);
            return;
        }
    }

    protected void startTrial(String trialId, Text warnText) {

        warnText.setVisible(false);

        if (dataBaseClient == null) {
            warnText.setText("no database connection");
            warnText.setVisible(true);
            return;
        }
        warnText.setText("Bitte geben sie eine gültige ID ein.");
        if (trialId.length() == 0) {
            warnText.setVisible(true);
            return;
        }

        // Verfügbarkeit überprüfen
        boolean available = dataBaseClient.trials.getAvailability(trialId);
        if (!available) {
            warnText.setVisible(true);
            return;
        }

        // Config laden
        boolean success = configClient.loadFromDataBase(trialId);
        if (!success) {
            warnText.setVisible(true);
            return;
        }

        // Tutorial noch hinzufügen an dieser Stelle
        TestVorbildschirm.display(primaryStage, configClient);
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

    /**
     * Setzt die Datenbankverbindung auf die Werte in der env Datei.
     *
     * @return Erfolgsboolean
     */
    protected static boolean resetDataBaseConnection() {
        // erstellt die Datenbankverbindung
        try {
            Dotenv dotenv = Dotenv.load();
            String url = dotenv.get("DB_URL");
            String username = dotenv.get("DB_USERNAME");
            String password = dotenv.get("DB_PASSWORD");
            String schema = dotenv.get("DB_SCHEMA");
            dataBaseClient = new DataBaseClient(
                    url,
                    username,
                    password,
                    schema);
        } catch (IllegalArgumentException e) {
            return false;
        }
        // erstellt den Config Client um die Informationen aus der Config zu handeln
        configClient = new ConfigClient(dataBaseClient);
        return true;
    }
}
