package github.weichware10.toolbox.zoommaps;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testet ZoomBild, bzw. die Größe des Outputs der Funktion move.
 */
public class ZoomBildTest {
    /**
     * Testet ob ein Array mit der richtigen Länge erstellt wird.
     */
    @Test
    public void testOutputSize() {
        String location = "abc";
        ZoomBild zoombild = new ZoomBild(location);
        float[] position = {0, 1, 2};
        assertEquals("Array-length should be three", 3, zoombild.move(position).length);
    }

    /**
     * Testet, ob falsch übergebener String einen Error throwt.
     */
    @Test(expected = IllegalArgumentException.class) // erwartet, dass Fehler auftritt
    public void shouldThrowAtWrongPicture() {
        new ZoomBild("www.thisisnotapicture.com/owl.html");
    }
}
