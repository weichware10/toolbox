package github.weichware10.toolbox.codecharts;

import github.weichware10.toolbox.Util;
import github.weichware10.util.config.CodeChartsConfiguration;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.data.TrialData;
import github.weichware10.util.db.DataBaseClient;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Steuert den Datenverkehr und gibt die Befehle zum anzeigen.
 * von Bild Raster und Eingabefenster.
 */
public class CodeChartsCoordinator {
    private final TrialData trialData;
    private final ImageView imageView;
    private final CodeChartsPane rootPane;

    private final List<String> usableStrings;
    private final List<String> currentStrings;

    private final long[] timings;
    private final boolean relativeSize;
    private final boolean randomized;
    private final int maxDepth;
    private final int iterations;

    /**
     * Konstruktor für den CodeChartsCoordinator.
     *
     * @param configClient - aus dem die Configuration geladen wird
     * @param dataBaseClient -
     * @throws IOException
     * @throws FileNotFoundException
     * @throws IllegalArgumentException
     * @throws MalformedURLException
     */
    public CodeChartsCoordinator(ConfigClient configClient, DataBaseClient dataBaseClient,
            TrialData trialData, ImageView imageView, StackPane stackPane)
            throws MalformedURLException, IllegalArgumentException,
            FileNotFoundException, IOException {
        this.trialData = trialData;
        this.imageView = imageView;

        // BILD SETZEN
        String imageUrl = Util.saveImage(configClient.getConfig().getImageUrl());
        imageView.setImage(new Image(imageUrl));
        imageView.setVisible(false);

        // Konfiguration abspeichern
        CodeChartsConfiguration ccConfig = configClient.getConfig().getCodeChartsConfiguration();
        iterations = ccConfig.getInterations();
        maxDepth = ccConfig.getMaxDepth();
        randomized = ccConfig.getRandomized();
        relativeSize = ccConfig.getRandomized();
        timings = ccConfig.getTimings();
        // String-Listen initialisieren
        usableStrings = dataBaseClient.strings.get(ccConfig.getStringId());
        currentStrings = new ArrayList<>();

        // Zukünftige Unterteilungen setzen (könnte auch null sein)
        CodeChartsPane.defaultHorizontal = ccConfig.getDefaultHorizontal();
        CodeChartsPane.defaultVertical = ccConfig.getDefaultVertical();
        CodeChartsPane.showGrid = ccConfig.getShowGrid();

        int horizontal = ccConfig.getInitialSize()[0];
        int vertical = ccConfig.getInitialSize()[1];

        // größ herausfinden (gleich wie Bild)
        double ratio = imageView.getImage().getWidth() / imageView.getImage().getHeight();
        double width = Math.min(imageView.getFitWidth(), imageView.getFitHeight() * ratio);
        double height = Math.min(imageView.getFitHeight(), imageView.getFitWidth() / ratio);

        // ROOT PANE
        rootPane = new CodeChartsPane(new ArrayList<>(), -1, -1, width, height);
        rootPane.subdivide(horizontal, vertical);
        rootPane.setVisible(false);
        stackPane.getChildren().add(rootPane);
    }

    public void startTest() {
        ;
    }
}
