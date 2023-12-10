package edu.project3.formatter;

import edu.project3.model.metrics.Metric;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class MarkdownFormatterTest {

    @Test
    @DisplayName("Markdown format test")
    public void print_shouldReturnStringInMarkdownFormat() {
        FormatPrinter formatPrinter = new MarkdownFormatter();
        Metric metric = new Metric("Test", List.of("Test column|Number", "first|1", "second|2"));
        String formattedString = """
            #### Test

            |Test column|Number|
            |:---------:|:----:|
            |      first|     1|
            |     second|     2|
            """;
        assertThat(formatPrinter.print(metric)).isEqualTo(formattedString);
    }
}
