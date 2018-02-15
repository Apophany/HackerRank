package com.hacker_rank.algorithms.codinginterview;

public class ArraysAndStrings {

    /**
     * 1.3: Urlify: Replace all spaces in a string
     * with %20. You are given the true length of
     * the string.
     */
    public static String urlify(char[] input) {
        char[] shifted = shiftLeft(input);

        int start = 0;
        while (shifted[start] == Character.MIN_VALUE) {
            start++;
        }

        for (int i = 0; i < shifted.length; ) {
            if (shifted[start] != ' ') {
                shifted[i++] = shifted[start++];
            } else {
                shifted[i++] = '%';
                shifted[i++] = '2';
                shifted[i++] = '0';
                start++;
            }
        }

        return String.valueOf(shifted);
    }

    private static char[] shiftLeft(char[] input) {
        final int inputLength = input.length - 1;

        int i = inputLength;
        while (input[i] == ' ') {
            i--;
        }
        //Cant shift, no space
        if (i == inputLength) {
            return input;
        }

        //Shift everything to the end
        int j = inputLength;
        while (i >= 0) {
            input[j--] = input[i--];
            input[i + 1] = Character.MIN_VALUE;
        }
        return input;
    }

    /**
     * 1.4 Palindrom Permutation: Given a string, check if it's
     * a permutation of a palindrome. It does not need to be
     * limited to just dictionary words
     */
    public static boolean checkPalindrome(String val) {
        int[] charCounts = new int[26];

        for (char c : val.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            charCounts[(c - 'a') % 26] = charCounts[(c - 'a') % 26] + 1;
        }

        int numOdd = 0;
        for (char c : val.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (charCounts[(c - 'a') % 26] / 2 != 0) {
                numOdd++;
            }
        }

        return val.length() / 2 != 0 ? numOdd == 1 : numOdd == 0;
    }

    /**
     * 1.5 One away: Three types of edit can be performed on a string
     * Insertion, deletion and replacement. Write a function to check
     * if only one edit has been made
     */
    public static boolean isOffByOne(char[] first, char[] second) {
        if (Math.abs(first.length - second.length) > 1) {
            return false;
        }
        boolean isReplace = first.length == second.length;

        char[] tmp = first;
        first = first.length >= second.length ? first : second;
        second = second.length <= tmp.length ? second : tmp;

        int errCount = 0;
        int i = 0;
        int j = 0;

        while (i < first.length && j < second.length) {
            if (first[i] == second[j]) {
                i++;
                j++;
                continue;
            }
            errCount++;

            if (isReplace) {
                i++;
                j++;
            } else {
                i++;
            }
        }

        return (errCount == 0 && i != j) || errCount <= 1;
    }

    /**
     * 1.6 String compression: Implement a method to perform
     * string compression using repeated character counts.
     */
    public static String compress(String toCompress) {
        StringBuilder builder = new StringBuilder();
        char prev = toCompress.charAt(0);
        int count = 0;

        for (char c : toCompress.toCharArray()) {
            if (c == prev) {
                count++;
                continue;
            }
            builder.append(prev);
            builder.append(count);
            prev = c;
            count = 1;
        }

        builder.append(prev);
        builder.append(count);

        return builder.length() < toCompress.length() ? builder.toString() : toCompress;
    }
}
