package edu.project3.metrics;

import edu.project3.model.metrics.Metric;
import edu.project3.model.metrics.MetricBuilder;
import edu.project3.model.metrics.MetricResponseCodesInfoBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.project3.TestUtils.logsForTests;
import static org.assertj.core.api.Assertions.assertThat;

public class MetricResponseCodesInfoBuilderTest {
    @Test
    @DisplayName("One file response metric build test")
    public void build_shouldReturnMetricForResponseInfo() {
        MetricBuilder metricBuilder = new MetricResponseCodesInfoBuilder();
        assertThat(metricBuilder.build(logsForTests)).isEqualTo(
            new Metric(
                "Коды ответа", List.of
                (
                    "Код|Имя|Количество",
                    "200|OK|6",
                    "400|Bad Request|1"
                )
            )
        );
    }
}
