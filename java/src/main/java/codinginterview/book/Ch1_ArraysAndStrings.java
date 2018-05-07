package codinginterview.book;

public class Ch1_ArraysAndStrings {

    /**
     * 1.3: Urlify: Replace all spaces in a string
     * with %20. You are given the true length of
     * the string.
     */
    public static String urlify(char[] input) {
        char[] shifted = shiftLeft(input);

        int start = 0;
        while (shifted[start] == Character.MIN_VALUE) {
            start++;
        }

        for (int i = 0; i < shifted.length; ) {
            if (shifted[start] != ' ') {
                shifted[i++] = shifted[start++];
            } else {
                shifted[i++] = '%';
                shifted[i++] = '2';
                shifted[i++] = '0';
                start++;
            }
        }

        return String.valueOf(shifted);
    }

    private static char[] shiftLeft(char[] input) {
        final int inputLength = input.length - 1;

        int i = inputLength;
        while (input[i] == ' ') {
            i--;
        }
        //Cant shift, no space
        if (i == inputLength) {
            return input;
        }

        //Shift everything to the end
        int j = inputLength;
        while (i >= 0) {
            input[j--] = input[i--];
            input[i + 1] = Character.MIN_VALUE;
        }
        return input;
    }

    /**
     * 1.4 Palindrome Permutation: Given a string, check if it's
     * a permutation of a palindrome. It does not need to be
     * limited to just dictionary words
     */
    public static boolean checkPalindrome(String val) {
        val = val.toLowerCase();
        int[] charCounts = new int[26];

        int nonEmptyCount = 0;
        for (char c : val.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            nonEmptyCount++;
            charCounts[(c - 'a') % 26] = charCounts[(c - 'a') % 26] + 1;
        }

        int numOdd = 0;
        for (char c : val.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (Integer.lowestOneBit(charCounts[(c - 'a') % 26]) == 1) {
                numOdd++;
            }
        }

        return nonEmptyCount % 2 == 0 ? numOdd == 0 : numOdd == 1;
    }

    /**
     * 1.5 One away: Three types of edit can be performed on a string
     * Insertion, deletion and replacement. Write a function to check
     * if only one edit has been made
     */
    public static boolean isOffByOne(char[] first, char[] second) {
        if (Math.abs(first.length - second.length) > 1) {
            return false;
        }
        boolean isReplace = first.length == second.length;

        char[] tmp = first;
        first = first.length >= second.length ? first : second;
        second = second.length <= tmp.length ? second : tmp;

        int errCount = 0;
        int i = 0;
        int j = 0;

        while (i < first.length && j < second.length) {
            if (first[i] == second[j]) {
                i++;
                j++;
                continue;
            }
            errCount++;

            if (isReplace) {
                i++;
                j++;
            } else {
                i++;
            }
        }

        return (errCount == 0 && i != j) || errCount <= 1;
    }

    /**
     * 1.6 String compression: Implement a method to perform
     * string compression using repeated character counts.
     */
    public static String compress(String toCompress) {
        StringBuilder builder = new StringBuilder();
        char prev = toCompress.charAt(0);
        int count = 0;

        for (char c : toCompress.toCharArray()) {
            if (c == prev) {
                count++;
                continue;
            }
            builder.append(prev);
            builder.append(count);
            prev = c;
            count = 1;
        }

        builder.append(prev);
        builder.append(count);

        return builder.length() < toCompress.length() ? builder.toString() : toCompress;
    }

    /**
     * 1.7 Matrix rotation: Given an image represented by an
     * NxN matrix, where each pixel is 4 bytes, rotate the
     * image by 90 degrees
     */
    public static void rotate(int[][] matrix) {
        final int numLayers = matrix.length / 2;
        final int matrixLength = matrix.length - 1;

        for (int layer = 0; layer < numLayers; layer++) {
            for (int offset = layer; offset < matrixLength - layer; offset++) {
                //save the top
                final int top = matrix[layer][offset];

                //left -> top
                matrix[layer][offset] = matrix[matrixLength - offset][layer];

                //bottom -> left
                matrix[matrixLength - offset][layer] = matrix[matrixLength - layer][matrixLength - offset];

                //right -> bottom
                matrix[matrixLength - layer][matrixLength - offset] = matrix[offset][matrixLength - layer];

                //top -> right
                matrix[offset][matrixLength - layer] = top;
            }
        }
    }

    /**
     * 1.8 Zero Matrix: Write an algorithm such that if
     * an element in an MxN matrix is 0, its entire row
     * and column are set to 0
     */
    public static void nullify(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Must be an NxM matrix");
        }
        final boolean[] isRowZero = new boolean[matrix.length];
        final boolean[] isColumnZero = new boolean[matrix[0].length];

        final int rows = matrix.length;
        final int columns = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 0) {
                    isRowZero[i] = true;
                    isColumnZero[j] = true;
                }
            }
        }

        for (int i = 0; i < isRowZero.length; i++) {
            if (isRowZero[i]) {
                for (int j = 0; j < rows; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < isColumnZero.length; i++) {
            if (isColumnZero[i]) {
                for (int j = 0; j < rows; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }
}
