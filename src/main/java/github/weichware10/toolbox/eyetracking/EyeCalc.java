package github.weichware10.toolbox.eyetracking;

import github.weichware10.util.Data;

/**
 * Bestimmung der Blickkoordinaten und Abspeicherung der Versuchsdaten.
 */
public class EyeCalc {
    private float distance;
    private float position;
    private Data data;

    /**
     * Startet den Versuch.
     */
    public void start() {
        ; // noch nichts implementiert
    }

    /**
     * Lädt für {@link #calculate()} benötigte Daten.
     */
    private void setup() {
        ;
    }

    /**
     * Rechnet aktuelle Blickkoordinaten aus.
     *
     * @return x- und y-Wert auf dem Bildschirm
     */
    private int[] calculate() {
        return new int[]{-1, -1};
    }
}
