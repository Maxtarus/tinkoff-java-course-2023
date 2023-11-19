package edu.project3.metrics;

import edu.project3.model.Log;
import edu.project3.model.metrics.Metric;
import edu.project3.model.metrics.MetricMainInfoBuilder;
import edu.project3.parser.logparser.LogParser;
import edu.project3.parser.logparser.NginxLogParser;
import edu.project3.receiver.Receiver;
import edu.project3.receiver.path.PathLogReceiver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import static edu.project3.TestUtils.logsForTests;
import static edu.project3.parser.pathparser.PathParser.getPaths;
import static org.assertj.core.api.Assertions.assertThat;

public class MetricMainInfoBuilderTest {
    private final OffsetDateTime fromDateOffset = OffsetDateTime.of(2023, 10, 5, 0, 0, 0, 0, ZoneOffset.UTC);
    private final OffsetDateTime toDateOffset = OffsetDateTime.of(2023, 10, 7, 0, 0, 0, 0, ZoneOffset.UTC);

    @Test
    @DisplayName("One file main metric build test")
    public void build_shouldReturnMetricForMainInfo() {
        MetricMainInfoBuilder metricBuilder = new MetricMainInfoBuilder(
            OffsetDateTime.MIN, OffsetDateTime.MAX,
            List.of(
                "src/main/resources/project3/log1.txt"
            )
        );
        assertThat(metricBuilder.build(logsForTests)).isEqualTo(
            new Metric(
                "Общая информация", List.of
                (
                    "Метрика|Значение", "Файл(ы)|`src/main/resources/project3/log1.txt`",
                    "Начальная дата|-", "Конечная дата|-",
                    "Количество запросов|7", "Средний размер ответа|1779.7142857142858"
                )
            )
        );
    }

    @Test
    @DisplayName("Two files with date main metric build test")
    public void build_shouldReturnMetricForMainInfoWithoutSomeLogs() {
        Receiver receiver;
        List<Path> pathsToLogs = getPaths("src/main/resources/project3/*");
        receiver = new PathLogReceiver(pathsToLogs);
        LogParser logParser = new NginxLogParser();
        List<Log> logs = filterLogsByDate(logParser.parseLogs(receiver.receive()));
        MetricMainInfoBuilder metricBuilder = new MetricMainInfoBuilder(
            fromDateOffset,
            toDateOffset,
            List.of(
                "src/main/resources/project3/log1.txt", "src/main/resources/project3/log2.txt"
            )
        );
        assertThat(metricBuilder.build(logs)).isEqualTo(
            new Metric(
                "Общая информация", List.of
                (
                    "Метрика|Значение",
                    "Файл(ы)|`src/main/resources/project3/log1.txt`, `src/main/resources/project3/log2.txt`",
                    "Начальная дата|05.10.2023",
                    "Конечная дата|07.10.2023",
                    "Количество запросов|4",
                    "Средний размер ответа|1479.0"
                )
            )
        );
    }

    private List<Log> filterLogsByDate(List<Log> logs) {
        return logs.stream()
            .filter(log -> (log.date().isBefore(toDateOffset) && log.date().isAfter(fromDateOffset)))
            .toList();
    }
}
