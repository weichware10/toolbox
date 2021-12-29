package github.weichware10.toolbox.gui;

import github.weichware10.toolbox.codecharts.CodeCharts;
import github.weichware10.toolbox.zoommaps.ZoomMaps;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.db.DataBaseClient;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Takes care of the functionality of PreTestWindow.
 */
public class PreTest {

    private final Stage primaryStage;
    private final ConfigClient configClient;
    private final DataBaseClient dataBaseClient;

    /**
     * This function shows the PreTestWindow.
     *
     * @param primaryStage - primary Window to do changes on.
     */
    public PreTest(Stage primaryStage,
            ConfigClient configClient, DataBaseClient dataBaseClient) {
        this.primaryStage = primaryStage;
        this.configClient = configClient;
        this.dataBaseClient = dataBaseClient;

        primaryStage.setTitle("Test starting");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PreTest.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            return;
        }

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

        PreTestController controller = loader.getController();
        controller.setPreTestWindow(this);
        controller.setPrompt(String.format("Sie können den %s Test jetzt starten.", toolType));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
    }

    /**
     * startet den Test.
     */
    protected void startTest() {
        switch (configClient.getConfig().getToolType()) {
            case ZOOMMAPS:
                new ZoomMaps(primaryStage, configClient, dataBaseClient);
                break;
            case CODECHARTS:
                new CodeCharts(primaryStage, configClient, dataBaseClient);
                break;
            default: // never
                break;
        }
    }
}
