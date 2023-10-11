package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Проверка, является ли исходное число или любой из его потомков длиной > 1 палиндромом")
class Task5Test {
    @Test
    @DisplayName("Исходное число является палиндромом")
    void isPalindromeDescendant_shouldReturnTrue_whenNumberIsPalindrome() {
        // given
        long number = 121L;

        // when
        boolean isPalindrome = Task5.isPalindromeDescendant(number);

        // then
        assertThat(isPalindrome).isEqualTo(true);
    }

    @Test
    @DisplayName("Потомок исходного числа является палиндромом")
    void isPalindromeDescendant_shouldReturnTrue_whenNumberDescendantIsPalindrome() {
        // given
        long number = 11211230L;

        // when
        boolean isPalindrome = Task5.isPalindromeDescendant(number);

        // then
        assertThat(isPalindrome).isEqualTo(true);
    }

    @Test
    @DisplayName("Ни исходное число, ни его потомок не является палиндромом")
    void isPalindromeDescendant_shouldReturnFalse_whenNumberDescendantIsNotPalindrome() {
        // given
        long number = 1234L;

        // when
        boolean isPalindrome = Task5.isPalindromeDescendant(number);

        // then
        assertThat(isPalindrome).isEqualTo(false);
    }

    @Test
    @DisplayName("Число содержит нечётное количество цифр и оно не палиндром")
    void isPalindromeDescendant_shouldReturnFalse_whenNumberIsNotPalindromeAndContainsOddDigitsNumber() {
        // given
        long number = 123L;

        // when
        boolean isPalindrome = Task5.isPalindromeDescendant(number);

        // then
        assertThat(isPalindrome).isEqualTo(false);
    }
}
