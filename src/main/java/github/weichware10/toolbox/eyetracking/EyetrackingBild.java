package github.weichware10.toolbox.eyetracking;

import github.weichware10.util.Bild;

/**
 * Bestimmung der Blickkoordinaten und Abspeicherung der Versuchsdaten.
 */
public class EyetrackingBild extends Bild {
    private int width;
    private int height;

    /**
     * Erstellt eine neue Instanz eines EyeTracking-Bildes mit dem Bild aus der location.
     *
     * @param location - Das zu benutzende Bild
     */
    public EyetrackingBild(String location) {
        super(location);
    }

    /**
     * Erstellt Bildschirmkoordinaten.
     */
    public void loadGrid() {
        ; // noch nichts implementiert
    }

}
