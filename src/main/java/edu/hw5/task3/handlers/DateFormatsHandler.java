package edu.hw5.task3.handlers;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateFormatsHandler {
    private DateFormatsHandler nextHandler;

    public static DateFormatsHandler link(DateFormatsHandler first, DateFormatsHandler... chain) {
        DateFormatsHandler head = first;

        for (DateFormatsHandler nextInChain: chain) {
            head.nextHandler = nextInChain;
            head = nextInChain;
        }

        return first;
    }

    public abstract Optional<LocalDate> parse(String date);

    protected Optional<LocalDate> parseNext(String date) {
        if (nextHandler == null) {
            return Optional.empty();
        }

        return nextHandler.parse(date);
    }
}
