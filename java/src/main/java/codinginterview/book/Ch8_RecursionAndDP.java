package codinginterview.book;

import java.util.*;

public class Ch8_RecursionAndDP {
    /**
     * 8.1 Triple Step: A child is running up a staircase with n
     * steps and can hop either 1 step, 2 steps or 3 steps at a
     * time. Implement a method to count how many possible ways
     * the child can run up the stairs
     */
    public static long tripleStep(int numSteps) {
        final long[] numWays = new long[Math.max(numSteps + 1, 1)];
        numWays[0] = 1;
        return tripleStep(numSteps, numWays);
    }

    private static long tripleStep(int numSteps, long[] numWays) {
        if (numSteps < 0) {
            return 0;
        }
        if (numWays[numSteps] == 0) {
            numWays[numSteps] = tripleStep(numSteps - 1, numWays)
                    + tripleStep(numSteps - 2, numWays)
                    + tripleStep(numSteps - 3, numWays);
        }
        return numWays[numSteps];
    }

    /**
     * 8.2 Robot in a grid: Imagine a robot sitting on the upper left
     * corner of the grid with r rows and c columns. The robot can only
     * move in two directions, right and down, but certain cells are
     * 'off limits' such that the robot cannot step on them. Design
     * an algorithm to find a path for the robot from the top left to
     * the bottom right.
     */
    public static List<Point> robotInAGrid(boolean[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException("Grid must be at least 1x1");
        }
        final Map<Point, List<Point>> memo = new HashMap<>();
        return robotInAGrid(grid, memo, grid.length - 1, grid[0].length - 1);
    }

    private static List<Point> robotInAGrid(boolean[][] grid, Map<Point, List<Point>> memo, int x, int y) {
        if (x < 0 || y < 0 || x > grid.length || y > grid[0].length) {
            return null;
        }
        //Cell is off limit
        if (!grid[x][y]) {
            return null;
        }
        final Point point = new Point(x, y);
        final boolean isOrigin = (x == 0 && y == 0);

        if (memo.get(point) == null) {
            List<Point> path = robotInAGrid(grid, memo, x - 1, y);
            if (path == null || path.isEmpty()) {
                path = robotInAGrid(grid, memo, x, y - 1);
            }
            if (path == null) {
                path = new ArrayList<>();
            }
            if (!path.isEmpty() || isOrigin) {
                path.add(point);
            }
            memo.put(point, path);
        }
        return memo.get(point);
    }

    public static class Point {
        int x;
        int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 8.3 Magic Index: A magic index in an array A[0..n-1] is defined to be
     * an index such that A[i] = i. Given a sorted array of distinct integers,
     * write a method to find a magic index, if one exists, in array A
     */
    public static int getMagicIndex(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            //Avoid integer overflow
            int midPoint = high - ((high - low) / 2);

            if (arr[midPoint] == midPoint) {
                return midPoint;
            }

            if (arr[midPoint] < midPoint) {
                low = midPoint + 1;
                continue;
            }
            if (arr[midPoint] > midPoint) {
                high = midPoint - 1;
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * 8.4 Power Set: Write a method to return all subsets of a set
     */
    public static <T> Set<Set<T>> powerSet(Set<T> set) {
        final Set<Set<T>> subsets = new HashSet<>();
        powerSet(subsets, new HashSet<>(), set);
        return subsets;
    }

    private static <T> void powerSet(Set<Set<T>> subsets, Set<T> currSubSet, Set<T> remainingElements) {
        for (T t : remainingElements) {
            final Set<T> elements = new HashSet<>(remainingElements);
            elements.remove(t);

            final Set<T> curr = new HashSet<>(currSubSet);
            curr.add(t);
            subsets.add(curr);

            powerSet(subsets, curr, elements);
        }
    }
}
