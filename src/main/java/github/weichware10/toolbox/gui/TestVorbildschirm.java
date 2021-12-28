package github.weichware10.toolbox.gui;

import github.weichware10.toolbox.codecharts.CodeChartBildschirm;
import github.weichware10.toolbox.eyetracking.EyeTrackingBildschirm;
import github.weichware10.toolbox.zoommaps.ZoomMapsBildschirm;
import github.weichware10.util.config.ConfigClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Bildschirm vorm Start des Test.
 */
public class TestVorbildschirm {
    /**
     * Bildschirm vorm Start des Test.
     *
     * @param primaryStage - Hauptfenster
     * @param configClient - configClient für die ID
     */
    public static void display(Stage primaryStage, ConfigClient configClient) {
        String title = null;

        switch (configClient.getConfig().getToolType()) {
            case ZOOMMAPS:
                title = "ZoomMaps Test";
                break;
            case CODECHARTS:
                title = "CodeCharts Test";
                break;
            case EYETRACKING:
                title = "EyeTracking Test";
                break;
            default: // never
                break;
        }

        primaryStage.setTitle(title);

        Button startButton = new Button("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switch (configClient.getConfig().getToolType()) {
                    case ZOOMMAPS:
                        ZoomMapsBildschirm.display(primaryStage, configClient);
                        break;
                    case CODECHARTS:
                        CodeChartBildschirm.display(primaryStage, configClient);
                        break;
                    case EYETRACKING:
                        EyeTrackingBildschirm.display(primaryStage, configClient);
                        break;
                    default: // never
                        break;
                }
            }
        });

        HBox layoutButtons = new HBox(10);
        layoutButtons.setPadding(new Insets(10, 10, 10, 10));
        layoutButtons.getChildren().addAll(startButton);
        layoutButtons.setAlignment(Pos.CENTER);

        Label testMessage = new Label(String.format("Sie können den %s starten", title));

        VBox layoutFinal = new VBox(10);
        layoutFinal.setPadding(new Insets(10, 10, 10, 10));
        layoutFinal.getChildren().addAll(testMessage, layoutButtons);
        layoutFinal.setAlignment(Pos.CENTER);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Scene toolBoxZoomMaps = new Scene(layoutFinal,
                                        screenBounds.getWidth() / 2, screenBounds.getHeight() / 2);

        primaryStage.setScene(toolBoxZoomMaps);
    }
}
