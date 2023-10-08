package edu.hw1;

public final class Task2 {
    private static final int NUMBER_SYSTEM_BASE = 10;

    private Task2() {
    }

    public static int countDigits(long number) {
        //Проверка на 0
        if (number == 0) {
            return 1;
        }

        long nonNegativeNumber;

        //Проверка на отрицательное число
        if (number < 0) {
            nonNegativeNumber = -number;
        } else {
            nonNegativeNumber = number;
        }

        int digitsNumber = 0;

        while (nonNegativeNumber != 0) {
            digitsNumber++;
            nonNegativeNumber /= NUMBER_SYSTEM_BASE;
        }
        return digitsNumber;
    }
}
