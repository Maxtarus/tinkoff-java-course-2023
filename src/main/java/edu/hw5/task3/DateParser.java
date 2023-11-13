package edu.hw5.task3;

import edu.hw5.task3.handlers.DateFormatsHandler;
import edu.hw5.task3.handlers.DateInNaturalLanguageFormatsHandler;
import edu.hw5.task3.handlers.DateSeparatedByDashFormatsHandler;
import edu.hw5.task3.handlers.DateSeparatedBySlashFormatsHandler;
import edu.hw5.task3.handlers.DateWithSomeDaysAgoFormatsHandler;
import java.time.LocalDate;
import java.util.Optional;

public final class DateParser {
    private static final DateFormatsHandler DATE_PARSER = DateFormatsHandler.link(
        new DateInNaturalLanguageFormatsHandler(),
        new DateSeparatedByDashFormatsHandler(),
        new DateSeparatedBySlashFormatsHandler(),
        new DateWithSomeDaysAgoFormatsHandler()
    );

    private DateParser() {}

    public static Optional<LocalDate> parseDate(String date) {
        if (date == null || date.isEmpty()) {
            return Optional.empty();
        }

        return DATE_PARSER.parse(date);
    }
}
