package github.weichware10.toolbox.zoommaps;

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
public class ZoomMaps {

    private final Stage primaryStage;
    private final DataBaseClient dataBaseClient;
    private final TrialData trialData;
    private final ConfigClient configClient;
    ZoomMapsController controller;


    /**
     * Startet den ZoomMaps-Versuch.
     *
     * @param primaryStage - Hauptfenster
     * @param configClient - configClient für Einstellungen
     * @param dataBaseClient - Verbindung zur Datenbank
     */
    public ZoomMaps(Stage primaryStage, ConfigClient configClient, DataBaseClient dataBaseClient) {
        this.primaryStage = primaryStage;
        this.dataBaseClient = dataBaseClient;
        this.configClient = configClient;
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

        controller = loader.getController();
        controller.setZoomMaps(this);

        controller.setImageViewSize(
                configClient.getConfig().getZoomMapsConfiguration().getImageViewWidth(),
                configClient.getConfig().getZoomMapsConfiguration().getImageViewHeight());

        Scene scene = new Scene(root, 300, 275);
        primaryStage.setScene(scene);

        new ZoomCalculator(trialData, configClient, controller);

    }

    /**
     * Beendet den Test und gibt die erhobenen Daten an den Endscreen weiter.
     *
     * @param answer - Antwort auf die Frage
     */
    public void endTest(String answer) {
        if (answer.length() == 0) {
            controller.setWarn("Please provide an answer.");
            return;
        }
        controller.setWarn(null);
        trialData.setAnswer(answer);
        new End(primaryStage, configClient, dataBaseClient, trialData);
    }
}
