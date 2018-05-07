package codinginterview.book;

import static java.lang.String.format;

public class Ch5_BitManipulation {
    /**
     * 5.1 Insertion: Given two 32 bit numbers, N and M, and two bit positions,
     * i and j. Insert M into N such that M starts at bit j and ends at bit i.
     * You can assume the bits j through i have enough space to fit all of M.
     * <p>
     * E.g N = 10000000000, M = 10011, i = 2, j = 6
     * Output: N = 10001001100
     */
    public static int insertMtoN(int m, int n, int i, int j) {
        int mask = ~(((1 << j) - 1) << i);
        return n & mask | m << i;
    }

    /**
     * 5.2 Binary to string: Given a real number between 0 and 1 that is passed in
     * as a double, print the binary representation. If the number cannot be
     * represented accurately in binary with at most 32 characters, print 'ERROR'
     */
    public static String binaryToString(double n) {
        if (n > 1 || n < 0) {
            return "ERROR";
        }
        final StringBuilder builder = new StringBuilder(".");

        double fraction = 0.5;
        while (n > 0) {
            if (builder.length() > 32) {
                return "ERROR";
            }

            if (n >= fraction) {
                builder.append(1);
                n -= fraction;
            } else {
                builder.append(0);
            }
            fraction /= 2;
        }

        return builder.toString();
    }

    /**
     * 5.3 Flip Bit to Win: You have an integer and you can flip exactly one bit
     * from a 0 to a 1. Write code to find the length of the longest sequence
     * of 1s that you could create
     * <br>
     * E.g.<br> Input: 1775 (11011101111)
     * <br>
     * Output: 8
     */
    public static int flipBitToWin(int input) {
        //If all 1s, already the max sequence
        if (~input == 0) {
            return Integer.BYTES * Byte.SIZE;
        }

        int maxSize = 1;
        int currSize = 0;
        int prevSize = 0;

        while (input != 0) {
            if ((input & 1) == 1) {
                currSize++;
            } else {
                //Check if next value can be merged (is it 1?)
                prevSize = (input & 2) == 0 ? 0 : currSize;
                currSize = 0;
            }
            maxSize = Math.max(currSize + prevSize + 1, maxSize);

            //Iterate
            input >>>= 1;
        }

        return maxSize;
    }

    /**
     * 5.4 Given a positive number, print the next smallest and next largest number that have
     * the same number of 1 bits in their binary representation
     */
    public static int nextHighest(int n) {
        int c0 = 0;
        int c1 = 1;

        int tmp = n;
        //Find the number of 0 bits before the first 1 bit
        while ((tmp & 1) == 0 && (tmp & 1) != 1) {
            c0++;
            tmp >>= 1;
        }
        //Find the number of 1 bits before the next 0 bit
        while ((tmp & 1) == 1) {
            c1++;
            tmp >>= 1;
        }

        //The position of the first non-trailing 0 bit
        int p = c0 + c1;

        //Mask to clear all the bits before p
        int clearMask = ~((1 << p) - 1);
        //Mask to set the first (c1-1) to 1 bits
        int addMask = (1 << c1 - 1) - 1;

        //Set the pth bit to 1
        n |= (1 << p);

        //Apply the mask to clear all bits before p
        n &= clearMask;

        //Apply the mask to set the first (c1-1) bits to 1 bits
        return n | addMask;
    }

    /**
     * 5.6 Conversion: Determine the number of bits you need to flip to
     * convert integer A to integer B
     */
    public static int numberOfBitsToConvert(int a, int b) {
        int diffBits = a ^ b;
        int bitsToFlip = 0;

        while (diffBits != 0) {
            if ((diffBits & 1) == 1) {
                bitsToFlip++;
            }
            diffBits >>= 1;
        }

        return bitsToFlip;
    }

    /**
     * 5.7 Pairwise swap: Write a program to swap odd and even bits in an integer with
     * as few as instructions as possible
     * <p>
     * e.g bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped and so on
     */
    public static int pairwiseSwap(int a) {
        final int evenMask = 0xAAAAAAAA;
        final int oddMask = 0x55555555;

        return ((a & evenMask) >> 1) | ((a & oddMask) << 1);
    }

    /**
     * <b>5.8 Draw line:</b> A monochrome screen is stored as a single array of bytes,
     * allowing eight consecutive pixels to be stored in one byte. The screen
     * has width w, where w is divisible by 8 (that is, no byte will be split
     * across rows). The height of the screen, of course, can be derived from
     * the length of the array and the width. Implement a function to draw a
     * horizontal line from (x1, y) to (x2, y).
     * <p><p>
     * The method signature should look something like:
     * <p>
     * drawLine(byte[] screen, int width, int x1, int x2, int y)
     *
     * Note: Bug when x1 & x2 are in the same byte
     */
    public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        final int rowNum = (width / 8) * (y - 1);
        final int numRows = (screen.length * 8) / width;
        if (rowNum > numRows) {
            throw new IllegalArgumentException(
                    format("Cannot draw row at height: %d. Screen height is: %d", rowNum, numRows)
            );
        }

        final int indexStart = rowNum + (x1 / 8);
        final int indexEnd = rowNum + (x2 / 8);
        if (indexEnd > width) {
            throw new IllegalArgumentException(
                    format("Cannot draw line to index %d. Screen width is: %d", indexEnd, width)
            );
        }

        int currByte = indexStart;
        int bitsToSet = x2 - x1;

        //Set the start bits
        int startOffset = x1 % 8;
        screen[currByte] = (byte) (screen[currByte] & (0xFF >> startOffset));
        bitsToSet -= startOffset;

        //Set the middle blocks
        while (bitsToSet / 8 != 0) {
            screen[currByte++] = (byte) 0xFF;
            bitsToSet -= 8;
        }

        //Set the end bits (checking if x1 & x2 are in the same byte)
        if ((x1 / 8) != (x2 / 8 )) {
            currByte++;
            screen[currByte] = (byte)(screen[currByte] & (~(0xFF >> (bitsToSet - 1))));
        } else {
            screen[currByte] = (byte)(screen[currByte] | (~(0xFF >> (bitsToSet - 1))));
        }
    }
}
