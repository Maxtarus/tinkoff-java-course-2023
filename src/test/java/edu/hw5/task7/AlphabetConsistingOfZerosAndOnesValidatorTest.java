package edu.hw5.task7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AlphabetConsistingOfZerosAndOnesValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "000", "010",
        "100", "110",
        "11011"
    })
    void testIsValidStringForFirstPattern_shouldReturnTrue(String validString) {
        // Act
        boolean result = AlphabetConsistingOfZerosAndOnesValidator
            .isContainsAtLeastThreeCharactersAndThirdCharacterIsZero(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "001", "011",
        "101", "111",
        "11111", "00", ""
    })
    void testIsValidStringForFirstPattern_shouldReturnFalse(String invalidString) {
        // Act
        boolean result = AlphabetConsistingOfZerosAndOnesValidator
            .isContainsAtLeastThreeCharactersAndThirdCharacterIsZero(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "101", "010",
        "111", "000"
    })
    void testIsValidStringForSecondPattern_shouldReturnTrue(String validString) {
        // Act
        boolean result = AlphabetConsistingOfZerosAndOnesValidator
            .isStartsAndEndsWithSameCharacter(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "001", "110",
        "011", "100", ""
    })
    void testIsValidStringForSecondPattern_shouldReturnFalse(String invalidString) {
        // Act
        boolean result = AlphabetConsistingOfZerosAndOnesValidator
            .isStartsAndEndsWithSameCharacter(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0", "1",
        "00", "01", "10", "11",
        "000", "001", "010", "011",
        "100", "101", "110", "111"
    })
    void testIsValidStringForThirdPattern_shouldReturnTrue(String validString) {
        // Act
        boolean result = AlphabetConsistingOfZerosAndOnesValidator
            .isLengthAtLeastOneAndLessThanThree(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "", "1111"
    })
    void testIsValidStringForThirdPattern_shouldReturnFalse(String invalidString) {
        // Act
        boolean result = AlphabetConsistingOfZerosAndOnesValidator
            .isLengthAtLeastOneAndLessThanThree(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @NullSource
    void testIsValidStringForAnyPattern_shouldThrowNullPointerException(String string) {
        assertThrows(NullPointerException.class,
            () -> AlphabetConsistingOfZerosAndOnesValidator
                .isContainsAtLeastThreeCharactersAndThirdCharacterIsZero(string));

        assertThrows(NullPointerException.class,
            () -> AlphabetConsistingOfZerosAndOnesValidator
                .isStartsAndEndsWithSameCharacter(string));

        assertThrows(NullPointerException.class,
            () -> AlphabetConsistingOfZerosAndOnesValidator
                .isLengthAtLeastOneAndLessThanThree(string));
    }
}
