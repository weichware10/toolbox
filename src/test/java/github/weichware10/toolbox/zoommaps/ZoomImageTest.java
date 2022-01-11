package github.weichware10.toolbox.zoommaps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Testet ZoomImage, bzw. die Größe des Outputs der Funktion move.
 */
public class ZoomImageTest {
    /**
     * Testet ob ein Array mit der richtigen Länge erstellt wird.
     *
     * @throws IOException -
     * @throws FileNotFoundException -
     * @throws IllegalArgumentException -
     * @throws MalformedURLException -
     */
    @Test
    @SuppressWarnings("unused")
    @Ignore
    public void testOutputSize() throws MalformedURLException, IllegalArgumentException,
            FileNotFoundException, IOException {
        String location = ZoomImageTest.class.getResource("owl.jpeg").toString();
        ZoomImage zoombild = new ZoomImage(location, new ImageView(), null);
        Point3D position = new Point3D(0, 1, 2);
        // assertEquals("Array-length should be three", 3, zoombild.move(position));
    }

    /**
     * Testet, ob falsch übergebener String einen Error throwt.
     *
     * @throws IOException -
     * @throws FileNotFoundException -
     * @throws IllegalArgumentException -
     * @throws MalformedURLException -
     */
    @Test(expected = MalformedURLException.class) // erwartet, dass Fehler auftritt
    public void shouldThrowAtWrongPicture() throws MalformedURLException, IllegalArgumentException,
            FileNotFoundException, IOException {
        new ZoomImage("www.thisisnotapicture.com/owl.html", new ImageView(), null);
    }
}
