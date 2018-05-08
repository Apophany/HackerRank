package codinginterview;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static codinginterview.book.Ch8_RecursionAndDP.robotInAGrid;
import static codinginterview.book.Ch8_RecursionAndDP.tripleStep;

public class Ch8_RecursionAndDPTest {
    @Test
    public void test_tripleStep() {
        Assert.assertEquals(4, tripleStep(3));
        Assert.assertEquals(13, tripleStep(5));
    }

    @Test
    public void test_robotGrid() {
        boolean[][] grid = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            Arrays.fill(grid[i], true);
        }
        grid[3][0] = false;
        grid[3][1] = false;
        grid[3][3] = false;
        grid[3][4] = false;

        Assert.assertTrue(!robotInAGrid(grid).isEmpty());

        grid[3][2] = false;
        Assert.assertTrue(robotInAGrid(grid).isEmpty());
    }
}
