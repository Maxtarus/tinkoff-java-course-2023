package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DatesSearcher {
    private static final int FIRST_DAY_OF_JANUARY = 1;
    private static final int LAST_DAY_OF_DECEMBER = 31;
    private static final Map<String, DayOfWeek> DAY_OF_WEEK_MAP = Map.of(
        "MONDAY", DayOfWeek.MONDAY,
        "TUESDAY", DayOfWeek.TUESDAY,
        "WEDNESDAY", DayOfWeek.WEDNESDAY,
        "THURSDAY", DayOfWeek.THURSDAY,
        "FRIDAY", DayOfWeek.FRIDAY,
        "SATURDAY", DayOfWeek.SATURDAY,
        "SUNDAY", DayOfWeek.SUNDAY
    );

    private DatesSearcher() {}

    private static boolean isDayOfWeekAndDayOfMonth(LocalDate date, DayOfWeek dayOfWeek, int dayOfMonth) {
        return date.getDayOfWeek().equals(dayOfWeek) && date.getDayOfMonth() == dayOfMonth;
    }

    public static List<LocalDate> getDatesWithDayOfWeekAndDayOfMonthInYear(
        String dayOfWeekStr, int dayOfMonth, int year) {
        List<LocalDate> dates = new ArrayList<>();

        DayOfWeek dayOfWeek = DAY_OF_WEEK_MAP.get(dayOfWeekStr.toUpperCase());
        LocalDate start = LocalDate.of(year, Month.JANUARY, FIRST_DAY_OF_JANUARY);
        LocalDate end = LocalDate.of(year, Month.DECEMBER, LAST_DAY_OF_DECEMBER);

        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            if (isDayOfWeekAndDayOfMonth(date, dayOfWeek, dayOfMonth)) {
                dates.add(date);
            }
        }

        return dates;
    }

    public static LocalDate getNearestNextDateWithDayOfWeekAndDayOfMonth(
        LocalDate date, String dayOfWeekStr, int dayOfMonth) {
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.ofDateAdjuster(temporal -> {
            DayOfWeek dayOfWeek = DAY_OF_WEEK_MAP.get(dayOfWeekStr.toUpperCase());
            LocalDate currentDate = temporal.plusDays(1);
            int days = 1;

            while (!isDayOfWeekAndDayOfMonth(currentDate, dayOfWeek, dayOfMonth)) {
                currentDate = currentDate.plusDays(1);
                days++;
            }

            return temporal.plusDays(days);
        });

        return date.with(temporalAdjuster);
    }
}
