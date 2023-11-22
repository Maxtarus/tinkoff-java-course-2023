package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateWithSomeDaysAgoFormatsHandler extends DateFormatsHandler {
    private static final Pattern DATE_WITH_SOME_DAYS_AGO_PATTERN =
        Pattern.compile("^([2-9]|[1-9]\\d+) days ago$");

    @Override
    public Optional<LocalDate> parse(String date) {
        Matcher dateMatcher = DATE_WITH_SOME_DAYS_AGO_PATTERN.matcher(date);

        if (dateMatcher.matches()) {
            return Optional.of(LocalDate.now().minusDays(Long.parseLong(dateMatcher.group(1))));
        } else {
            return parseNext(date);
        }
    }
}
