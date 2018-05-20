package quant;

import org.junit.Assert;
import org.junit.Test;

import static quant.MaximumDrawdown.maximumDrawndown;

public class MaximumDrawdownTest {
    @Test
    public void test_continuously_increasing() {
        Assert.assertEquals(0, maximumDrawndown(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8}
        ));
    }

    @Test
    public void test_continuously_decreasing() {
        Assert.assertEquals(6, maximumDrawndown(
                new int[]{8, 7, 6, 5, 4, 3, 2}
        ));
    }

    @Test
    public void test_local_minima() {
        Assert.assertEquals(3, maximumDrawndown(
                new int[]{2, 3, 4, 3, 2, 3, 2, 1, 2, 3}
        ));
    }

    @Test
    public void test_local_maxima() {
        Assert.assertEquals(7, maximumDrawndown(
                new int[]{2, 3, 8, 3, 5, 1, 5, 9, 5, 7, 10}
        ));
    }

    @Test
    public void test_empty() {
        Assert.assertEquals(0, maximumDrawndown(new int[]{}));
    }
}
