package github.weichware10.toolbox.eyetracking;

import github.weichware10.util.data.TrialData;

/**
 * Bestimmung der Blickkoordinaten und Abspeicherung der Versuchsdaten.
 */
@SuppressWarnings("unused")
public class EyeTrackingCoordinator {
    private float distance;
    private float position;
    private TrialData data;

    /**
     * Startet den Versuch.
     */
    public void start() {
        Auge auge = new Auge();
        float[][] position = auge.getPosition();
        int[] coords = EyeTrackingCalculator.calculateScreenCoordinates(position);
        // data.save(coords)
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
