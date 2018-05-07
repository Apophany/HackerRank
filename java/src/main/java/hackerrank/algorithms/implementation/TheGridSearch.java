package hackerrank.algorithms.implementation;

import java.util.Scanner;

public class TheGridSearch {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int numCases = in.nextInt();

        for (int testCase = 0; testCase < numCases; testCase++) {
            final int numSearchRows = in.nextInt();
            final String[] searchGrid = new String[numSearchRows];
            for (int row_i = 0; row_i < numSearchRows; row_i++) {
                searchGrid[row_i] = in.next();
            }

            int numPatternRows = in.nextInt();
            final String[] pattern = new String[numPatternRows];
            for (int row_i = 0; row_i < numPatternRows; row_i++) {
                pattern[row_i] = in.next();
            }

            String result = "NO";
            for (int search_row = 0; search_row < numSearchRows; search_row++) {
                String searchString = searchGrid[search_row];
                String patternString = pattern[0];

                if (searchString.contains(patternString)) {
                    int searchLen = searchString.length();
                    int patternLen = patternString.length();
                    int index = 0;

                    while (index <= searchLen - patternLen) {
                        if (searchString.substring(index, patternLen + index).equals(patternString)) {
                            for (int patternRow = 1; patternRow < numPatternRows; patternRow++) {
                                int i = search_row + patternRow;
                                if (i >= numSearchRows) {
                                    break;
                                }
                                String nextSearch = searchGrid[i].substring(index, patternLen + index);
                                String nextPattern = pattern[patternRow];
                                if (nextSearch.equals(nextPattern)){
                                    if (patternRow == numPatternRows - 1) {
                                        result = "YES";
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                        index++;
                    }
                }
            }
            System.out.println(result);
        }
    }
}
