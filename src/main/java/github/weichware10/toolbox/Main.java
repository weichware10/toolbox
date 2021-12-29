package github.weichware10.toolbox;

import github.weichware10.toolbox.gui.ConfirmBox;
import github.weichware10.toolbox.gui.TestVorbildschirm;
import github.weichware10.util.Logger;
import github.weichware10.util.config.CodeChartsConfiguration;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.config.Configuration;
import github.weichware10.util.config.ZoomMapsConfiguration;
import github.weichware10.util.db.DataBaseClient;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.joda.time.DateTime;

/**
 * Toolbox GUI.
 *
 */
public class Main extends Application {
    private Stage primaryStage;
    private static ConfigClient configClient;
    private static DataBaseClient dataBaseClient;

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

    @Override
    public void start(Stage primaryStage) {

        resetDataBaseConnection();

        this.primaryStage = primaryStage;

        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(300);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Error when loading main scene", e, true);
            System.exit(-1);
        }

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

    protected void changeDb() {
        Dialog<Void> dialog = new Dialog<>();
        ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataBaseDialog.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            Logger.error("Error while displaying dialog", e);
        }
        if (root == null) {
            return;
        }
        DataBaseDialogController controller = loader.getController();
        // nur bei Erfolg Dialog verlassen
        final Button ok = (Button) dialog.getDialogPane().lookupButton(okButtonType);
        ok.addEventFilter(ActionEvent.ACTION, okEvent -> {
            try {
                // Versuchen Datenbankclient zu erstellen
                dataBaseClient = new DataBaseClient(
                        controller.getUrl(),
                        controller.getUsername(),
                        controller.getPassword(),
                        controller.getSchema());
            } catch (IllegalArgumentException e) {
                // display error
                Logger.info("Error while changing database connection");
                controller.setWarning("Your input is not valid:");
                controller.setError(e.getMessage());
                okEvent.consume();
            }
            // erstellt den Config Client um die Informationen aus der Config zu handeln
            configClient = new ConfigClient(dataBaseClient);
        });
        dialog.getDialogPane().setContent(root);
        dialog.showAndWait();
    }

    protected void createZoomMapsTestTrial(TextField trialIdInput) {
        if (dataBaseClient == null) {
            return;
        }
        ZoomMapsConfiguration zoomMapsConfiguration = new ZoomMapsConfiguration(
                4.2f,
                true,
                Arrays.asList(
                        "https://media.discordapp.net/attachments/707505654218358818/836645130417078282/TBY5IslL.png",
                        "url2", "url3"));

        createTestTrial(new Configuration(
                "dunno yet",
                "Test Question?",
                zoomMapsConfiguration),
                trialIdInput);
    }

    protected void createCodeChartsTestTrial(TextField trialIdInput) {
        if (dataBaseClient == null) {
            return;
        }
        CodeChartsConfiguration codeChartsConfiguration = new CodeChartsConfiguration(
                Arrays.asList("string1", "string2"),
                new int[] { 10, 10 },
                new long[] { 100, 100 },
                true,
                Arrays.asList(
                        "https://media.discordapp.net/attachments/707505654218358818/836645130417078282/TBY5IslL.png",
                        "url2", "url3"));

        createTestTrial(new Configuration(
                "dunno yet",
                "Test Question?",
                codeChartsConfiguration),
                trialIdInput);
    }

    protected void createTestTrial(Configuration configuration, TextField trialIdInput) {
        String configId = dataBaseClient.configurations.set(configuration);
        List<String> trialIds = dataBaseClient.trials.add(configId, 1);
        if (trialIds.size() > 0) {
            trialIdInput.setText(trialIds.get(0));
        }
    }

    /**
     * Setzt die Datenbankverbindung auf die Werte in der env Datei.
     *
     * @return Erfolgsboolean
     */
    protected boolean resetDataBaseConnection() {
        // erstellt die Datenbankverbindung
        try {
            Dotenv dotenv = Dotenv.load();
            // Dotenv dotenv = Dotenv.configure().directory(".").load();
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
                file.getParentFile().mkdirs();
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
