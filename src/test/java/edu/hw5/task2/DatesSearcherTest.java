package edu.hw5.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class DatesSearcherTest {
    @DisplayName("Поиск дат, выпадающих на определённое число и день недели в заданном году")
    @Test
    void testGetDatesWithDayOfWeekAndDayOfMonthInYear() {
        // Arrange
        String dayOfWeek = "Friday";
        int dayOfMonth = 13;
        int year = 2024;
        List<LocalDate> expectedDates = List.of(
            LocalDate.of(2024, 9, 13),
            LocalDate.of(2024, 12, 13)
        );

        // Act
        List<LocalDate> actualDates =
            DatesSearcher.getDatesWithDayOfWeekAndDayOfMonthInYear(dayOfWeek, dayOfMonth, year);

        // Assert
        assertThat(actualDates).isEqualTo(expectedDates);
    }

    @DisplayName("Поиск ближайшей даты с тем же днём недели и числом месяца для заданной даты")
    @Test
    void testGetNearestNextDateWithDayOfWeekAndDayOfMonth() {
        // Arrange
        LocalDate date =  LocalDate.of(2024, 12, 15);
        String dayOfWeek = "Friday";
        int dayOfMonth = 13;

        LocalDate expectedDate = LocalDate.of(2025, 6, 13);

        // Act
        LocalDate actualDate =
            DatesSearcher.getNearestNextDateWithDayOfWeekAndDayOfMonth(date, dayOfWeek, dayOfMonth);

        // Assert
        assertThat(actualDate).isEqualTo(expectedDate);
    }
}
