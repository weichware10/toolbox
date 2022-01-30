package github.weichware10.toolbox.gui;

import github.weichware10.toolbox.gui.util.Log;
import github.weichware10.util.Logger;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.data.TrialData;
import github.weichware10.util.db.DataBaseClient;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Zuständig für die Funktionalität vom Endfenster.
 */
public class End {
    private final Stage primaryStage;
    private final DataBaseClient dataBaseClient;
    private final TrialData trialData;
    EndController controller;

    /**
     * Erstellt den Abschluss Bildschirm.
     *
     * @param primaryStage   - das Hauptfenster
     * @param dataBaseClient - Datenbankverbindung
     * @param trialData      - die erhobenen Daten
     */
    public End(Stage primaryStage,
            ConfigClient configClient, DataBaseClient dataBaseClient, TrialData trialData) {
        this.primaryStage = primaryStage;
        this.dataBaseClient = dataBaseClient;
        this.trialData = trialData;

        primaryStage.setTitle("Toolbox - Test finished");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("End.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Exception when loading end screen", e);
            return;
        }

        controller = loader.getController();
        controller.setEnd(this, primaryStage);
        controller.setOutro(configClient.getConfig().getOutro());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        if (dataBaseClient.permissions.isAdmin) {
            controller.setStatus(
                    "TrialData will not be saved automatically, do that through the admin menu.",
                    null,
                    null);
        } else if (dataBaseClient.permissions.isSubject) {
            saveTestToDataBase();
        }
        setPermissionAccess();
    }

    protected void saveTestToJson() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("JSON Config auswählen");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("JSON Dateien", "*.json"));

        // Dateipfad als String speichern und json laden
        String location = fileChooser.showSaveDialog(primaryStage).getAbsolutePath();
        controller.setStatus(null, null, null);
        if (location != null) {
            controller.setStatusIndicator(true);
            Thread taskThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (TrialData.toJson(location, trialData)) {
                        Platform.runLater(() -> controller.setStatus(
                                "TrialData unter", location, "gespeichert."));
                    } else {
                        Platform.runLater(() -> controller.setStatus(
                                "Could not save TrialData to JSON", null, null));
                    }
                }
            });
            taskThread.start();
        }
    }

    protected void saveTestToDataBase() {
        controller.setStatusIndicator(true);
        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (dataBaseClient.trials.set(trialData)) {
                    Platform.runLater(() -> controller.setStatus(
                            "successfully saved TrialData to DataBase.", null, null));
                } else {
                    Platform.runLater(() -> controller.setStatus(
                            "Could not save TrialData to DataBase.", null, null));
                }
            }
        });
        taskThread.start();
    }

    /**
     * schließt das Programm.
     */
    protected void closeProgramm() {
        primaryStage.close();
        Log.close();
    }

    /**
     * Kehrt zum Startbildschirm zurück.
     */
    protected void newTest() {
        new App(primaryStage, dataBaseClient);
    }

    /**
     * (de)aktiviert Elemente, basierend auf den Permissions des Datenbank-Nutzers.
     */
    private void setPermissionAccess() {
        if (dataBaseClient != null) {
            controller.setAdminMenuVisibile(dataBaseClient.permissions.isAdmin);
            return;
        }
        controller.setAdminMenuVisibile(false);
    }
}
