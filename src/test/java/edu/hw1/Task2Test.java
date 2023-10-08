package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Нахождение количества цифр в десятичной форме целого числа")
class Task2Test {
    @Test
    @DisplayName("Четырёхзначное положительное число")
    void countDigits_shouldGetDigitsNumber_whenFourDigitPositiveNumber() {
        // given
        long number = 4666L;

        // when
        int digitsNumber = Task2.countDigits(number);

        // then
        assertThat(digitsNumber).isEqualTo(4);
    }

    @Test
    @DisplayName("Двухзначное отрицательное число")
    void countDigits_shouldGetDigitsNumber_whenTwoDigitNegativeNumber() {
        // given
        long number = -31L;

        // when
        int digitsNumber = Task2.countDigits(number);

        // then
        assertThat(digitsNumber).isEqualTo(2);
    }

    @Test
    @DisplayName("Число ноль")
    void countDigits_shouldGetDigitsNumber_whenZero() {
        // given
        long number = 0L;

        // when
        int digitsNumber = Task2.countDigits(number);

        // then
        assertThat(digitsNumber).isEqualTo(1);
    }
}
