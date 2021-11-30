package github.weichware10.toolbox.eyetracking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;


/**
 * Unittests der Positionsbestimmung calcFkt.
 */
public class EyeTrackingCalculatorTest {

    @Test
    public void testCalculation() {
        int[] result = EyeTrackingCalculator.calculateScreenCoordinates(
                new float[][] { { -1f, -1f }, { -1f, -1f } });
        assertEquals("Array Length should be 2", 2, result.length);
    }

    @Test
    @Ignore
    public void testCalculationChanged() {
        int[] oldResult = EyeTrackingCalculator.calculateScreenCoordinates(
                new float[][] { { 1, 1 }, { 1, 2 } });
        int[] newResult = EyeTrackingCalculator.calculateScreenCoordinates(
                new float[][] { { 3, 2 }, { 1, 2 } });
        assertFalse("newResult should not be the same as old result", Arrays.equals(oldResult, newResult));



    }

    @Test
    public void testCalculationNotChanged() {
        int[] oldResult = EyeTrackingCalculator.calculateScreenCoordinates(
                new float[][] { { -1f, -1f }, { -1f, -1f } });
        int[] newResult = EyeTrackingCalculator.calculateScreenCoordinates(
                new float[][] { { -1f, -1f }, { -1f, -1f } });
        assertTrue("newResult should be the same as old result", Arrays.equals(oldResult, newResult));
        


    }
}
