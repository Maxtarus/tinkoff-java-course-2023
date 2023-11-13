package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.Optional;

public class DateInNaturalLanguageFormatsHandler extends DateFormatsHandler {
    @Override
    public Optional<LocalDate> parse(String date) {
        return switch (date) {
            case "yesterday", "1 day ago" -> Optional.of(LocalDate.now().minusDays(1));
            case "today" -> Optional.of(LocalDate.now());
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            default -> parseNext(date);
        };
    }
}
