package github.weichware10.toolbox.codecharts;

/**
 * TODO: Fertigstellen.
 */
public class CodeChartsRaster {
    private int[] dimensions;
    private String[] strings;
    private int[] coords = new int[2];
    private String input;
    private boolean isRealtive;

    public void show() {
    }

    /**
     * Was macht setInput()?.
     */
    public void setInput() {
    }

    /**
     * Speichert die Daten aus CodeChartsCoordinator in den Variablen.
     */
    public void setData() {
        this.dimensions = CodeChartsCoordinator.getDimensions();
        this.strings = CodeChartsCoordinator.getStrings();
        this.isRealtive = CodeChartsCoordinator.getIsRelative();
    }

    /**
     * TODO: Funktion füllen
     * Sendet die Daten an das Speichermedium.
     */
    public static void sendData() {
    }

    /**
     * Soll eigentlich die Daten holen uns für die Berechnung speichern.
     * Sinnlos, weil man die Coordinaten der Vorherigen durchläufe
     * auch einfach in einer Arraylist anlegen könnte.
     * Und parallel dazu eine Arraylist mit Datum und Uhrzeit für das Speichern im Speichermedium.
     * Und parallel dazu eine Arraylist mit Rastergröße der einzelnen Durchläufe.
     */
    private void loadPastRuns() {
    }

    /**
     * Berechnet die Coordinaten und evtl. neue Aufteilung des Rasters.
     */
    public void calculate() {
        int x = 0;
        int y = 0;
        while (input != strings[x]) {
            x = x + 1;
        }
        while (x > dimensions[0]) {
            x = x - dimensions[0];
            y = y + 1;
        }
        this.coords[0] = x;
        this.coords[1] = y;

        if (isRealtive == true) {
            // Berechnung der relativen Rastergöße.
        }
    }
}
