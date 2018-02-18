package com.hacker_rank.algorithms.codinginterview;


import org.junit.Test;

import static com.hacker_rank.algorithms.codinginterview.ArraysAndStrings.*;
import static org.junit.Assert.*;

public class ArraysAndStringsTest {

    @Test
    public void test_urlify() {
        assertEquals("Mr%20John%20Smith", urlify("Mr John Smith    ".toCharArray()));
    }

    @Test
    public void test_palindrome() {
        assertTrue(checkPalindrome("Tact Coa"));
    }

    @Test
    public void test_offByOne() {
        assertTrue(isOffByOne("pale".toCharArray(), "ple".toCharArray()));
        assertTrue(isOffByOne("ple".toCharArray(), "pale".toCharArray()));
        assertTrue(isOffByOne("pales".toCharArray(), "pale".toCharArray()));
        assertTrue(isOffByOne("pale".toCharArray(), "bale".toCharArray()));
        assertFalse(isOffByOne("pale".toCharArray(), "bake".toCharArray()));
    }

    @Test
    public void test_compression() {
        assertEquals("a2b1c5a3", compress("aabcccccaaa"));
    }

    @Test
    public void test_rotate() {
        int[][] matrix = new int[][]{new int[]{1, 2}, new int[]{3, 4}};
        rotate(matrix);

        assertArrayEquals(new int[][]{new int[]{3, 1}, new int[]{4, 2}}, matrix);

        matrix = new int[][]{
                new int[]{1, 2, 3, 4},
                new int[]{5, 6, 7, 8},
                new int[]{9, 10, 11, 12},
                new int[]{13, 14, 15, 16}
        };
        rotate(matrix);

        assertArrayEquals(
                new int[][]{
                        new int[]{13, 9, 5, 1},
                        new int[]{14, 10, 6, 2},
                        new int[]{15, 11, 7, 3},
                        new int[]{16, 12, 8, 4}
                },
                matrix
        );
    }
}