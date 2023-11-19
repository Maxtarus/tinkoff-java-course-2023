package edu.project3.model.metrics;

import edu.project3.model.Log;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricIpInfoBuilder implements MetricBuilder {
    private final Map<String, OffsetDateTime> lastDates = new HashMap<>();
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private static final int MAX_INFO = 20;

    @Override
    public Metric build(List<Log> logs) {
        List<String> metricList = new ArrayList<>();
        metricList.add("Часто встречающийся IP адрес|Количество запросов|Время последнего запроса");
        Map<String, Integer> ipCounter = getMapOfIp(logs);
        List<Map.Entry<String, Integer>> sortedEntries = ipCounter.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(MAX_INFO)
            .toList();
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            metricList.add(
                entry.getKey() + "|" + entry.getValue() + "|" + lastDates.get(entry.getKey()).format(dateFormat));
        }
        return new Metric("IP адреса", metricList);
    }

    private Map<String, Integer> getMapOfIp(List<Log> logs) {
        List<Log> mutableLogs = new ArrayList<>(logs);
        Map<String, Integer> ipCounter = new HashMap<>();
        mutableLogs.sort(Comparator.comparing(Log::date));
        for (Log log : mutableLogs) {
            String ip = log.ip();
            lastDates.put(ip, log.date());
            if (!ipCounter.containsKey(ip)) {
                ipCounter.put(ip, 1);
            } else {
                ipCounter.put(ip, ipCounter.get(ip) + 1);
            }
        }
        return ipCounter;
    }
}
