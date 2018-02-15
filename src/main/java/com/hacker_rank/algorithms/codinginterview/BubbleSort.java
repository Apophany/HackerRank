package com.hacker_rank.algorithms.codinginterview;

import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numElements = sc.nextInt();

        int[] a = new int[numElements];
        for(int i = 0; i < numElements; i++) {
            a[i] = sc.nextInt();
        }

        int numSwaps = sort(a);
        System.out.println("Array is sorted in " + numSwaps + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[a.length - 1]);
    }

    public static int sort(int[] a) {
        int numSwaps = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    int left = a[j];

                    a[j] = a[j+1];
                    a[j+1] = left;

                    numSwaps++;
                }
            }
        }
        return numSwaps;
    }
}
