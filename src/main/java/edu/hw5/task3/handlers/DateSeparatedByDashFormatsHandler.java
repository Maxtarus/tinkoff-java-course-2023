package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateSeparatedByDashFormatsHandler extends DateFormatsHandler {
    private static final DateTimeFormatter DATE_SEPARATED_BY_DASH_PATTERN_1 =
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_SEPARATED_BY_DASH_PATTERN_2 =
        DateTimeFormatter.ofPattern("yyyy-M-d");

    @Override
    public Optional<LocalDate> parse(String date) {
        try {
            return Optional.of(LocalDate.parse(date, DATE_SEPARATED_BY_DASH_PATTERN_1));
        } catch (DateTimeParseException e) {
            try {
                return Optional.of(LocalDate.parse(date, DATE_SEPARATED_BY_DASH_PATTERN_2));
            } catch (DateTimeParseException ex) {
                return parseNext(date);
            }
        }
    }
}
