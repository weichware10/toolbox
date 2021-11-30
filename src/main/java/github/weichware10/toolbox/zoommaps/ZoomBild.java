package github.weichware10.toolbox.zoommaps;

import github.weichware10.util.Bild;

/**
 * Ändert den aktuellen Bildausschnitt mit Hilfe gegebener Koordianten
 * und gibt die neuen Koordinaten an ZoomCalculator zurück.
 */
public class ZoomBild extends Bild {

    /**
     * Instanziiert ein neues ZoomBild.
     *
     * @param location - die Quelle der zu benutzenden Bilddatei
     * @throws IllegalArgumentException falls die Location falsch ist
     */
    public ZoomBild(String location) throws IllegalArgumentException {
        super(location);
    }

    /**
     * Bewegt das Bild an die gegebenen Koordinaten, neue
     * Koordinaten werden zurückgegeben.
     *
     * @param position - gegebene Koordinaten (float[3])
     * @return neue Koordinaten
     */
    protected float[] move(float[] position) {
        return new float[]{0, 0, 0};
    }
}
