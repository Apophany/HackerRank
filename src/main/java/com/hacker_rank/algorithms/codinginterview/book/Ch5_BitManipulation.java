package com.hacker_rank.algorithms.codinginterview.book;

import org.omg.CORBA.INTERNAL;

public class Ch5_BitManipulation {
    /**
     * 5.1 Insertion: Given two 32 bit numbers, N and M, and two bit positions,
     * i and j. Insert M into N such that M starts at bit j and ends at bit i.
     * You can assume the bits j through i have enough space to fit all of M.
     * <p>
     * E.g N = 10000000000, M = 10011, i = 2, j = 6
     * Output: N = 10001001100
     */
    public static int insertMtoN(int m, int n, int i, int j) {
        int mask = ~(((1 << j) - 1) << i);
        return n & mask | m << i;
    }

    /**
     * 5.2 Binary to string: Given a real number between 0 and 1 that is passed in
     * as a double, print the binary representation. If the number cannot be
     * represented accurately in binary with at most 32 characters, print 'ERROR'
     */
    public static String binaryToString(double n) {
        if (n > 1 || n < 0) {
            return "ERROR";
        }
        final StringBuilder builder = new StringBuilder(".");

        double fraction = 0.5;
        while (n > 0) {
            if (builder.length() > 32) {
                return "ERROR";
            }

            if (n >= fraction) {
                builder.append(1);
                n -= fraction;
            } else {
                builder.append(0);
            }
            fraction /= 2;
        }

        return builder.toString();
    }

    /**
     * 53 Flip Bit to Win: You have an integer and you can flip exactly one bit
     * from a 0 to a 1. Write code to find the length of the longest sequence
     * of 1s that you could create
     * <br>
     * E.g.<br> Input: 1775 (11011101111)
     * <br>
     * Output: 8
     */
    public static int flipBitToWin(int input) {
        //If all 1s, already the max sequence
        if (~input == 0) {
            return Integer.BYTES * Byte.SIZE;
        }

        int maxSize = 1;
        int currSize = 0;
        int prevSize = 0;

        while (input != 0) {
            if ((input & 1) == 1) {
                currSize++;
            } else {
                //Check if next value can be merged (is it 1?)
                prevSize = (input & 2) == 0 ? 0 : currSize;
                currSize = 0;
            }
            maxSize = Math.max(currSize + prevSize + 1, maxSize);

            //Iterate
            input >>>= 1;
        }

        return maxSize;
    }
}
