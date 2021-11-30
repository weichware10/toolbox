package github.weichware10.toolbox.zoommaps;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testes, ob bei der move-Funktion in ZoomBild ein Array mit der richtigen
 * Länge erstellt wird.
 */
public class ZoomBildTest {
    @Test
    public void testingCoordinates() {
        ZoomBild zoombild = new ZoomBild();
        float[] position = {0, 1, 2};
        assertEquals("Array-length should be three", 3, zoombild.move(position).length);
    }
}
