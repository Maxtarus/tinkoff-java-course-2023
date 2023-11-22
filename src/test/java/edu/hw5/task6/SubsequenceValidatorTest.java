package edu.hw5.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubsequenceValidatorTest {
    @DisplayName("Строка является подпоследовательностью другой строки")
    @Test
    void testIsSubsequence_shouldReturnTrue() {
        // Arrange
        String subsequence = "abc";
        String string = "achfdbaabgabcaabg";

        // Act
        boolean result = SubsequenceValidator.isSubsequence(subsequence, string);

        // Assert
        assertThat(result).isTrue();
    }

    @DisplayName("Строка не является подпоследовательностью другой строки")
    @Test
    void testIsSubsequence_shouldReturnFalse() {
        // Arrange
        String subsequence = "abc";
        String string = "achfdbaabgaxcaabg";

        // Act
        boolean result = SubsequenceValidator.isSubsequence(subsequence, string);

        // Assert
        assertThat(result).isFalse();
    }


    @DisplayName("Null-строка")
    @Test
    public void testIsSubsequence_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class,
            () -> SubsequenceValidator.isSubsequence(null, null));
    }
}
