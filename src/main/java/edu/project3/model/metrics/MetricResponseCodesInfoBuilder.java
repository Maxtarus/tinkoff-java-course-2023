package edu.project3.model.metrics;

import edu.project3.model.Log;
import edu.project3.remote.HttpStatusCode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricResponseCodesInfoBuilder implements MetricBuilder {
    private static final int MAX_INFO = 20;

    @Override
    public Metric build(List<Log> logs) {
        List<String> metricList = new ArrayList<>();
        metricList.add("Код|Имя|Количество");
        Map<Integer, Integer> responseCodes = getMapOfResponseCodes(logs);
        List<Map.Entry<Integer, Integer>> sortedEntries = responseCodes.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(MAX_INFO)
            .toList();
        for (Map.Entry<Integer, Integer> entry : sortedEntries) {
            metricList.add(entry.getKey() + "|" + HttpStatusCode.getByValue(entry.getKey()) + "|" + entry.getValue());
        }
        return new Metric("Коды ответа", metricList);
    }

    private Map<Integer, Integer> getMapOfResponseCodes(List<Log> logs) {
        Map<Integer, Integer> responseCodes = new HashMap<>();
        for (Log log : logs) {
            int responseCode = log.response().code();
            if (!responseCodes.containsKey(responseCode)) {
                responseCodes.put(responseCode, 1);
            } else {
                responseCodes.put(responseCode, responseCodes.get(responseCode) + 1);
            }
        }
        return responseCodes;
    }
}
