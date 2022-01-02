package github.weichware10.toolbox.zoommaps;

import javafx.geometry.Point2D;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Überprüft die Funktionen processInput() und save().
 */
public class ZoomCalculatorTest {

    /**
     * Überprüfen, ob Input richtig umgesetzt wird -> damit wird auch ZoomInput getestet.
     */
    @Test
    @Ignore
    public void processInputCorrectly() {
        ZoomCalculator testcalculator = new ZoomCalculator(null, null, null);
        testcalculator.processInput(new Point2D(0, 0), 1);
        // Überprüfen, ob Input richtig gesetzt wurde
    }
}
