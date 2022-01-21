package github.weichware10.toolbox.gui;

import github.weichware10.toolbox.codecharts.CodeCharts;
import github.weichware10.toolbox.zoommaps.ZoomMaps;
import github.weichware10.util.Files;
import github.weichware10.util.Logger;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.db.DataBaseClient;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * GUI für Tutorial.
 */
public class Tutorial {
    private final Stage primaryStage;
    private final ConfigClient configClient;
    private final DataBaseClient dataBaseClient;
    private TutorialController controller;
    private static ArrayList<Image> imageList = new ArrayList<>();
    private final String toolType;

    /**
     * Startet das Tutorial.
     *
     * @param primaryStage - Hauptfenster
     * @param toolType - enthält den Funktions-Typ um das richtige Tutorial zu starten
     * @param configClient - configClient für Einstellungen
     * @param dataBaseClient - Verbindung zur Datenbank
     */
    public Tutorial(Stage primaryStage, String toolType,
                    ConfigClient configClient, DataBaseClient dataBaseClient) {
        this.primaryStage = primaryStage;
        this.toolType = toolType;
        this.configClient = configClient;
        this.dataBaseClient = dataBaseClient;

        primaryStage.setTitle("Tutorial");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Tutorial.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            Logger.error("Error when loading main scene", e, true);
            System.exit(-1);
        }

        controller = loader.getController();
        controller.setTutorial(this);

        //Kümmert sich um das Laden aller Bilder und setzt das erste Bild
        boolean finished = false;
        int index = 0;
        while (!finished) {
            try {
                String imageLoc = Files.saveImage(String.format("https://raw.githubusercontent.com/weichware10/dokumente/main/tutorial/%s/%d.png", toolType, index));
                imageList.add(new Image(Paths.get(imageLoc).toUri().toString()));
            } catch (IllegalArgumentException | IOException e) {
                finished = true;
            }
            index += 1;
        }
        if (imageList.size() > 0) {
            controller.getImageView().setImage(imageList.get(0));
            controller.getProgressText().setText(String.format("1/%d", imageList.size()));
        }

        Scene scene = new Scene(root, 300, 275);
        primaryStage.setScene(scene);
    }

    /**
     * Setzt das neue Bild vom Tutorial.
     *
     * @param tutorialCount - In welchem Schritt vom Tutorial man ist
     * @param nextButton - Weiter Knopf um ihn zu deaktivieren
     * @param backButton - Zurück Knopf um ihn zu deaktivieren
     * @param imageView - Ausschnitt auf den das Bild geladen wird
     * @param progressText - Fortschrittsanzeige
     */
    public static void setImage(int tutorialCount, Button nextButton,
                                Button backButton, ImageView imageView, Text progressText) {
        //setzt die Sichtbarkeit des zurück Button
        if (tutorialCount <= 0) {
            backButton.setDisable(true);
        } else if (tutorialCount > 0) {
            backButton.setDisable(false);
        }
        //setzt die Sichtbarkeit des weiter Button
        if (tutorialCount >= imageList.size() - 1) {
            nextButton.setDisable(true);
        } else if (tutorialCount < imageList.size() - 1) {
            nextButton.setDisable(false);
        }
        //setzt die aktuelle Seite vom Tutorial
        progressText.setText(String.format("%d/%d", tutorialCount + 1, imageList.size()));
        //setzt die nächste Szene vom Tutorial
        if (tutorialCount < imageList.size() && tutorialCount >= 0) {
            imageView.setImage(imageList.get(tutorialCount));
        }
    }

    /**
     * Startet den Test, wenn man auf Test starten drückt.
     */
    public void startTest() {
        if (toolType.equals("zoommaps")) {
            new ZoomMaps(primaryStage, configClient, dataBaseClient);
        } else if (toolType.equals("codecharts")) {
            new CodeCharts(primaryStage, configClient, dataBaseClient);
        }
    }
}
