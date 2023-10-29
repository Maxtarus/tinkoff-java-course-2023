package edu.hw3.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест преобразования числа, записанного в арабской форме, в римскую форму записи")
class RomanNumeralsUtilsTest {
    @Test
    @DisplayName("Допустимое число (1<=n<=3999)")
    void convertToRoman_shouldReturnNumberInRomanNumerals_whenValidNumber() {
        //given
        int arabicNumber1 = 1;
        int arabicNumber2 = 1449;
        int arabicNumber3 = 3999;

        String expectedRomanNumber1 = "I";
        String expectedRomanNumber2 = "MCDXLIX";
        String expectedRomanNumber3 = "MMMCMXCIX";

        //when
        String actualRomanNumber1 = RomanNumeralsUtils.convertToRoman(arabicNumber1);
        String actualRomanNumber2 = RomanNumeralsUtils.convertToRoman(arabicNumber2);
        String actualRomanNumber3 = RomanNumeralsUtils.convertToRoman(arabicNumber3);

        //then
        assertThat(actualRomanNumber1).isEqualTo(expectedRomanNumber1);
        assertThat(actualRomanNumber2).isEqualTo(expectedRomanNumber2);
        assertThat(actualRomanNumber3).isEqualTo(expectedRomanNumber3);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 4000})
    @DisplayName("Недопустимое число")
    void convertToRoman_shouldThrowIllegalArgumentException_whenNotValidNumber(int number) {
        assertThrows(IllegalArgumentException.class, () -> RomanNumeralsUtils.convertToRoman(number));
    }
}
