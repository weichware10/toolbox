package github.weichware10.toolbox.codecharts;

import github.weichware10.toolbox.gui.End;
import github.weichware10.toolbox.gui.dialogs.ErrorDialog;
import github.weichware10.util.Logger;
import github.weichware10.util.ToolType;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.data.TrialData;
import github.weichware10.util.db.DataBaseClient;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * GUI für ZoomMaps Versuche.
 */
public class CodeCharts {

    private final Stage primaryStage;
    private final ConfigClient configClient;
    private final DataBaseClient dataBaseClient;
    private final TrialData trialData;

    /**
     * Startet den CodeCharts-Versuch.
     *
     * @param primaryStage - Hauptfenster
     * @param configClient - configClient für Einstellungen
     * @param dataBaseClient - Verbindung zur Datenbank
     */
    public CodeCharts(Stage primaryStage,
            ConfigClient configClient, DataBaseClient dataBaseClient) {
        this.primaryStage = primaryStage;
        this.configClient = configClient;
        this.dataBaseClient = dataBaseClient;
        this.trialData = new TrialData(
                ToolType.CODECHARTS,
                configClient.getConfig().getTrialId(),
                configClient.getConfig().getConfigId());

        primaryStage.setTitle("Toolbox - CodeCharts Test");
        primaryStage.setMaximized(true);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CodeCharts.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Error when loading main scene", e, true);
            System.exit(-1);
        }

        CodeChartsController controller = loader.getController();
        controller.setCodeCharts(this);

        Scene scene = new Scene(root, 300, 275);
        primaryStage.setScene(scene);

        CodeChartsCoordinator coordinator = null;
        try {
            coordinator = new CodeChartsCoordinator(configClient, dataBaseClient, trialData,
                    controller.getImageView(), controller.getStackPane(), primaryStage, this);
            coordinator.iterate();
        } catch (Exception e) {
            Logger.error("Error while loading image", e, false);
            primaryStage.close();
            new ErrorDialog().showErrorDialog(e);
            Platform.exit();
        }
    }

    /**
     * Beendet den Test und gibt die erhobenen Daten an den Endscreen weiter.
     */
    public void endTest() {
        new End(primaryStage, configClient, dataBaseClient, trialData);
    }
}
