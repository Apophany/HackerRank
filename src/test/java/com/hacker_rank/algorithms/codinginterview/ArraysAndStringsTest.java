package com.hacker_rank.algorithms.codinginterview;


import org.junit.Test;

import static com.hacker_rank.algorithms.codinginterview.ArraysAndStrings.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
}