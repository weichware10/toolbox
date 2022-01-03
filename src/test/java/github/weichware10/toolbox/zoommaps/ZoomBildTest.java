package github.weichware10.toolbox.zoommaps;

import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Testet ZoomBild, bzw. die Größe des Outputs der Funktion move.
 */
public class ZoomBildTest {
    /**
     * Testet ob ein Array mit der richtigen Länge erstellt wird.
     */
    @Test
    @SuppressWarnings("unused")
    @Ignore
    public void testOutputSize() {
        String location = ZoomBildTest.class.getResource("owl.jpeg").toString();
        ZoomBild zoombild = new ZoomBild(location, new ImageView(), null);
        Point3D position = new Point3D(0, 1, 2);
        // assertEquals("Array-length should be three", 3, zoombild.move(position));
    }

    /**
     * Testet, ob falsch übergebener String einen Error throwt.
     */
    @Test(expected = IllegalArgumentException.class) // erwartet, dass Fehler auftritt
    public void shouldThrowAtWrongPicture() {
        new ZoomBild("www.thisisnotapicture.com/owl.html", new ImageView(), null);
    }
}
