package github.weichware10.toolbox.eyetracking;

/**
 * Führt Berechnungen bei EyeTracking-Versuchen aus.
 */
class EyeTrackingCalculator {

    /**
     * Cannot be instantiated.
     */
    private EyeTrackingCalculator() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
     * Rechnet aktuelle Blickkoordinaten aus.
     *
     * @param eyePosition - die Position des Auges
     * @return die Bildschirmkoordinaten
     */
    protected static int[] calculateScreenCoordinates(float[][] eyePosition) {
        // Berechnung implementieren
        return new int[]{ -1, -1 };
    }
}
