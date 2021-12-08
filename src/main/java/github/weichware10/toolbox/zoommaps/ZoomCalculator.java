package github.weichware10.toolbox.zoommaps;

import github.weichware10.util.data.TrialData;

/**
 * Bekommt Rohdaten des Systems (Mausklick), berechnet neue Position des Bildes
 * und meldet Speichererfolg.
 */
public class ZoomCalculator {
    private float speed;
    private float[] position;
    private TrialData data;

    /**
     * verarbeitet Input von Benutzer/System.
     *
     * @param rawInput - Screenkoordinaten vom Mausklick (int[2])
     */
    public void processInput(int[] rawInput) {
        ; // pass
    }

    /**
     * Sendet gesammelte Daten Speichermedium und meldet (Miss-)Erfolg.
     *
     * @return Erfolgsmeldung
     */
    public boolean save() {
        return false;
    }

    /**
     * Berechnet die neue position des Bildes aus den Rohdaten.
     *
     * @param rawInput - Screenkoordinaten vom Mausklick (int[2])
     * @return neue Screenkoordinaten
     */
    private float[] calculate(int[] rawInput) {
        // float[] output = new float[3];
        return new float[]{0, 0, 0};
    }
}
