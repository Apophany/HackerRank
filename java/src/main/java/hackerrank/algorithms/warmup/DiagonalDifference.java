package hackerrank.algorithms.warmup;

import java.util.Scanner;

public class DiagonalDifference {

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int matrixSize = in.nextInt();

        int primaryDiagonalTotal = 0;
        int secondaryDiagonalTotal = 0;

        for(int i = 1; i <= matrixSize; i++) {
            for(int j = 1; j <= matrixSize; j++) {
                final boolean isPrimary = j == (matrixSize - (matrixSize - i));
                final boolean isSecondary = j == (matrixSize - (i - 1));

                final int value = in.nextInt();
                if(isPrimary) {
                    primaryDiagonalTotal += value;
                }
                if(isSecondary) {
                    secondaryDiagonalTotal += value;
                }
            }
        }
        System.out.print(Math.abs(primaryDiagonalTotal - secondaryDiagonalTotal));
    }
}
