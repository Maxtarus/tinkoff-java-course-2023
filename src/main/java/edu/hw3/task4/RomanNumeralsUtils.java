package edu.hw3.task4;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("MagicNumber")
public final class RomanNumeralsUtils {
    private RomanNumeralsUtils() {
    }

    private static final int MIN_ROMAN_NUMERALS_NUMBER = 1;
    private static final int MAX_ROMAN_NUMERALS_NUMBER = 3999;
    private static final Map<String, Integer> ROMAN_NUMERALS = new LinkedHashMap<>();

    static {
        ROMAN_NUMERALS.put("M", 1000);
        ROMAN_NUMERALS.put("CM", 900);
        ROMAN_NUMERALS.put("D", 500);
        ROMAN_NUMERALS.put("CD", 400);
        ROMAN_NUMERALS.put("C", 100);
        ROMAN_NUMERALS.put("XC", 90);
        ROMAN_NUMERALS.put("L", 50);
        ROMAN_NUMERALS.put("XL", 40);
        ROMAN_NUMERALS.put("X", 10);
        ROMAN_NUMERALS.put("IX", 9);
        ROMAN_NUMERALS.put("V", 5);
        ROMAN_NUMERALS.put("IV", 4);
        ROMAN_NUMERALS.put("I", 1);
    }

    public static String convertToRoman(int number) {
        if (number < MIN_ROMAN_NUMERALS_NUMBER || number > MAX_ROMAN_NUMERALS_NUMBER) {
            throw new IllegalArgumentException("Число должно быть в промежутке от 1 до 3999");
        }

        StringBuilder romanNumber = new StringBuilder();
        int decreasingNumber = number;

        for (var entry : ROMAN_NUMERALS.entrySet()) {
            int arabicValue = entry.getValue();
            String romanValue = entry.getKey();

            while (decreasingNumber >= arabicValue) {
                romanNumber.append(romanValue);
                decreasingNumber -= arabicValue;
            }
        }

        return romanNumber.toString();
    }
}

