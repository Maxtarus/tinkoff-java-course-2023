package edu.hw5.task8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BonusAlphabetConsistingOfZerosAndOnesValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {
        "0", "1",
        "100", "110",
        "11011", "00110"
    })
    void testIsOddLength_shouldReturnTrue(String validString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator.isOddLength(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "00", "10", "",
        "1000", "1101",
        "110111", "001100"
    })
    void testIsOddLength_shouldReturnFalse(String invalidString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator.isOddLength(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0", "000", "00100",
        "10", "1111", "100010"
    })
    void testIsStartedWithZeroAndOddLengthOrStartedWithOneAndEvenLength_shouldReturnTrue(String validString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator
            .isStartedWithZeroAndOddLengthOrStartedWithOneAndEvenLength(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "1", "100", "10100",
        "00", "0111", "000010"
    })
    void testIsStartedWithZeroAndOddLengthOrStartedWithOneAndEvenLength_shouldReturnFalse(String invalidString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator
            .isStartedWithZeroAndOddLengthOrStartedWithOneAndEvenLength(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"000", "1000", "100010010", "1"})
    void testIsNumberOfZerosDivisibleByThree_shouldReturnTrue(String validString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator
            .isNumberOfZerosDivisibleByThree(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"00", "10", "10001001"})
    void testIsNumberOfZerosDivisibleByThree_shouldReturnFalse(String invalidString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator
            .isNumberOfZerosDivisibleByThree(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0", "1",
        "01", "10",
        "011", "110",
        "1100", "1110", "0111"})
    void testIsAnyStringExceptTwoOrThreeOnesInRow_shouldReturnTrue(String validString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator
            .isAnyStringExceptTwoOrThreeOnesInRow(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "111"})
    void testIsAnyStringExceptTwoOrThreeOnesInRow_shouldReturnFalse(String invalidString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator
            .isAnyStringExceptTwoOrThreeOnesInRow(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "1",
        "10", "11",
        "101", "111",
        "1010", "1011", "1110", "1111"})
    void testIsEveryOddSymbolIsOne_shouldReturnTrue(String validString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator
            .isEveryOddSymbolIsOne(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0",
        "01", "00",
        "001", "110",
        "1000", "0011", "0100", "0101"})
    void testIsEveryOddSymbolIsOne_shouldReturnFalse(String invalidString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator
            .isEveryOddSymbolIsOne(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "001", "010", "100",
        "00", "000",
        "0001", "0010"
    })
    void testDoesContainAtLeastTwoZerosAndLessThanTwoOnes_shouldReturnTrue(String validString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator
            .doesContainAtLeastTwoZerosAndLessThanTwoOnes(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0", "1",
        "01", "10", "11",
        "0011", "01011"
    })
    void testDoesContainAtLeastTwoZerosAndLessThanTwoOnes_shouldReturnFalse(String invalidString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator
            .doesContainAtLeastTwoZerosAndLessThanTwoOnes(invalidString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "0", "1",
        "00", "01", "10",
        "000", "001", "010", "100", "101",
        "1010", "0101"
    })
    void testHasNotTwoOnesInRow_shouldReturnTrue(String validString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator
            .hasNotTwoOnesInRow(validString);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "11",
        "110", "011",
        "1100", "0011", "1011", "1101"
    })
    void testHasNotTwoOnesInRow_shouldReturnFalse(String validString) {
        // Act
        boolean result = BonusAlphabetConsistingOfZerosAndOnesValidator
            .hasNotTwoOnesInRow(validString);

        // Assert
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @NullSource
    void testIsValidStringForAnyPattern_shouldThrowNullPointerException(String string) {
        assertThrows(NullPointerException.class,
            () -> BonusAlphabetConsistingOfZerosAndOnesValidator
                .isOddLength(string));

        assertThrows(NullPointerException.class,
            () -> BonusAlphabetConsistingOfZerosAndOnesValidator
                .isStartedWithZeroAndOddLengthOrStartedWithOneAndEvenLength(string));

        assertThrows(NullPointerException.class,
            () -> BonusAlphabetConsistingOfZerosAndOnesValidator
                .isNumberOfZerosDivisibleByThree(string));

        assertThrows(NullPointerException.class,
            () -> BonusAlphabetConsistingOfZerosAndOnesValidator
                .isAnyStringExceptTwoOrThreeOnesInRow(string));

        assertThrows(NullPointerException.class,
            () -> BonusAlphabetConsistingOfZerosAndOnesValidator
                .isEveryOddSymbolIsOne(string));

        assertThrows(NullPointerException.class,
            () -> BonusAlphabetConsistingOfZerosAndOnesValidator
                .doesContainAtLeastTwoZerosAndLessThanTwoOnes(string));

        assertThrows(NullPointerException.class,
            () -> BonusAlphabetConsistingOfZerosAndOnesValidator
                .hasNotTwoOnesInRow(string));
    }

}
