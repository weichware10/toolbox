package github.weichware10.toolbox.codecharts;

import github.weichware10.util.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Container für das CodeChartsRaster.
 */
public class CodeChartsPane extends Pane {
    private boolean root = false;
    private boolean leaf = true;
    private List<int[]> globalId = new ArrayList<>();
    private int[] localId;
    private List<List<CodeChartsPane>> childPanes = new ArrayList<>();

    /**
     * Erstellt eine neue CodeChartsPane.
     *
     * @param parentIds -
     * @param hoId -
     * @param veId -
     * @param width -
     * @param height -
     */
    public CodeChartsPane(List<int[]> parentIds,
            int hoId, int veId,
            double width, double height) {
        super();
        if (hoId < 0 || veId < 0) {
            localId = new int[] { -1, -1 };
            root = true;
        } else {
            localId = new int[] { hoId, veId };
            globalId.addAll(parentIds);
            globalId.add(localId);
        }

        // sizing
        setPrefSize(width, height);
        setMinSize(width, height);
        setMaxSize(width, height);

        // debug
        Logger.debug(String.format("created %s", toString()));
        enableDebugStyle();
        setDebugSplitting(true);
    }

    /**
     * subdivides the pane.
     *
     * @param horizontal -
     * @param vertical -
     */
    public void subdivide(int horizontal, int vertical) {

        leaf = false;

        // Größe berechnen
        double paneWidth = getPrefWidth() / horizontal;
        double paneHeight = getPrefHeight() / vertical;
        Logger.debug(String.format("subdividing {w:%f,h:%f}", paneWidth, paneHeight));

        // sorgt für Spalten
        HBox horizontalBox = new HBox();
        for (int hoId = 0; hoId < horizontal; hoId++) {

            // Liste für Spalteninhalt erstellen
            childPanes.add(new ArrayList<>());

            // Spalte veId, beinhaltet Zellen
            VBox verticalBox = new VBox();
            for (int veId = 0; veId < vertical; veId++) {

                // CodeChartsPane erstellen
                CodeChartsPane ccPane = new CodeChartsPane(
                        globalId, hoId, veId, paneWidth, paneHeight);
                // zu Spalte hinzufügen
                verticalBox.getChildren().add(ccPane);
                // zu Spaltenliste hinzufügen
                childPanes.get(hoId).add(ccPane);
            }
            // Spalte veId in HBox hinzufügen
            horizontalBox.getChildren().add(verticalBox);
        }

        // Gesamtkonstrukt hinzufügen
        getChildren().clear();
        getChildren().add(horizontalBox);
        setDebugSplitting(false);
    }

    /**
     * findet das Kind mit der globalId childId.
     *
     * @param childId - globalId
     * @return - Child, falls gefunden (sonst {@code null})
     */
    public CodeChartsPane getChild(List<int[]> childId) {

        if (childId.size() == 1) {
            if (Arrays.equals(childId.get(0), localId)) {
                return this;
            } else {
                return null;
            }
        }

        // Falls leaf aber Such-ID-Listengröße noch zu groß
        if (leaf) {
            return null;
        }

        // zu durchsuchende Liste beinhaltet erste ID nicht mehr (außer bei root)
        List<int[]> queryList = root ? childId : childId.subList(1, childId.size());

        // alle Kinder durchsuchen
        // Spalten
        for (List<CodeChartsPane> column : childPanes) {

            // Zeilen
            for (CodeChartsPane ccPane : column) {

                // rekursiver Aufruf
                CodeChartsPane child = ccPane.getChild(queryList);

                // falls gefunden: Suche beendet
                if (child != null) {
                    return child;
                }
            }
        }

        // nichts gefunden
        return null;
    }

    public int[] getLocalId() {
        return localId;
    }

    public void setContent(String content) {
        getChildren().clear();
        getChildren().add(new Label("content"));
    }


    @SuppressWarnings("unused")
    private void enableDebugStyle() {
        Color color = Color.color(Math.random(), Math.random(), Math.random());
        setBackground(new Background(new BackgroundFill(color, null, null)));
        setOpacity(0.7);
        setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setOnMouseEntered(leaf
                ? e -> setBackground(
                        new Background(new BackgroundFill(color.brighter(), null, null)))
                : e -> {});
        setOnMouseExited(leaf
                ? e -> setBackground(
                        new Background(new BackgroundFill(color, null, null)))
                : e -> {});
    }

    @SuppressWarnings("unused")
    private void setDebugSplitting(boolean value) {
        setOnMouseClicked(value ? e -> subdivide(2, 2) : e -> {});
    }

    @Override
    public String toString() {
        final StringBuilder idSb = new StringBuilder("[]");
        globalId.forEach(i -> idSb.insert(idSb.length() - 1, Arrays.toString(i)));
        return String.format("codeChartsPane%s {w:%d,h:%d}",
                idSb.toString(), (int) getPrefWidth(), (int) getPrefHeight());
    }
}
