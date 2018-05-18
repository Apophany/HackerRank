package codinginterview;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static codinginterview.book.Ch8_RecursionAndDP.*;

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

    @Test
    public void test_MagicIndex() {
        int[] arr;

        arr = new int[]{2, 3, 4, 5, 6, 7};
        Assert.assertEquals(Integer.MIN_VALUE, getMagicIndex(arr));

        arr = new int[]{-2, -3, -4, -5, -6, -7};
        Assert.assertEquals(Integer.MIN_VALUE, getMagicIndex(arr));

        arr = new int[]{-2, 1, 3, 7, 23};
        Assert.assertEquals(1, getMagicIndex(arr));
    }

    @Test
    public void test_PowerSet() {
        Set<Integer> set = new HashSet<>();
        set.add(2);
        set.add(5);
        set.add(7);

        final Set<Set<Integer>> subsets = powerSet(set);

        Assert.assertEquals(7, subsets.size());
    }

    @Test
    public void test_multiply() {
        Assert.assertEquals(6, multiply(2, 3));
        Assert.assertEquals(0, multiply(2, 0));
        Assert.assertEquals(0, multiply(0, 2));
    }

    @Test
    public void test_towers_of_hanoi() {
        Stack<Integer> origin = new Stack<>();
        origin.push(5);
        origin.push(4);
        origin.push(3);
        origin.push(2);
        origin.push(1);

        final Stack<Integer> destination = new Stack<>();
        hanoiSolver(origin, new Stack<>(), destination);

        Assert.assertEquals(5, destination.size());
    }

    @Test
    public void test_permutations_with_dupes() {
        List<Character> characters = new ArrayList<>();
        characters.add('1');
        characters.add('2');
        characters.add('3');

        Assert.assertEquals(
                "[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]",
                permutations(characters).toString()
        );
    }

    @Test
    public void test_permutations_without_dupes() {
        String s = "abcc";
        Assert.assertEquals(
                "[abcc, acbc, accb, bacc, bcac, bcca, cabc, cacb, cbac, cbca, ccab, ccba]",
                permutationsWithDupes(s).toString()
        );
    }

    @Test
    public void test_parentheses_combinations() {
        Assert.assertEquals("[((())), (()()), (())(), ()(()), ()()()]", parens(3).toString());
    }

    @Test
    public void test_coin_combinations() {
        Assert.assertEquals(1, numCombinations(1, new int[]{3, 2}));
        Assert.assertEquals(1, numCombinations(1, new int[]{3, 2, 1}));
        Assert.assertEquals(2, numCombinations(2, new int[]{3, 2, 1}));
        Assert.assertEquals(4, numCombinations(4, new int[]{5, 3, 2, 1}));
    }
}
