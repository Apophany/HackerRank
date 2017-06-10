package com.hacker_rank.algorithms.implementation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Apophany on 05/06/2017.
 *
 *  The idea behind the algorithm is to first
 *  search right to left for the sequence where
 *  n < n-1 < n-2 ... < n-i. For this sub-sequence
 *  it is not possible to find a smaller sub-sequence.
 *
 *  When we find an element such that  n - i > n - (i-1)
 *  then this increasing sub-sequence is broken and we call
 *  (n - i) - 1 the pivot.
 *
 *  Starting from the right, we then search left
 *  until we hit an element such that n - k > (n - i) - 1
 *  i.e greater than the pivot. Due to the ordering of the
 *  searched sub-sequence, this will always be the next largest
 *  element greater than the pivot. We then swap the positions
 *  of these two elements.
 *
 *  Finally we reverse then increasing sub-sequence n..n-i so
 *  that is it in descending order from right to left.
 *
 *  This is necessarily the next largest permutation.
 */
public class BiggerIsGreater {
    public static void main(String[] args) {
        try {
            solution();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void solution() throws IOException {
        final BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("C:/Users/Zeus/Documents/input.txt")
                )
        );
        final int numEntries = Integer.parseInt(in.readLine());

        for(int entry = 0; entry < numEntries; entry++){
            final String encrypted = in.readLine();
            final char[] encrypArray = encrypted.toCharArray();
            final int len = encrypArray.length;

            if (encrypArray.length == 1){
                System.out.println("no answer");
                continue;
            }

            int index = len - 1;
            char right = encrypArray[index];
            char left = encrypArray[index - 1];

            while(left >= right) {
                index--;
                if (index - 1 < 0) {
                    break;
                }
                right = encrypArray[index];
                left = encrypArray[index - 1];
            }
            if (index - 1 < 0){
                System.out.println("no answer");
                continue;
            }

            final int pivot = index - 1;
            index = len - 1;

            left = encrypArray[pivot];
            right = encrypArray[index];

            while (left >= right && index > pivot) {
                right = encrypArray[--index];
            }
            encrypArray[pivot] = right;
            encrypArray[index] = left;

            int i = pivot + 1;
            int j = len - 1;

            try {
                while (i < j) {
                    left = encrypArray[i];
                    right = encrypArray[j];
                    encrypArray[i] = right;
                    encrypArray[j] = left;
                    i++;
                    j--;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(String.valueOf(encrypArray));
        }
    }
}
