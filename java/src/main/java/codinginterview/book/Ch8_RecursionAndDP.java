package codinginterview.book;

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
            numWays[numSteps] =
                    tripleStep(numSteps - 1, numWays)
                            + tripleStep(numSteps - 2, numWays)
                            + tripleStep(numSteps - 3, numWays);
        }
        return numWays[numSteps];
    }
}
