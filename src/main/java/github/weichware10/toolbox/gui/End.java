package github.weichware10.toolbox.gui;

import github.weichware10.util.Logger;
import github.weichware10.util.data.TrialData;
import github.weichware10.util.db.DataBaseClient;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Takes care of the functionality for the EndWindow.
 */
public class End {
    private final Stage primaryStage;
    private final DataBaseClient dataBaseClient;
    @SuppressWarnings("unused")
    private final TrialData trialData;

    /**
     * Erstellt den Abschluss Bildschirm.
     *
     * @param primaryStage - das Hauptfenster
     * @param dataBaseClient - Datenbankverbindung
     * @param trialData - die erhobenen Daten
     */
    public End(Stage primaryStage, DataBaseClient dataBaseClient, TrialData trialData) {
        this.primaryStage = primaryStage;
        this.dataBaseClient = dataBaseClient;
        this.trialData = trialData;

        primaryStage.setTitle("Toolbox - Test finished");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("End.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            Logger.error("Exception when loading end screen", e);
            return;
        }

        EndController controller = loader.getController();
        controller.setEnd(this);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    /**
     * schließt das Programm.
     */
    protected void closeProgramm() {
        primaryStage.close();
    }

    /**
     * Kehrt zum Startbildschirm zurück.
     */
    protected void newTest() {
        new App(primaryStage, dataBaseClient);
    }
}
