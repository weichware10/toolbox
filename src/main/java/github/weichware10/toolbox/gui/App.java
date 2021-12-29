package github.weichware10.toolbox.gui;

import github.weichware10.toolbox.gui.dialogs.DataBaseDialog;
import github.weichware10.util.Logger;
import github.weichware10.util.config.CodeChartsConfiguration;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.config.Configuration;
import github.weichware10.util.config.ZoomMapsConfiguration;
import github.weichware10.util.db.DataBaseClient;
import io.github.cdimascio.dotenv.Dotenv;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Hauptbildschirm der Toolbox.
 */
public class App {
    private Stage primaryStage;
    private ConfigClient configClient;
    private DataBaseClient dataBaseClient;

    /**
     * zeigt den Hauptbildschirm an.
     *
     * @param primaryStage   - Fenster
     * @param dataBaseClient - der DataBaseClient, falls man ihn vom Ende
     *                       wiederverwendet
     */
    public App(Stage primaryStage, DataBaseClient dataBaseClient) {

        primaryStage.setTitle("Toolbox - Start");

        if (dataBaseClient == null) {
            resetDataBaseConnection();
        } else {
            this.dataBaseClient = dataBaseClient;
            this.configClient = new ConfigClient(dataBaseClient);
        }

        this.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Error when loading main scene", e, true);
            System.exit(-1);
        }

        AppController controller = loader.getController();
        controller.setApp(this);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
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
            new PreTest(primaryStage, configClient, dataBaseClient);
        } catch (Exception e) {
            warnText.setVisible(true);
            return;
        }
    }

    /**
     * Nimmt eine trialId entgegen und startet den Versuch. Bei Fehlern wird eine Warnung angezeigt.
     *
     * @param trialId - die ID des Versuches
     * @param warnText - das warnText-Objekt
     */
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
        new PreTest(primaryStage, configClient, dataBaseClient);
    }

    /**
     * Erstellt eine neue ZoomMaps Konfiguration,
     * um die Konfiguration an {@link #createTestTrial(Configuration, TextField)} weiterzugeben.
     *
     * @param trialIdInput - das TextField zum Eintragen der generierten ID
     * @param warnText - Text Objekt zum Ausgeben einer Warnung
     */
    protected void createZoomMapsTestTrial(TextField trialIdInput, Text warnText) {
        if (dataBaseClient == null) {
            warnText.setText("no database connection");
            warnText.setVisible(true);
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

    /**
     * Erstellt eine neue CodeCharts Konfiguration,
     * um die Konfiguration an {@link #createTestTrial(Configuration, TextField)} weiterzugeben.
     *
     * @param trialIdInput - das TextField zum Eintragen der generierten ID
     * @param warnText - Text Objekt zum Ausgeben einer Warnung
     */
    protected void createCodeChartsTestTrial(TextField trialIdInput, Text warnText) {
        if (dataBaseClient == null) {
            warnText.setText("no database connection");
            warnText.setVisible(true);
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

    /**
     * Erstellt einen neuen Versuch
     * und trägt die dazugehörige ID in das Textfeld ein.
     *
     * @param configuration - die zu benutzende configuration
     * @param trialIdInput - das TextField zum Eintragen der generierten ID
     */
    protected void createTestTrial(Configuration configuration,
            TextField trialIdInput) {
        String configId = dataBaseClient.configurations.set(configuration);
        List<String> trialIds = dataBaseClient.trials.add(configId, 1);
        if (trialIds.size() > 0) {
            trialIdInput.setText(trialIds.get(0));
        }
    }

    /**
     * ändert die Zugangsdaten zur Datenbank.
     * dabei wird ein neuer Dialog eingeblendet
     */
    protected void changeDb() {
        DataBaseClient newClient = new DataBaseDialog().getDataBaseClient();
        if (newClient == null) {
            return;
        }

        dataBaseClient = newClient;
        // erstellt den Config Client um die Informationen aus der Config zu handeln
        configClient = new ConfigClient(dataBaseClient);
    }

    /**
     * öffnet die Dokumentation im Browser.
     */
    protected void openDocs() {
        if (Desktop.isDesktopSupported()
                && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("https://weichware10.github.io/dokumente/"));
            } catch (IOException | URISyntaxException e) {
                Logger.error("error occured while opening documentation", e);
            }
        }
    }

    /**
     * Setzt die Datenbankverbindung auf die Werte in der .env Datei.
     * Der Erfolg kann daran abgelesen werden, ob {@link #dataBaseClient} {@code null} ist.
     * Der ConfigClient wird im gleichen Zug angepasst.
     */
    protected void resetDataBaseConnection() {
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
            Logger.error("error when loading env", e);
            // auf null setzen, falls die Verbindung vorher angepasst wurde und dies gewünscht ist
            dataBaseClient = null;
            return;
        }
        // erstellt den Config Client um die Informationen aus der Config zu handeln
        configClient = new ConfigClient(dataBaseClient);
    }
}
