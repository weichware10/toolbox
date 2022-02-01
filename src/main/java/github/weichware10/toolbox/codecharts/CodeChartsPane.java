package github.weichware10.toolbox.codecharts;

import github.weichware10.util.Logger;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Container für das CodeChartsRaster.
 */
public class CodeChartsPane extends AnchorPane {
    private final List<CodeChartsPane> childPanes = new ArrayList<>();
    public final Rectangle2D viewport;
    public final int depth;

    private String content = null;

    public static Integer defaultHorizontal = -1;
    public static Integer defaultVertical = -1;
    public static boolean showGrid = false;
    public static boolean DEBUG = false;

    /**
     * Erstellt eine neue CodeChartsPane.
     *
     * @param parent    - das Elter
     * @param hoId      - horizontale ID
     * @param veId      - vertikale ID
     * @param width     - Breite
     * @param height    - Höhe
     */
    public CodeChartsPane(CodeChartsPane parent, int hoId, int veId, double width, double height) {
        // AnchorPane Constructor
        super();

        // root
        if (parent == null) {
            viewport = new Rectangle2D(0, 0, width, height);
            depth = 0;
            // kein root
        } else {
            viewport = new Rectangle2D(
                    parent.viewport.getMinX() + (hoId * width),
                    parent.viewport.getMinY() + (veId * height),
                    width,
                    height);
            depth = parent.depth + 1;
        }

        // Größe
        setPrefSize(width, height);
        setMinSize(width, height);
        setMaxSize(width, height);

        if (showGrid) {
            setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }

        if (DEBUG) {
            enableDebugStyle();
            setDebugSplitting(true);
        }
    }

    /**
     * Unterteilt das CodeCharts-Pane mit den default-Maßen.
     */
    public void subdivide() {
        subdivide(defaultHorizontal, defaultVertical);
    }

    /**
     * Unterteilt das CodeCharts-Pane mit selbst gewählten Maßen.
     * Wird mindestens 1 Wert <= 0 gewählt, wird eine dynamische Unterteilung durchgeführt.
     *
     * @param horizontal - Anzahl der Horizontalen Unterteilungen
     * @param vertical   - Anzahl der Vertikalen Unterteilungen
     */
    public void subdivide(int horizontal, int vertical) {

        if (horizontal < 0 || vertical < 0) {
            if (getPrefWidth() < getPrefHeight()) {
                horizontal = 1;
                vertical = 2;
            } else {
                horizontal = 2;
                vertical = 1;
            }
        }

        // Größe berechnen
        double paneWidth = getPrefWidth() / horizontal;
        double paneHeight = getPrefHeight() / vertical;
        Logger.info(String.format("subdividing CodeChartsPane {w:%f,h:%f}", paneWidth, paneHeight));

        // sorgt für Spalten
        HBox horizontalBox = new HBox();
        for (int hoId = 0; hoId < horizontal; hoId++) {

            // Spalte veId, beinhaltet Zellen
            VBox verticalBox = new VBox();
            for (int veId = 0; veId < vertical; veId++) {

                // CodeChartsPane erstellen
                CodeChartsPane ccPane = new CodeChartsPane(
                        this, hoId, veId, paneWidth, paneHeight);
                // zu Spalte hinzufügen
                verticalBox.getChildren().add(ccPane);
                // zu Spaltenliste hinzufügen
                childPanes.add(ccPane);
            }
            // Spalte veId in HBox hinzufügen
            horizontalBox.getChildren().add(verticalBox);
        }

        // Gesamtkonstrukt hinzufügen
        getChildren().clear();
        getChildren().add(horizontalBox);

        if (DEBUG) {
            disableDebugStyle();
            setDebugSplitting(false);
        }
    }

    /**
     * initialisiert Felder mit Wörtern.
     *
     * @param usableStrings  - Liste mit Wörtern, die noch verwendet werden können
     * @param currentStrings - Wörter, die in Benutzung sind
     */
    public void philLeaves(List<String> usableStrings, List<String> currentStrings) {
        if (isChild()) {
            String current = usableStrings.remove(0);
            currentStrings.add(current);
            setContent(current);
        } else {
            // rekursiver Aufruf
            for (CodeChartsPane childPane : childPanes) {
                childPane.philLeaves(usableStrings, currentStrings);
            }
        }
    }

    /**
     * Setzt den Inhalt des CodeChartsPanes auf einen String.
     *
     * @param content - der gewünschte Inhalt
     */
    public void setContent(String content) {
        this.content = content;
        // Inhalt erstellen und Größe setzen
        Label label = new Label(content);
        label.setFont(new Font(Math.hypot(getPrefWidth(), getPrefHeight()) / 10));
        // zentrieren
        label.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label, 0.0);
        AnchorPane.setRightAnchor(label, 0.0);
        AnchorPane.setTopAnchor(label, 0.0);
        AnchorPane.setBottomAnchor(label, 0.0);
        label.setAlignment(Pos.CENTER);
        // hinzufügen
        childPanes.clear();
        getChildren().clear();
        getChildren().add(label);
    }

    /**
     * findet das CodeChartsPane, welches den String enthält.
     *
     * @param searchContent - zu findender String
     * @return das CodeChartsPane-Objekt, welches den String enthält.
     */
    public CodeChartsPane getLeaf(String searchContent) {
        if (isChild()) {
            return this.content.equals(searchContent) ? this : null;
        }
        // alle Kinder durchsuchen
        // Spalten
        for (CodeChartsPane ccPane : childPanes) {
            // rekursiver Aufruf
            CodeChartsPane child = ccPane.getLeaf(searchContent);
            // falls gefunden: Suche beendet
            if (child != null) {
                return child;
            }
        }
        // nichts gefunden
        return null;
    }

    /**
     * Färbt das Pane zum Debuggen.
     */
    public void enableDebugStyle() {
        Color color = Color.color(Math.random(), Math.random(), Math.random());
        setBackground(new Background(new BackgroundFill(color, null, null)));
        setOpacity(0.7);
        setOnMouseEntered(isChild()
                ? e -> setBackground(
                        new Background(new BackgroundFill(color.brighter(), null, null)))
                : e -> {
                });
        setOnMouseExited(isChild()
                ? e -> setBackground(
                        new Background(new BackgroundFill(color, null, null)))
                : e -> {
                });
    }

    /**
     * versteckt den DebugStyle.
     */
    public void disableDebugStyle() {
        setBackground(null);
        setOpacity(1);
        setOnMouseEntered(e -> {
        });
        setOnMouseExited(e -> {
        });
    }

    /**
     * CodeChartsPanes teilen sich beim Klicken.
     *
     * @param value - ob das Feature aktiviert sein soll
     */
    public void setDebugSplitting(boolean value) {
        setOnMouseClicked(value ? e -> subdivide() : e -> {});
    }

    @Override
    public String toString() {
        return String.format("codeChartsPane {%s}", viewport.toString());
    }

    private boolean isChild() {
        return childPanes.size() == 0;
    }
}
