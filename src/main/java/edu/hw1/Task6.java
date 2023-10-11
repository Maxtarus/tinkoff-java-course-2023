package edu.hw1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class Task6 {
    private static final int KAPREKAR_CONSTANT = 6174;
    private static final int MIN_FOUR_DIGIT_NUMBER = 1000;
    private static final int MAX_FOUR_DIGITS_NUMBER = 9999;
    private static int stepsNumber;

    static {
        stepsNumber = 0;
    }

    public static void setZeroStepsNumber() {
        stepsNumber = 0;
    }

    private Task6() {
    }

    public static int countK(int number) {
        if (stepsNumber == 0) {
            if (!isSuitableNumber(number)) {
                throw new IllegalArgumentException("Данное число не подходит под условие задачи!");
            }

            if (number == KAPREKAR_CONSTANT) {
                return 0;
            }
        }

        int difference = kaprekarFunction(number);
        stepsNumber++;

        if (difference != KAPREKAR_CONSTANT) {
            countK(difference);
        }

        return stepsNumber;
    }

    private static int kaprekarFunction(int number) {
        int largestNumber = getLargestNumberFromDigits(number);
        int smallestNumber = getSmallestNumberFromDigits(number);
        return largestNumber - smallestNumber;
    }

    private static int getLargestNumberFromDigits(int number) {
        String strNumber = String.valueOf(number);
        String[] digits = new String[strNumber.length()];

        int i = 0;
        for (char c : strNumber.toCharArray()) {
            digits[i++] = String.valueOf(c);
        }

        Arrays.sort(digits, Comparator.reverseOrder());
        return Integer.parseInt(String.join("", digits));
    }

    private static int getSmallestNumberFromDigits(int number) {
        String strNumber = String.valueOf(number);
        String[] digits = new String[strNumber.length()];

        int i = 0;
        for (char c : strNumber.toCharArray()) {
            digits[i++] = String.valueOf(c);
        }

        Arrays.sort(digits);

        return Integer.parseInt(Arrays.stream(digits).filter(s -> !s.equals("0")).collect(Collectors.joining()));
    }

    private static boolean isSuitableNumber(int number) {
        String strNumber = String.valueOf(number);
        boolean allDigitsInNumberAreSame = strNumber.chars().allMatch(c -> strNumber.charAt(0) == c);
        return number > MIN_FOUR_DIGIT_NUMBER && number < MAX_FOUR_DIGITS_NUMBER
            && !allDigitsInNumberAreSame;
    }
}
