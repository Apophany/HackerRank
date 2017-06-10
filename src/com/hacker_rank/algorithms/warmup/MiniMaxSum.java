package com.hacker_rank.algorithms.warmup;

import java.util.Scanner;

/**
 * Created by Apophany on 29/01/2017.
 */
public class MiniMaxSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();
        long d = in.nextLong();
        long e = in.nextLong();

        Long[] inputs = new Long[5];
        inputs[0] = a;
        inputs[1] = b;
        inputs[2] = c;
        inputs[3] = d;
        inputs[4] = e;

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        int min_index = 0;
        int max_index = 0;
        for(int i = 0; i < inputs.length; i++) {
            if (inputs[i] < min) {
                min = inputs[i];
                min_index = i;
            }
            if (inputs[i] > max) {
                max = inputs[i];
                max_index = i;
            }
        }

        long min_sum = 0;
        long max_sum = 0;
        for(int i = 0; i < inputs.length; i++) {
            if(min_index == i) {
                max_sum += inputs[i];
            }
            if(max_index == i) {
                min_sum += inputs[i];
            }
        }

        System.out.println(min_sum + " " + max_sum);
    }
}
