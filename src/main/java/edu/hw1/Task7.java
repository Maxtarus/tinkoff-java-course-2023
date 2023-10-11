package edu.hw1;

public final class Task7 {

    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        checkNumberAndShift(n, shift);
        String binaryString = Integer.toBinaryString(n);
        int binaryStringLength = binaryString.length();
        int remainder = shift % binaryStringLength;
        binaryString = binaryString.substring(remainder) + binaryString.substring(0, remainder);
        return Integer.parseInt(binaryString, 2);
    }

    public static int rotateRight(int n, int shift) {
        checkNumberAndShift(n, shift);
        String binaryString = Integer.toBinaryString(n);
        int binaryStringLength = binaryString.length();
        int remainder = shift % binaryStringLength;
        binaryString = binaryString.substring(binaryStringLength - remainder)
            + binaryString.substring(0, binaryStringLength - remainder);
        return Integer.parseInt(binaryString, 2);
    }

    private static void checkNumberAndShift(int n, int shift) {
        if (n <= 0 || shift < 0) {
            throw new IllegalArgumentException("Число должно положительным, а размер сдвига неотрицательным!");
        }
    }
}



