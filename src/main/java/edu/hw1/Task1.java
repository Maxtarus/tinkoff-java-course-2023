package edu.hw1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task1 {
    private static final int SECONDS_PER_MINUTE = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Строка не может быть null!");
        }

        String videoLength = str.trim();

        //Проверка на пустую строку с учётом пробелов
        if (videoLength.isEmpty()) {
            return -1;
        }

        String[] minutesAndSeconds = videoLength.split(":");
        String minutes = minutesAndSeconds[0];
        String seconds = minutesAndSeconds[1];

        String regex = "^(\\d|0\\d|[1-9]\\d+):[0-5]\\d$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(videoLength);

        //Проверка на соответсвие шаблону regex
        if (!matcher.matches()) {
            return -1;
        }

        //если формат времени типа "05:15" (после нуля ровно одна цифра до двоеточия), ноль не учитываем
        if (minutes.startsWith("0") && minutes.length() == 2) {
            minutes = minutes.substring(1);
        }

        //если формат времени типа "15:05" (после двоеточия и следующего за ним нуля ровно одна), ноль не учитываем
        if (seconds.startsWith("0") && seconds.length() == 2) {
            seconds = seconds.substring(1);
        }

        final int minutesNumber = Integer.parseInt(minutes);
        final int secondsNumber = Integer.parseInt(seconds);
        return minutesNumber * SECONDS_PER_MINUTE + secondsNumber;
    }
}
