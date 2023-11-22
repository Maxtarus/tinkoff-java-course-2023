package edu.hw5.task1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ComputerClubStatisticsCalculatorTest {
    @Test
    void testCalculateAverageTimePerSession() {
        // Arrange
        String sessionsInfo = """
                2022-03-12, 20:20 - 2022-03-12, 23:50
                2022-04-01, 21:30 - 2022-04-02, 01:20
                """;
        String expectedAverageTimePerSession = "3 ч 40 мин";

        // Act
        String actualAverageTimePerSession =
            ComputerClubStatisticsCalculator.calculateAverageTimePerSession(sessionsInfo);

        // Assert
        assertThat(actualAverageTimePerSession).isEqualTo(expectedAverageTimePerSession);
    }

}
