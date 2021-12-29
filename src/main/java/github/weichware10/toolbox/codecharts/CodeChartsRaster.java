package github.weichware10.toolbox.codecharts;

import github.weichware10.util.data.TrialData;
import java.util.ArrayList;
import java.util.List;


/**
 * Zeigt das Raster an und ist für die Berechnung.
 * von Rastergröße und Koordinate zuständig.
 */
@SuppressWarnings("unused")
public class CodeChartsRaster {
    private int[] dimensions;
    private String[] strings;
    private int[] coords = new int[2];
    private String input;
    private boolean isRealtive;
    private CodeChartsCoordinator coordinator;
    private List<Integer> pastRuns = new ArrayList<Integer>();

    /**
     * Zeigt das Raster an.
     */
    public void show() {
    }


    /**
     * Bekommt den String von Eingabefenster.
     * über den Coordinator zur berechnung.
     */
    public void setInput(String string) {
        this.input = string;
    }

    /**
     * für Test.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Speichert die Daten aus CodeChartsCoordinator in den Variablen.
     */
    public void setData() {
        this.dimensions = coordinator.getDimensions();
        this.strings = coordinator.getStrings();
        this.isRealtive = coordinator.getIsRelative();
    }

    /**
     * Sendet die Daten an das Coordinator.
     */
    public void sendData() {
        // coordinator.data.setData(data);
    }

    /**
     * Holt die Daten aus data in Coordinator für die Berechnungen.
     * Speichert die Daten in ArrayList.
     */
    private void loadPastRuns() {
        TrialData data = coordinator.data;
        data.getData();
        // pastRuns.add(420);
        // Lädt alle vorherien DUrchläufe in die Strucktur in Rasterklasse.
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
            loadPastRuns();
            // Berechnung der relativen Rastergöße.
        }

        // Speichert die Coordinate in Coordinator.
    }
}
