package github.weichware10.toolbox.gui;

import github.weichware10.toolbox.codecharts.CodeCharts;
import github.weichware10.toolbox.gui.util.WindowCloser;
import github.weichware10.toolbox.zoommaps.ZoomMaps;
import github.weichware10.util.Logger;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.db.DataBaseClient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Zuständig für die Funktionalität vom PreTestWindow.
 */
public class PreTest {

    private final Stage primaryStage;
    private final ConfigClient configClient;
    private final DataBaseClient dataBaseClient;

    /**
     * zeigt das PreTestWindow.
     *
     * @param primaryStage - primäres Fenster
     */
    public PreTest(Stage primaryStage,
            ConfigClient configClient, DataBaseClient dataBaseClient) {
        this.primaryStage = primaryStage;
        this.configClient = configClient;
        this.dataBaseClient = dataBaseClient;

        String toolType = "";
        switch (configClient.getConfig().getToolType()) {
            case ZOOMMAPS:
                toolType = "ZoomMaps";
                break;
            case CODECHARTS:
                toolType = "CodeCharts";
                break;
            default: // never
                break;
        }

        primaryStage.setTitle(String.format("Toolbox - %s Test starting", toolType));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PreTest.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Error when loading pretest scene", e, true);
            return;
        }

        PreTestController controller = loader.getController();
        controller.setPreTest(this);
        controller.setPrompt(configClient.getConfig().getIntro());

        Scene scene = new Scene(root);

        // Event welches beim schließen eines Fensters aufgerufen wird
        primaryStage.setOnCloseRequest(e -> WindowCloser.closeRequestFilter(e));
        primaryStage.setScene(scene);
    }

    /**
     * startet den Test.
     */
    protected void startTest() {
        switch (configClient.getConfig().getToolType()) {
            case CODECHARTS:
                new CodeCharts(primaryStage, configClient, dataBaseClient);
                break;
            case ZOOMMAPS:
                new ZoomMaps(primaryStage, configClient, dataBaseClient);
                break;
            default: // never
                break;
        }
    }
}
