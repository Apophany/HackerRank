package com.codility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question1 {
    private static final Map<Integer, List<Integer>> rotationMap = new HashMap<>();

    public int solution(int[] A) {
        initialiseRoationMap();
        return calculateMinimumRotations(A);
    }

    private int calculateMinimumRotations(int[] a) {
        int minRotations = Integer.MAX_VALUE;
        if (a.length <= 1) {
            return 0;
        }

        for (int a_i : a) {
            int currRotations = 0;
            for (int a_j : a) {
                currRotations += calcSingleDistance(a_i, a_j);
            }
            if (currRotations < minRotations) {
                minRotations = currRotations;
            }
        }
        return minRotations;
    }

    private int calcSingleDistance(int from, int to) {
        if (from == to) {
            return 0;
        }
        return rotationMap.get(from).contains(to) ? 1 : 2;
    }

    private void initialiseRoationMap() {
        final List<Integer> yAxis = Arrays.asList(2, 3, 4, 5);
        final List<Integer> xAxis = Arrays.asList(1, 3, 4, 6);
        final List<Integer> zAxis = Arrays.asList(1, 2, 5, 6);

        rotationMap.put(1, yAxis);
        rotationMap.put(2, xAxis);
        rotationMap.put(3, zAxis);
        rotationMap.put(4, zAxis);
        rotationMap.put(5, xAxis);
        rotationMap.put(6, yAxis);
    }

    public static void main(String[] args) {
        final Question1 q = new Question1();
        System.out.println(q.solution(new int[]{1, 2, 3, 4, 5, 6}));
    }
}
