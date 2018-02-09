package com.hacker_rank.algorithms.codinginterview;

public class MergeSortInversions {
    private static Count sort(int[] arr) {
        if (arr.length == 1) {
            return new Count(arr, 0);
        }

        int[] leftCopy = new int[arr.length / 2];
        int[] rightCopy = new int[arr.length - arr.length / 2];

        System.arraycopy(arr, 0, leftCopy, 0, arr.length / 2);
        System.arraycopy(arr, arr.length / 2, rightCopy, 0, arr.length - (arr.length / 2));

        Count left = sort(leftCopy);
        Count right = sort(rightCopy);

        final Count result = merge(left.res, right.res);

        return new Count(result.res, left.inversions + right.inversions + result.inversions);
    }

    private static Count merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;

        long inversions = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
                inversions = inversions + (left.length - i);
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }

        return new Count(result, inversions);
    }

    private static final class Count{
        final int[] res;
        final long inversions;

        Count(int[] res, long inversions){

            this.res = res;
            this.inversions = inversions;
        }
    }

    public static void main(String[] args) {
        System.out.println(sort(new int[]{1,3,4,5,3,4,7,9,2,3}).inversions);
    }
}
