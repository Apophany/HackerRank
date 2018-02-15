package com.hacker_rank.algorithms.warmup;

import java.util.Scanner;

/**
 * Created by Apophany on 29/01/2017.
 */
public class PlusMinus {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int total = in.nextInt();

        double numPositive = 0;
        double numNegative = 0;
        double numZeroes = 0;

        int i = 0;
        while(i < total) {
            final int current = in.nextInt();
            if (current > 0) {
                numPositive += 1;
            }
            if (current < 0) {
                numNegative += 1;
            }
            if (current == 0) {
                numZeroes += 1;
            }
            i++;
        }

        System.out.printf("%.6f%n", numPositive / total);
        System.out.printf("%.6f%n", numNegative / total);
        System.out.printf("%.6f", numZeroes / total);
    }
}
