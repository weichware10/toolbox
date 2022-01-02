package github.weichware10.toolbox.zoommaps;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Überprüft die Funktionen processInput() und save().
 */
public class ZoomCalculatorTest {
    @Test
    public void doesNotThrowErrors() {
        ZoomCalculator testcalculator = new ZoomCalculator(null, null, null);
        assertEquals(false, testcalculator.save());
    }

    /**
     * Überprüfen, ob Input richtig umgesetzt wird -> damit wird auch ZoomInput getestet.
     */
    @Test
    @Ignore
    public void processInputCorrectly() {
        ZoomCalculator testcalculator = new ZoomCalculator(null, null, null);
        testcalculator.processInputIn(new double[]{0, 0});
        // Überprüfen, ob Input richtig gesetzt wurde
    }
}
