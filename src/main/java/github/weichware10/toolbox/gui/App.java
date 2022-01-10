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
    private AppController controller;

    /**
     * zeigt den Hauptbildschirm an.
     *
     * @param primaryStage   - Fenster
     * @param dataBaseClient - der DataBaseClient, falls man ihn vom Ende
     *                       wiederverwendet
     */
    public App(Stage primaryStage, DataBaseClient dataBaseClient) {

        primaryStage.setTitle("Toolbox - Start");

        this.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Error when loading main scene", e, true);
            System.exit(-1);
        }

        controller = loader.getController();
        controller.setApp(this);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        if (dataBaseClient == null) {
            resetDataBaseConnection();
        } else {
            this.dataBaseClient = dataBaseClient;
            this.configClient = new ConfigClient(dataBaseClient);
        }
        setPermissionAccess();
    }

    /**
     * lädt Config aus JSON und versucht den Test zu starten.
     *
     * @param warnText - Fehlernachricht
     */
    protected void loadJsonConfig() {

        controller.setWarnText(null);

        // Datei auswählen setup
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("JSON Config auswählen");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("JSON Dateien", "*.json"));

        // Dateipfad als String speichern und json laden
        String location = fileChooser.showOpenDialog(primaryStage).getAbsolutePath();
        if (!configClient.loadFromJson(location)) {
            controller.setWarnText("JSON Config not valid");
            return;
        }

        try {
            new PreTest(primaryStage, configClient, dataBaseClient);
        } catch (Exception e) {
            controller.setWarnText("JSON Config not valid");
            return;
        }
    }

    /**
     * Nimmt eine trialId entgegen und startet den Versuch. Bei Fehlern wird eine
     * Warnung angezeigt.
     *
     * @param trialId - die ID des Versuches
     */
    protected void startTrial(String trialId) {

        controller.setWarnText(null);

        if (dataBaseClient == null) {
            controller.setWarnText("no database connection");
            return;
        }
        if (trialId.length() == 0) {
            controller.setWarnText("Bitte geben sie eine gültige ID ein.");
            return;
        }

        // Verfügbarkeit überprüfen
        boolean available = dataBaseClient.trials.getAvailability(trialId);
        if (!available) {
            controller.setWarnText("Bitte geben sie eine gültige ID ein.");
            return;
        }

        // Config laden
        boolean success = configClient.loadFromDataBase(trialId);
        if (!success) {
            controller.setWarnText("Bitte geben sie eine gültige ID ein.");
            return;
        }

        // Tutorial noch hinzufügen an dieser Stelle
        new PreTest(primaryStage, configClient, dataBaseClient);
    }

    /**
     * Erstellt eine neue ZoomMaps Konfiguration,
     * um die Konfiguration an {@link #createTestTrial(Configuration, TextField)}
     * weiterzugeben.
     *
     * @param trialIdInput - das TextField zum Eintragen der generierten ID
     */
    protected void createZoomMapsTestTrial(TextField trialIdInput) {
        if (dataBaseClient == null) {
            controller.setWarnText("no database connection");
            return;
        }

        ZoomMapsConfiguration zoomMapsConfiguration = new ZoomMapsConfiguration(
                4.2, 600, 600);

        createTestTrial(new Configuration(
                "dunno yet",
                "Von wem werden Froot Loops hergestellt?",
                /*Util.saveImage("https://scotchaddict.com/wp-content/uploads/2014/01/illusion-of-choice.jpg"),*/
                App.class.getClassLoader().getResource("test-image.jpg").toString(),
                "Willkommen zu unserem ZoomMaps Versuch Illusion der Auswahl!",
                "Vielen Dank für die Teilnahme. Sie sind jetzt ein Froot Loops Connoisseur!",
                true,
                zoomMapsConfiguration),
                trialIdInput);
    }

    /**
     * Erstellt eine neue CodeCharts Konfiguration,
     * um die Konfiguration an {@link #createTestTrial(Configuration, TextField)}
     * weiterzugeben.
     *
     * @param trialIdInput - das TextField zum Eintragen der generierten ID
     */
    protected void createCodeChartsTestTrial(TextField trialIdInput) {
        if (dataBaseClient == null) {
            controller.setWarnText("no database connection");
            return;
        }

        CodeChartsConfiguration codeChartsConfiguration = new CodeChartsConfiguration(
            "OBST", new int[]{ 3, 5 }, new long[]{ 300, 500 },
            false, true, true, 5,
            15, -1, -1);

        createTestTrial(new Configuration(
                "dunno yet",
                "Test Question?",
                /*Util.saveImage("https://scotchaddict.com/wp-content/uploads/2014/01/illusion-of-choice.jpg"),*/
                App.class.getClassLoader().getResource("test-image.jpg").toString(),
                "Welcome to this magnificent CodeCharts Trial",
                "Thanks for participating in this extraordinary CodeCharts Trial!",
                true,
                codeChartsConfiguration),
                trialIdInput);
    }

    /**
     * Erstellt einen neuen Versuch
     * und trägt die dazugehörige ID in das Textfeld ein.
     *
     * @param configuration - die zu benutzende configuration
     */
    protected void createTestTrial(Configuration configuration,
            TextField trialIdInput) {
        String configId = dataBaseClient.configurations.set(configuration);
        List<String> trialIds = dataBaseClient.trials.add(configId, 1);
        if (trialIds != null && trialIds.size() > 0) {
            trialIdInput.setText(trialIds.get(0));
        } else {
            controller.setWarnText("Error when creating test trial");
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
        setPermissionAccess();
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
     * Der Erfolg kann daran abgelesen werden, ob {@link #dataBaseClient}
     * {@code null} ist.
     * Der ConfigClient wird im gleichen Zug angepasst.
     */
    protected void resetDataBaseConnection() {
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
            Logger.error("error when loading env", e);
            // auf null setzen, falls die Verbindung vorher angepasst wurde und dies
            // gewünscht ist
            // zurücksetzen auf dataBaseClient-freien Zustand
            dataBaseClient = null;
        }
        // erstellt den Config Client um die Informationen aus der Config zu handeln
        configClient = new ConfigClient(dataBaseClient);
        setPermissionAccess();
    }

    /**
     * (de)aktiviert Elemente, basierend auf den Permissions des Datenbank-Nutzers.
     */
    private void setPermissionAccess() {
        if (dataBaseClient != null) {
            controller.setAdminMenuVisibile(dataBaseClient.permissions.isAdmin);
            controller.setTrialInputDisable(!dataBaseClient.permissions.isSubject);
            return;
        }
        controller.setAdminMenuVisibile(false);
        controller.setTrialInputDisable(false);
    }
}
