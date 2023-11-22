package edu.hw5.task8;

import java.util.Objects;
import java.util.regex.Pattern;

public final class BonusAlphabetConsistingOfZerosAndOnesValidator {
    //нечетной длины
    private static final Pattern FIRST_PATTERN = Pattern.compile("^[01]([01]{2})*$");
    //начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    private static final Pattern SECOND_PATTERN = Pattern.compile("^(0([01]{2})*)|(1[01]([01]{2})*)$");
    //количество 0 кратно 3
    private static final Pattern THIRD_PATTERN = Pattern.compile("^((1*01*01*01*)+)|(1+)$");
    //любая строка, кроме 11 или 111
    private static final Pattern FOURTH_PATTERN = Pattern.compile("^(?!11$|111$)[01]*$");
    //каждый нечетный символ равен 1
    private static final Pattern FIFTH_PATTERN = Pattern.compile("^(1[01])*1?$");
    //содержит не менее двух 0 и не более одной 1
    private static final Pattern SIXTH_PATTERN = Pattern.compile("^(1?0{2,})|(0+1?0+)|(0{2,}1?)$");
    //нет последовательных 1
    private static final Pattern SEVENTH_PATTERN = Pattern.compile("^((?!11)[01]?(?!11))+$");

    private BonusAlphabetConsistingOfZerosAndOnesValidator() {}

    public static boolean isOddLength(String string) {
        Objects.requireNonNull(string);
        return FIRST_PATTERN.matcher(string).matches();
    }

    public static boolean isStartedWithZeroAndOddLengthOrStartedWithOneAndEvenLength(String string) {
        Objects.requireNonNull(string);
        return SECOND_PATTERN.matcher(string).matches();
    }

    public static boolean isNumberOfZerosDivisibleByThree(String string) {
        Objects.requireNonNull(string);
        return THIRD_PATTERN.matcher(string).matches();
    }

    public static boolean isAnyStringExceptTwoOrThreeOnesInRow(String string) {
        Objects.requireNonNull(string);
        return FOURTH_PATTERN.matcher(string).matches();
    }

    public static boolean isEveryOddSymbolIsOne(String string) {
        Objects.requireNonNull(string);
        return FIFTH_PATTERN.matcher(string).matches();
    }

    public static boolean doesContainAtLeastTwoZerosAndLessThanTwoOnes(String string) {
        Objects.requireNonNull(string);
        return SIXTH_PATTERN.matcher(string).matches();
    }

    public static boolean hasNotTwoOnesInRow(String string) {
        Objects.requireNonNull(string);
        return SEVENTH_PATTERN.matcher(string).matches();
    }
}
