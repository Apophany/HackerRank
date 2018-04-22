package com.hacker_rank.algorithms.codinginterview;

import org.junit.Assert;
import org.junit.Test;

import static com.hacker_rank.algorithms.codinginterview.book.Ch5_BitManipulation.*;

public class Ch5_BitManipulationTest {

    @Test
    public void test_insertMToN() {
        int m = Integer.parseInt("10011", 2);
        int n = Integer.parseInt("10001111100", 2);
        int i = 2;
        int j = 6;

        Assert.assertEquals("10001001100", Integer.toBinaryString(insertMtoN(m, n, i, j)));
    }

    @Test
    public void test_binaryToString() {
        Assert.assertEquals("ERROR", binaryToString(0.1));
        Assert.assertEquals(".101", binaryToString(0.625));
        Assert.assertEquals(".11", binaryToString(0.75));
        Assert.assertEquals("ERROR", binaryToString(-0.5));
        Assert.assertEquals("ERROR", binaryToString(1.5));
    }

    @Test
    public void test_FlipBitToWin() {
        Assert.assertEquals(8, flipBitToWin(1775));
    }

    @Test
    public void test_findNextHighestAndLowest() {
        Assert.assertEquals(13, nextHighest(9));
    }

    @Test
    public void test_BitsToFlip() {
        Assert.assertEquals(2, numberOfBitsToConvert(29, 15));
    }

    @Test
    public void test_pairwiseSwap() {
        Assert.assertEquals(Integer.parseInt("1100101", 2), pairwiseSwap(Integer.parseInt("10011010", 2)));
    }
}
