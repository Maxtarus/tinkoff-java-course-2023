package edu.project3.metrics;

import edu.project3.model.metrics.Metric;
import edu.project3.model.metrics.MetricBuilder;
import edu.project3.model.metrics.MetricRequestMethodsInfoBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.project3.TestUtils.logsForTests;
import static org.assertj.core.api.Assertions.assertThat;

public class MetricRequestMethodsInfoBuilderTest {
    @Test
    @DisplayName("One file request metric build test")
    public void build_shouldReturnMetricForResourcesInfo() {
        MetricBuilder metricBuilder = new MetricRequestMethodsInfoBuilder();
        assertThat(metricBuilder.build(logsForTests)).isEqualTo(
            new Metric(
                "HTTP запросы", List.of
                (
                    "HTTP запрос|Количество",
                    "GET|6",
                    "PUT|1"
                )
            )
        );
    }
}
