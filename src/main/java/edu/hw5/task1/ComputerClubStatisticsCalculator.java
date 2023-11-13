package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public final class ComputerClubStatisticsCalculator {
    private static final int MINUTES_IN_HOUR = 60;

    private ComputerClubStatisticsCalculator() {}

    private static List<Integer> getSessionDurationsInMinutes(String sessionsInfo) {
        String[] sessions = sessionsInfo.split("\\R");
        List<Integer> sessionDurations = new ArrayList<>(sessions.length);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

        for (String session : sessions) {
            String[] sessionInfo = session.split(" - ");
            String sessionStart = sessionInfo[0];
            String sessionEnd = sessionInfo[1];

            try {
                LocalDateTime sessionStartTime = LocalDateTime.parse(sessionStart, dtf);
                LocalDateTime sessionEndTime = LocalDateTime.parse(sessionEnd, dtf);
                Duration sessionDuration = Duration.between(sessionStartTime, sessionEndTime);
                int sessionDurationInMinutes = (int) sessionDuration.toMinutes();
                sessionDurations.add(sessionDurationInMinutes);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        return sessionDurations;
    }

    public static String calculateAverageTimePerSession(String sessionsInfo) {
        List<Integer> sessionDurations = getSessionDurationsInMinutes(sessionsInfo);
        int totalMinutes = sessionDurations.stream().reduce(0, Integer::sum);
        int sessionsAmount = sessionDurations.size();
        int averageSessionDurationInMinutes = totalMinutes / sessionsAmount;
        int hours = averageSessionDurationInMinutes / MINUTES_IN_HOUR;
        int minutes = averageSessionDurationInMinutes % MINUTES_IN_HOUR;
        return hours + " ч " + minutes + " мин";
    }
}
