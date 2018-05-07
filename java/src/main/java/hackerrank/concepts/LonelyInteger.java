package hackerrank.concepts;

import java.util.Scanner;

/**
 * Given an array A = [a0,a1,...,an-1]
 * consisting of integers in pairs
 * find the odd integer
 */
public class LonelyInteger {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        int val = 0;
        for(int i : a) {
            val ^= i;
        }
        System.out.println(val);
    }
}
