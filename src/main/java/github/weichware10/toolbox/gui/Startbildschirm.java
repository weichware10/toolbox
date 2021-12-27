package github.weichware10.toolbox.gui;

import github.weichware10.toolbox.codecharts.CodeChartBildschirm;
import github.weichware10.toolbox.eyetracking.EyeTrackingBildschirm;
import github.weichware10.toolbox.zoommaps.ZoomMapsBildschirm;
import github.weichware10.util.Enums.ToolType;
import github.weichware10.util.Logger;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.db.DataBaseClient;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Kümmert sich um das Design des Startbildschirm.
 */
public class Startbildschirm {
    static ConfigClient configClient;
    static DataBaseClient dataBaseClient;
    static Scene toolBoxHome;

    /**
     * Die eigentliche Funktion, die den Startbildschirm darstellt.
     *
     * @param primaryStage - Hauptfenster um Änderungen vorzunehmen
     */
    public static void display(Stage primaryStage) {

        resetDataBaseConnection();

        Label warningMessage = new Label("Bitte geben sie eine gültige ID ein.");
        warningMessage.setTextFill(Color.web("#da3633"));
        warningMessage.setVisible(false);
        primaryStage.setTitle("ToolBox");

        TextField trialIdInput = new TextField();
        trialIdInput.setPromptText("insert TrialID");

        Button startTestButton = new Button("Start");
        startTestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Verfügbarkeit überprüfen
                boolean available = dataBaseClient.trials.getAvailability(trialIdInput.getText());
                if (!available) {
                    warningMessage.setVisible(true);
                    return;
                }

                // Config laden
                boolean success = configClient.loadFromDataBase(trialIdInput.getText());
                if (!success) {
                    warningMessage.setVisible(true);
                    return;
                }

                // Tutorial noch hinzufügen an dieser Stelle
                TestVorbildschirm.display(primaryStage, configClient);
            }
        });

        Label trialIdExplenation = new Label("Insert the ID given in the Email");

        VBox layoutTestIdCenterWindow = new VBox(10);
        layoutTestIdCenterWindow.setPadding(new Insets(10, 10, 10, 10));
        layoutTestIdCenterWindow.getChildren().addAll(trialIdExplenation, trialIdInput,
                warningMessage,
                startTestButton);
        layoutTestIdCenterWindow.setAlignment(Pos.CENTER);

        // Admin Menü
        Menu adminMenu = new Menu("Admin");
        // Items
        MenuItem jsonTest = new MenuItem("JSON Test");
        MenuItem dataBaseChange = new MenuItem("change database connection");
        MenuItem dataBaseReset = new MenuItem("reset database connection");
        adminMenu.getItems().addAll(jsonTest, dataBaseChange, dataBaseReset);

        jsonTest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Datei auswählen setup
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("JSON Config auswählen");
                fileChooser.getExtensionFilters().add(
                        new ExtensionFilter("JSON Dateien", "*.json"));

                // Dateipfad als String speichern und json laden
                String location = fileChooser.showOpenDialog(primaryStage).getAbsolutePath();
                configClient.loadFromJson(location);

                if (configClient.getConfig().getToolType().equals(ToolType.ZOOMMAPS)) {
                    ZoomMapsBildschirm.display(primaryStage, configClient);
                }
                if (configClient.getConfig().getToolType().equals(ToolType.CODECHARTS)) {
                    CodeChartBildschirm.display(primaryStage, configClient);
                }
                if (configClient.getConfig().getToolType().equals(ToolType.EYETRACKING)) {
                    EyeTrackingBildschirm.display(primaryStage, configClient);
                }
            }
        });

        // change DB access
        dataBaseChange.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dialog<Void> dialog = new Dialog<>();
                dialog.setTitle("DataBase connection change");

                // Set the button types.
                ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

                GridPane gridPane = new GridPane();
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                ColumnConstraints column1 = new ColumnConstraints(100);
                gridPane.getColumnConstraints().add(column1);
                ColumnConstraints column2 = new ColumnConstraints(300);
                gridPane.getColumnConstraints().add(column2);

                // fields
                TextField url = new TextField();
                url.setPromptText("url");
                TextField username = new TextField();
                username.setPromptText("username");
                PasswordField password = new PasswordField();
                password.setPromptText("password");
                TextField schema = new TextField();
                schema.setPromptText("schema");

                // labels
                Label label = new Label("Please insert the login data...");
                label.setStyle("-fx-font-weight: bold");
                Label warning = new Label();
                warning.setTextFill(Color.web("#da3633"));
                // text benutzt um wrappingWidthProperty zu benutzen
                ScrollPane errorMessage = new ScrollPane();
                errorMessage.setStyle("-fx-font-family: 'monospaced';");
                errorMessage.setVisible(false);

                gridPane.add(label, 0, 0, 2, 1);
                gridPane.add(new Label("URL"), 0, 1);
                gridPane.add(url, 1, 1);
                gridPane.add(new Label("Username"), 0, 2);
                gridPane.add(username, 1, 2);
                gridPane.add(new Label("Password"), 0, 3);
                gridPane.add(password, 1, 3);
                gridPane.add(new Label("Schema"), 0, 4);
                gridPane.add(schema, 1, 4);
                gridPane.add(warning, 0, 5, 2, 1);
                gridPane.add(errorMessage, 0, 6, 2, 1);

                dialog.getDialogPane().setContent(gridPane);

                // Request focus on the username field by default.
                Platform.runLater(() -> url.requestFocus());

                // nur bei Erfolg Dialog verlassen
                final Button ok = (Button) dialog.getDialogPane().lookupButton(okButtonType);
                ok.addEventFilter(ActionEvent.ACTION, okEvent -> {
                    try {
                        dataBaseClient = new DataBaseClient(
                                url.getText(),
                                username.getText(),
                                password.getText(),
                                schema.getText());
                        dialog.close();
                    } catch (IllegalArgumentException e) {
                        Logger.info("Error while changing database connection");
                        warning.setText("Your input is not valid:");
                        Text errorContent = new Text(e.getMessage());
                        errorContent.wrappingWidthProperty().set(gridPane.getWidth() / 2);
                        errorMessage.setContent(errorContent);
                        errorMessage.setVisible(true);
                        dialog.getDialogPane().setMinHeight(gridPane.getHeight());
                        okEvent.consume();
                    }
                    // erstellt den Config Client um die Informationen aus der Config zu handeln
                    configClient = new ConfigClient(dataBaseClient);
                });

                dialog.show();
            }
        });

        // reset it
        dataBaseReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetDataBaseConnection();
            }
        });

        // Main Menüleiste
        MenuBar testMenuBar = new MenuBar();
        testMenuBar.getMenus().add(adminMenu);

        // Layout welches alle Teillayouts zusammenfasst
        BorderPane layoutTestIdWindow = new BorderPane();
        layoutTestIdWindow.setTop(testMenuBar);
        layoutTestIdWindow.setCenter(layoutTestIdCenterWindow);

        toolBoxHome = new Scene(layoutTestIdWindow, 400, 400);

        primaryStage.setScene(toolBoxHome);
    }

    private static void resetDataBaseConnection() {
        // erstellt die Datenbankverbindung
        Dotenv dotenv = Dotenv.load();
        dataBaseClient = new DataBaseClient(
                dotenv.get("DB_URL"),
                dotenv.get("DB_USERNAME"),
                dotenv.get("DB_PASSWORD"),
                dotenv.get("DB_SCHEMA"));
        // erstellt den Config Client um die Informationen aus der Config zu handeln
        configClient = new ConfigClient(dataBaseClient);
    }
}
