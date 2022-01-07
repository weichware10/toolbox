package github.weichware10.toolbox.codecharts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import github.weichware10.util.Logger;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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

        // Größe berechnen
        double paneWidth = getPrefWidth() / horizontal;
        double paneHeight = getPrefHeight() / vertical;
        Logger.debug(String.format("subdividing {w:%f,h:%f}", paneWidth, paneHeight));

        HBox horizontalBox = new HBox(); // sorgt für Spalten
        for (int hoId = 0; hoId < horizontal; hoId++) {
            childPanes.add(new ArrayList<>());
            VBox verticalBox = new VBox(); // Spalte i, beinhaltet Zellen
            for (int veId = 0; veId < vertical; veId++) {
                CodeChartsPane ccPane = new CodeChartsPane(globalId, hoId, veId, paneWidth, paneHeight);
                verticalBox.getChildren().add(ccPane);
                childPanes.get(hoId).add(ccPane);
            }
            horizontalBox.getChildren().add(verticalBox);
        }
        getChildren().clear();
        getChildren().add(horizontalBox);
        setDebugSplitting(false);
    }

    public CodeChartsPane getChild(List<int[]> childId) {

        if (childId.size() == 1) {
            if (Arrays.equals(childId.get(0), localId)) {
                return this;
            }
        }

        for (List<CodeChartsPane> column : childPanes) {
            for (CodeChartsPane ccPane : column) {
                CodeChartsPane child = ccPane.getChild(root ? childId : childId.subList(1, childId.size()));
                if (child != null) {
                    return child;
                }
            }
        }

        return null;
    }

    public int[] getLocalId() {
        return localId;
    }

    public void setContent(String content) {
        getChildren().clear();
        getChildren().add(new Label("content"));
    }


    private void enableDebugStyle() {
        Color color = Color.color(Math.random(), Math.random(), Math.random());
        setBackground(new Background(new BackgroundFill(color, null, null)));
        setOpacity(0.8);
        setOnMouseEntered(leaf
                ? e -> setBackground(
                        new Background(new BackgroundFill(color.brighter(), null, null)))
                : e -> {
                });
        setOnMouseExited(leaf
                ? e -> setBackground(
                        new Background(new BackgroundFill(color, null, null)))
                : e -> {
                });
    }

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
