package github.weichware10.toolbox.codecharts;

import org.junit.Ignore;
import org.junit.Test;

/**
 * TODO: David.
 */
public class CodeChartsBildTest {
    /**
     * Testet ob die URL richtig ist.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAtWrongPicture() {
        new CodeChartsBild("www.thisisnotapicture.com/owl.html");
    }
}
