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
        ZoomCalculator testcalculator = new ZoomCalculator();
        assertEquals(false, testcalculator.save());
    }

    /**
     * Überprüfen, ob Input richtig umgesetzt wird -> damit wird auch ZoomInput getestet.
     */
    @Test
    @Ignore
    public void processedInput() {
        ZoomCalculator testcalculator = new ZoomCalculator();
        // testcalculator.processInput(new int[]{0, 0});
    }
}
