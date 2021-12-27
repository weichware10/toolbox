package github.weichware10.toolbox.gui;

import github.weichware10.toolbox.codecharts.CodeChartBildschirm;
import github.weichware10.toolbox.eyetracking.EyeTrackingBildschirm;
import github.weichware10.toolbox.zoommaps.ZoomMapsBildschirm;
import github.weichware10.util.Enums.ToolType;
import github.weichware10.util.config.ConfigClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class TestVorbildschirm {

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
                Endbildschirm.display(primaryStage, configClient);
            }
        });

        Button endButton = new Button("Abschließen");
        endButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Endbildschirm.display(primaryStage, configClient);
            }
        });

        HBox layoutButtons = new HBox(10);
        layoutButtons.setPadding(new Insets(10, 10, 10, 10));
        layoutButtons.getChildren().addAll(startButton, endButton);
        layoutButtons.setAlignment(Pos.CENTER);

        Label testMessage = new Label(String.format("Sie können den %s starten", title));

        VBox layoutFinal = new VBox(10);
        layoutFinal.setPadding(new Insets(10, 10, 10, 10));
        layoutFinal.getChildren().addAll(testMessage, layoutButtons);
        layoutFinal.setAlignment(Pos.CENTER);

        Scene toolBoxZoomMaps = new Scene(layoutFinal, 400, 400);

        primaryStage.setScene(toolBoxZoomMaps);
    }
}
