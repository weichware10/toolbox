package github.weichware10.toolbox.eyetracking;

import github.weichware10.toolbox.gui.End;
import github.weichware10.util.Logger;
import github.weichware10.util.ToolType;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.data.TrialData;
import github.weichware10.util.db.DataBaseClient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * GUI für ZoomMaps Versuche.
 */
public class EyeTracking {

    private final Stage primaryStage;
    private final ConfigClient configClient;
    private final DataBaseClient dataBaseClient;
    private final TrialData trialData;


    /**
     * Startet den CodeCharts-Versuch.
     *
     * @param primaryStage - hauptfenster
     * @param configClient - configClient für Einstellungen
     * @param dataBaseClient - Verbindung zur Datenbank
     */
    public EyeTracking(Stage primaryStage,
            ConfigClient configClient, DataBaseClient dataBaseClient) {
        this.primaryStage = primaryStage;
        this.configClient = configClient;
        this.dataBaseClient = dataBaseClient;
        this.trialData = new TrialData(
                ToolType.EYETRACKING,
                configClient.getConfig().getTrialId(),
                configClient.getConfig().getConfigId());

        primaryStage.setTitle("Toolbox - EyeTracking Test");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EyeTracking.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Error when loading main scene", e, true);
            System.exit(-1);
        }

        EyeTrackingController controller = loader.getController();
        controller.setEyeTracking(this);

        Scene scene = new Scene(root, 300, 275);
        primaryStage.setScene(scene);
    }

    /**
     * Beendet den Test und gibt die erhobenen Daten an den Endscreen weiter.
     */
    public void endTest() {
        new End(primaryStage, configClient, dataBaseClient, trialData);
    }
}
