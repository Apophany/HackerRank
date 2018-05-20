package quant;

/**
 * Given a timeseries, find the maximum drawdown i.e the maximum loss
 * from a peak to a trough of a portfolio.
 */
public class MaximumDrawdown {
    public static int maximumDrawndown(int[] series) {
        int highPoint = Integer.MIN_VALUE;
        int lowPoint = Integer.MAX_VALUE;

        int maxDrawdown = 0;
        for (int currPoint : series) {
            if (currPoint > highPoint) {
                highPoint = currPoint;
                lowPoint = Integer.MAX_VALUE;
            }
            if (currPoint < lowPoint) {
                lowPoint = currPoint;
            }
            if (highPoint - lowPoint > maxDrawdown) {
                maxDrawdown = highPoint - lowPoint;
            }
        }
        return maxDrawdown;
    }
}
