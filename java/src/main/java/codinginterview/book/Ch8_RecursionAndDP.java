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

    /**
     * 8.5 Recursive Multiply: Write a recursive function to multiply two positive integers
     * without using the * operator. You can use addition, subtraction, and bit shifting,
     * but you should minimize the number of those operations
     */
    public static int multiply(int a, int b) {
        int bigger = a < b ? b : a;
        int smaller = a < b ? a : b;
        return multiplyHelper(smaller, bigger);
    }

    private static int multiplyHelper(int smaller, int bigger) {
        if (smaller == 0) {
            return 0;
        }
        if (smaller == 1) {
            return bigger;
        }

        int halfProduct = multiplyHelper(smaller >> 1, bigger);
        if ((halfProduct & 1) == 1) {
            return halfProduct + halfProduct;
        }
        return halfProduct + halfProduct + bigger;
    }

    /**
     * 8.6 Towers of Hanoi: In the classic problem of the Towers of Hanoi, you
     * have 3 towers and N disks of different sizes which can slide onto any
     * tower. The puzzle starts with disks sorted in ascending order of size
     * from top to bottom. You have the following constraints:
     * <p>
     * 1. Only one disk can be moved at a time
     * <p>
     * 2. A disk is slide off the top of one tower to another
     * <p>
     * 3. A disk cannot be placed on top of a smaller disk
     * <p>
     * Write a program to move the disks from the first tower to the last using
     * stacks.
     */
    public static void hanoiSolver(Stack<Integer> first, Stack<Integer> second, Stack<Integer> third) {
        hanoiSolverHelper(first.size(), first, third, second);
    }

    private static void hanoiSolverHelper(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> buffer) {
        if (n > 0) {
            hanoiSolverHelper(n - 1, from, buffer, to);
            moveTopTo(from, to);
            hanoiSolverHelper(n - 1, buffer, to, from);
        }
    }

    private static void moveTopTo(Stack<Integer> from, Stack<Integer> to) {
        int valToMove = from.pop();
        if (!to.isEmpty() && to.peek() <= valToMove) {
            System.out.println("Error replacing disk");
        } else {
            to.push(valToMove);
        }
    }

    /**
     * 8.7 Permutations without dups: Write a method to compute all
     * permutations of a string of unique characters
     */
    public static List<List<Character>> permutations(List<Character> characters) {
        return permutationHelper(new ArrayList<>(), new ArrayList<>(), characters);
    }

    private static List<List<Character>> permutationHelper(
            List<List<Character>> permutations,
            List<Character> curr,
            List<Character> characters
    ) {
        if (curr.size() == characters.size()) {
            permutations.add(curr);
        }
        for (Character c : characters) {
            if (!curr.contains(c)) {
                List<Character> permutation = new ArrayList<>(curr);
                permutation.add(c);
                permutationHelper(permutations, permutation, characters);
            }
        }
        return permutations;
    }

    /**
     * 8.8 Permutations with dupes: Write a method to computer all permutations
     * of a string whose characters are not necessary unique. The list of
     * permutations should not have duplicates
     */
    public static List<String> permutationsWithDupes(String s) {
        final HashMap<Character, Integer> charFreqs = buildFreqMap(s);
        final List<String> result = new ArrayList<>();
        permutationWithDupeHelper("", s.length(), charFreqs, result);
        return result;
    }

    private static HashMap<Character, Integer> buildFreqMap(String s) {
        final HashMap<Character, Integer> freq = new HashMap<>();
        for (Character c : s.toCharArray()) {
            freq.merge(c, 1, (oldV, newV) -> oldV + newV);
        }
        return freq;
    }

    private static void permutationWithDupeHelper(String prefix, int remaining, HashMap<Character, Integer> charFreqs, List<String> result) {
        if (remaining == 0) {
            result.add(prefix);
            return;
        }

        for (Character c : charFreqs.keySet()) {
            final Integer count = charFreqs.get(c);
            if (count > 0) {
                charFreqs.put(c, count - 1);
                permutationWithDupeHelper(prefix + c, remaining - 1, charFreqs, result);
                charFreqs.put(c, count);
            }
        }
    }

    /**
     * 8.9 Parens: Print all valid (e.g properly opened and closed) combinations
     * of n pairs of parentheses
     */
    public static List<String> parens(int numPairs) {
        final ArrayList<String> combinations = new ArrayList<>();
        parenHelper("", 0, 0, numPairs, combinations);
        return combinations;
    }

    private static void parenHelper(String curr, int numOpen, int numClosed, int numPairs, List<String> combinations) {
        if ((numOpen == numClosed) && (numOpen == numPairs)) {
            combinations.add(curr);
            return;
        }
        if (numOpen != numPairs) {
            parenHelper(curr + "(", numOpen + 1, numClosed, numPairs, combinations);
        }
        if (numClosed < numOpen) {
            parenHelper(curr + ")", numOpen, numClosed + 1, numPairs, combinations);
        }
    }

    /**
     * 8.10 Paint Fill: Implement the 'paint fill' function that one might see on
     * many image editing programs. That is, given a screen (represented by a two-
     * dimensional array of colours), a point, and a new colour, fill in the
     * surrounding area until the colour changes from the original colour.
     */
    public static void fill(int[][] screen, Point point, int colour) {
        final boolean[][] visited = new boolean[screen.length][];
        Arrays.fill(visited, new boolean[screen.length]);

        final Queue<Point> toExplore = new LinkedList<>();
        toExplore.offer(point);

        while (!toExplore.isEmpty()) {
            final Point p = toExplore.poll();
            if (p.y > screen.length - 1 || p.x > screen.length - 1 || visited[p.y][p.x]) {
                continue;
            }
            visited[p.y][p.x] = true;
            if (screen[p.y][p.x] != screen[point.y][point.x]) {
                continue;
            }
            screen[p.y][p.x] = colour;
            toExplore.offer(new Point(p.x + 1, p.y));
            toExplore.offer(new Point(p.x - 1, p.y));
            toExplore.offer(new Point(p.x, p.y + 1));
            toExplore.offer(new Point(p.x, p.y - 1));
        }
    }
}
