package github.weichware10.toolbox.zoommaps;

import org.junit.Test;

/**
 * TODO: Sarah.
 */
public class ZoomCalculatorTest {
    @Test
    public void doesNotThrowErrors() {
        ZoomCalculator testcalculator = new ZoomCalculator();
        testcalculator.processInput(new int[]{0, 0});
        testcalculator.save();
    }
}
