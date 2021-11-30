package github.weichware10.toolbox.eyetracking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EyeTrackingCalculatorTest {

    @Test
    public void testCalculation() {
        int[] result = EyeTrackingCalculator.calculateScreenCoordinates(
                new float[][] { { -1f, -1f }, { -1f, -1f } });
        assertEquals("Array Length should be 2", 2, result.length);
    }
}
