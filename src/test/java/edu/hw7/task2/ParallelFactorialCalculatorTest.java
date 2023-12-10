package edu.hw7.task2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParallelFactorialCalculatorTest {
    @Test
    void testFactorial_shouldGetResult() {
        // Arrange
        int n = 15;
        long expected = 1307674368000L;

        // Act
        long result = ParallelFactorialCalculator.culculateFactorial(n);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testFactorial_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
            () -> ParallelFactorialCalculator.culculateFactorial(-1));
    }
}
