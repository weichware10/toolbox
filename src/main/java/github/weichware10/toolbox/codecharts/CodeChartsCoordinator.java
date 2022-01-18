package github.weichware10.toolbox.codecharts;

import github.weichware10.util.Files;
import github.weichware10.util.Logger;
import github.weichware10.util.config.CodeChartsConfiguration;
import github.weichware10.util.config.ConfigClient;
import github.weichware10.util.data.TrialData;
import github.weichware10.util.db.DataBaseClient;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Steuert den Datenverkehr und gibt die Befehle zum Anzeigen.
 * von Bild Raster und Eingabefenster.
 */
public class CodeChartsCoordinator {
    private final TrialData trialData;
    private final ImageView imageView;
    private final Rectangle2D imageViewPort;
    private final CodeChartsPane rootPane;
    private final CodeChartsInput codeChartsInput;
    private final CodeCharts codeCharts;

    private final List<String> usableStrings;
    private final List<String> currentStrings;

    private final long[] timings;
    private final boolean relativeSize;
    private final int maxDepth;
    private final int iterations;
    private int currentIteration = 1;

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
            TrialData trialData, ImageView imageView, StackPane stackPane, Stage primaryStage,
            CodeCharts codeCharts) throws MalformedURLException, IllegalArgumentException,
            FileNotFoundException, IOException {
        this.trialData = trialData;
        this.imageView = imageView;
        this.codeCharts = codeCharts;
        codeChartsInput = new CodeChartsInput();
        // BILD SETZEN
        String imageUrl = Files.saveImage(configClient.getConfig().getImageUrl());
        Image image = new Image(Paths.get(imageUrl).toUri().toString());
        imageViewPort = new Rectangle2D(0, 0, image.getWidth(), image.getHeight());
        imageView.setImage(image);
        imageView.setVisible(false);

        // Konfiguration abspeichern
        CodeChartsConfiguration ccConfig = configClient.getConfig().getCodeChartsConfiguration();
        iterations = ccConfig.getIterations();
        maxDepth = ccConfig.getMaxDepth();
        relativeSize = ccConfig.getRandomized();
        timings = ccConfig.getTimings();

        // String-Listen initialisieren
        usableStrings = ccConfig.getStrings();
        if (usableStrings == null) {
            throw new IllegalArgumentException("no strings provided!");
        }
        int neededStrings = calculateMinStringAmount(ccConfig.getInitialSize()[0],
                ccConfig.getInitialSize()[1], ccConfig.getDefaultHorizontal(),
                ccConfig.getDefaultVertical(), iterations);
        if (neededStrings > usableStrings.size()) {
            String message = String.format("not enough strings provided! needs %d but only has %d",
                    neededStrings, usableStrings.size());
            throw new IllegalArgumentException(message);
        }
        currentStrings = new ArrayList<>();
        if (ccConfig.getRandomized()) {
            Collections.shuffle(usableStrings);
        }

        // Zukünftige Unterteilungen setzen (könnte auch null sein)
        CodeChartsPane.defaultHorizontal = ccConfig.getDefaultHorizontal();
        CodeChartsPane.defaultVertical = ccConfig.getDefaultVertical();
        CodeChartsPane.showGrid = ccConfig.getShowGrid();

        int horizontal = ccConfig.getInitialSize()[0];
        int vertical = ccConfig.getInitialSize()[1];

        // Größe herausfinden (gleich wie Bild)
        double ratio = imageView.getImage().getWidth() / imageView.getImage().getHeight();
        double width = Math.min(imageView.getFitWidth(), imageView.getFitHeight() * ratio);
        double height = Math.min(imageView.getFitHeight(), imageView.getFitWidth() / ratio);

        // ROOT PANE
        rootPane = new CodeChartsPane(null, -1, -1, width, height);
        rootPane.subdivide(horizontal, vertical);
        rootPane.philLeaves(usableStrings, currentStrings);
        rootPane.setVisible(false);
        stackPane.getChildren().add(rootPane);
    }

    /**
     * Durchführen einer CodeCharts Iteration.
     */
    public void iterate() {


        Task<Void> iteration = new Task<>() {

            @Override
            protected Void call() throws Exception {
                try {
                    Platform.runLater(() -> imageView.setVisible(true));
                    Thread.sleep(timings[0]);
                    Platform.runLater(() -> imageView.setVisible(false));
                    Platform.runLater(() -> rootPane.setVisible(true));
                    Thread.sleep(timings[1]);
                    Platform.runLater(() -> rootPane.setVisible(false));
                } catch (InterruptedException e) {
                    Logger.error("Error while doing a CodeCharts iteration", e, true);
                }
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                String input = codeChartsInput.getInput(
                        currentStrings, currentIteration, iterations);
                if (input != null) {
                    CodeChartsPane currentPane = rootPane.getLeaf(input);
                    trialData.addDataPoint(
                            toImageViewport(currentPane.viewport), currentPane.depth);
                    currentStrings.remove(input);
                    if (currentPane.depth < maxDepth && relativeSize) {
                        currentPane.subdivide();
                        currentPane.philLeaves(usableStrings, currentStrings);
                    } else {
                        String newString = usableStrings.remove(0);
                        currentStrings.add(newString);
                        currentPane.setContent(newString);
                    }
                    currentIteration += 1;
                }
                // eine weitere Iteration starten
                if (currentIteration <= iterations) {
                    iterate();
                } else {
                    codeCharts.endTest();
                }
            }
        };
        new Thread(iteration).start();
    }

    private int calculateMinStringAmount(int horizontal, int vertical,
            int defaultHorizontal, int defaultVertical, int iterations) {
        int initialPanes = horizontal * vertical;
        int divisionResult = defaultHorizontal * defaultVertical;
        if (defaultHorizontal < 0 || defaultVertical < 0) {
            divisionResult = 2;
        }
        // initiale Anzahl * im Verlauf des Versuchs durchgeführte Unterteilungen
        return initialPanes + iterations * divisionResult;
    }

    private Rectangle2D toImageViewport(Rectangle2D viewport) {
        // universale Vorgehensweise: Verhältnis berechnen, auf Bild-Viewport anwenden
        // minX und minY um minX bzw minY des Bild-Viewports verschieben
        double minX = (viewport.getMinX() / rootPane.getPrefWidth())
                * imageViewPort.getWidth() + imageViewPort.getMinX();
        double minY = (viewport.getMinY() / rootPane.getPrefHeight())
                * imageViewPort.getHeight() + imageViewPort.getMinY();
        double width = (viewport.getWidth() / rootPane.getPrefWidth())
                * imageViewPort.getWidth();
        double height = (viewport.getHeight() / rootPane.getPrefHeight())
                * imageViewPort.getHeight();
        return new Rectangle2D(minX, minY, width, height);
    }
}
