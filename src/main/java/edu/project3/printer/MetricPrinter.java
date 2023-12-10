package edu.project3.printer;


import edu.project3.formatter.AdocFormatter;
import edu.project3.formatter.FormatPrinter;
import edu.project3.formatter.MarkdownFormatter;
import edu.project3.model.metrics.Metric;
import java.util.List;

public class MetricPrinter {
    private FormatPrinter getFormatPrinter(String format) {
        return switch (format) {
            case "markdown" -> new MarkdownFormatter();
            case "adoc" -> new AdocFormatter();
            default -> throw new IllegalStateException("Unexpected value: " + format);
        };
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public void printMetrics(String format, List<Metric> metrics) {
        FormatPrinter printer = getFormatPrinter(format);
        metrics.forEach(metric -> System.out.println(printer.print(metric)));
    }
}
