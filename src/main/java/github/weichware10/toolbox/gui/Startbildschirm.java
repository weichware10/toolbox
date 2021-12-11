package github.weichware10.toolbox.gui;

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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Kümmert sich um das Design des Startbildschirm.
 */
public class Startbildschirm {
    static Scene toolBoxHome;

    /**
     * Die eigentliche Funktion, die den Startbildschirm darstellt.
     *
     * @param primaryStage - Hauptfenster um Änderungen vorzunehmen
     * @param configClient - Informationen aus der Configfile holen
     */
    public static void display(Stage primaryStage, ConfigClient configClient) {
        primaryStage.setTitle("ToolBox");

        TextField trialIdInput = new TextField();
        trialIdInput.setPromptText("insert TrialID");

        Button startTestButton = new Button("Start");
        startTestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Test started");
            }
        });

        Label trialIdExplenation = new Label("Insert the ID given in the Email");

        VBox layoutTestIdCenterWindow = new VBox(10);
        layoutTestIdCenterWindow.setPadding(new Insets(10, 10, 10, 10));
        layoutTestIdCenterWindow.getChildren().addAll(trialIdExplenation, trialIdInput,
                                                        startTestButton);
        layoutTestIdCenterWindow.setAlignment(Pos.CENTER);

        //Admin Menü
        Menu adminMenu = new Menu("Admin");
        //Items
        MenuItem jsonTest = new MenuItem("JSON Test");
        jsonTest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Datei auswählen setup
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("JSON Config auswählen");
                fileChooser.getExtensionFilters().add(
                    new ExtensionFilter("JSON Dateien", "*.json"));
                //Dateipfad als String speichern
                String location = fileChooser.showOpenDialog(primaryStage).getAbsolutePath();
                configClient.loadFromJson(location);
                boolean test = configClient.getConfig().getToolType().equals(ToolType.ZOOMMAPS);
                System.out.println(test);
            }
        });
        adminMenu.getItems().add(jsonTest);
        //Main Menüleiste
        MenuBar testMenuBar = new MenuBar();
        testMenuBar.getMenus().add(adminMenu);

        //Layout welches alle Teillayouts zusammenfasst
        BorderPane layoutTestIdWindow = new BorderPane();
        layoutTestIdWindow.setTop(testMenuBar);
        layoutTestIdWindow.setCenter(layoutTestIdCenterWindow);

        toolBoxHome = new Scene(layoutTestIdWindow, 400, 400);

        primaryStage.setScene(toolBoxHome);
    }
}
