package edu.hw5.task7;

import java.util.Objects;
import java.util.regex.Pattern;

public final class AlphabetConsistingOfZerosAndOnesValidator {
    //содержит не менее 3 символов, причем третий символ равен 0
    private static final Pattern FIRST_PATTERN = Pattern.compile("^[01]{2}0[01]*$");
    //начинается и заканчивается одним и тем же символом
    private static final Pattern SECOND_PATTERN = Pattern.compile("^0|1|0[01]*0|1[01]*1$");
    //длина не менее 1 и не более 3
    private static final Pattern THIRD_PATTERN = Pattern.compile("^[01]{1,3}$");

    private AlphabetConsistingOfZerosAndOnesValidator() {}


    public static boolean isContainsAtLeastThreeCharactersAndThirdCharacterIsZero(String string) {
        Objects.requireNonNull(string);
        return FIRST_PATTERN.matcher(string).matches();
    }

    public static boolean isStartsAndEndsWithSameCharacter(String string) {
        Objects.requireNonNull(string);
        return SECOND_PATTERN.matcher(string).matches();
    }

    public static boolean isLengthAtLeastOneAndLessThanThree(String string) {
        Objects.requireNonNull(string);
        return THIRD_PATTERN.matcher(string).matches();
    }
}
