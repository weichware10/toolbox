package github.weichware10.toolbox.eyetracking;

/**
 * Bestimmung der Blickkoordinaten und Abspeicherung der Versuchsdaten.
 */
public class Auge {

    float[][] lastPosition;

    /**
     * Bestimmt Position von Auge und Kopf.
     *
     * <p>Rückgabeformat: [[Augenposition], [Kopfposition]]
     *
     * @return Position, als zwei-dimensionales Array.
     */
    public float[][] getPosition() {
        return new float[][] { { -1f, -1f }, { -1f, -1f } };
    }
}
