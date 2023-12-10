package edu.project3.printer;

import edu.project3.model.metrics.Metric;
import edu.project3.printer.MetricPrinter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MetricPrinterTest {
    @Test
    @DisplayName("Wrong format test")
    public void printMetrics_shouldThrowException_whenFormatIsNotCorrect() {
        MetricPrinter printer = new MetricPrinter();
        assertThatThrownBy(() -> printer.printMetrics("notmarkdown", List.of(
            new Metric("Test", List.of("aboba|aboba"))
        )));
    }
}
