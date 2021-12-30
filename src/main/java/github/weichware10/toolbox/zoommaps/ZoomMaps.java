package github.weichware10.toolbox.zoommaps;

import github.weichware10.toolbox.gui.End;
import github.weichware10.util.Enums.ToolType;
import github.weichware10.util.Logger;
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
public class ZoomMaps {

    private final Stage primaryStage;
    @SuppressWarnings("unused")
    private final ConfigClient configClient;
    private final DataBaseClient dataBaseClient;
    private final TrialData trialData;


    /**
     * Startet den ZoomMaps-Versuch.
     *
     * @param primaryStage - Hauptfenster
     * @param configClient - configClient für Einstellungen
     * @param dataBaseClient - Verbindung zur Datenbank
     */
    public ZoomMaps(Stage primaryStage, ConfigClient configClient, DataBaseClient dataBaseClient) {
        this.primaryStage = primaryStage;
        this.configClient = configClient;
        this.dataBaseClient = dataBaseClient;
        this.trialData = new TrialData(
                ToolType.ZOOMMAPS,
                configClient.getConfig().getTrialId(),
                configClient.getConfig().getConfigId());

        primaryStage.setTitle("Toolbox - ZoomMaps Test");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ZoomMaps.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Error when loading main scene", e, true);
            System.exit(-1);
        }

        ZoomMapsController controller = loader.getController();
        controller.setZoomMaps(this);

        Scene scene = new Scene(root, 300, 275);
        primaryStage.setScene(scene);
    }

    /**
     * Beendet den Test und gibt die erhobenen Daten an den Endscreen weiter.
     */
    public void endTest() {
        new End(primaryStage, dataBaseClient, trialData);
    }
}
