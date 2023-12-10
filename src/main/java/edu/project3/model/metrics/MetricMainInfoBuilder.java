package edu.project3.model.metrics;

import edu.project3.model.Log;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MetricMainInfoBuilder implements MetricBuilder {
    private final OffsetDateTime startDate;
    private final OffsetDateTime endDate;
    private final List<String> paths;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public MetricMainInfoBuilder(OffsetDateTime startDate, OffsetDateTime endDate, List<String> paths) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.paths = paths;
    }

    @Override
    public Metric build(List<Log> logs) {
        return buildMainMetricInfo(logs, startDate, endDate, paths);
    }

    private Metric buildMainMetricInfo(
        List<Log> logs,
        OffsetDateTime startDate,
        OffsetDateTime endDate,
        List<String> paths
    ) {
        List<String> metricList = new ArrayList<>();
        metricList.add("Метрика|Значение");
        String logNames = String.join("`, `", paths);
        metricList.add("Файл(ы)|" + "`" + logNames + "`");
        metricList.add("Начальная дата|" + (startDate.equals(OffsetDateTime.MIN) ? "-" : startDate.format(formatter)));
        metricList.add("Конечная дата|" + (endDate.equals(OffsetDateTime.MAX) ? "-" : endDate.format(formatter)));
        metricList.add("Количество запросов|" + logs.size());
        double averageBytesAnswer = logs.stream().mapToInt(log -> log.response().bytesSend()).average().getAsDouble();
        metricList.add("Средний размер ответа|" + averageBytesAnswer);

        return new Metric("Общая информация", metricList);
    }
}
