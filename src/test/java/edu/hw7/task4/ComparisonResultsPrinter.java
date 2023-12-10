package edu.hw7.task4;

public final class ComparisonResultsPrinter {
    private ComparisonResultsPrinter() {}

    public static void printComparisonResult(
        long totalPointsCount,
        int threadsCount,
        double singlePi,
        double singleTotalExecutionTime,
        double singleDelta,
        double multiPi,
        double multiTotalExecutionTime,
        double multiDelta
    ) {
        String[] headers = {"Metric", "Single thread", "Several threads", "Time difference"};
        String[][] data = {
            {"Calculated PI", String.valueOf(singlePi), String.valueOf(multiPi), "-"},
            {"Delta", String.format("%.6f", singleDelta), String.format("%.6f", multiDelta), "-"},
            {"Execution time", String.valueOf(singleTotalExecutionTime), String.valueOf(multiTotalExecutionTime),
                String.format("%.5f sec", Math.abs(multiTotalExecutionTime - singleTotalExecutionTime))},
            {"Speedup", "1", String.format("%.2fx", singleTotalExecutionTime / multiTotalExecutionTime), "-"}
        };

        String markdownTable = generateMarkdownTable(headers, data);
        System.out.printf("**Comparison results for %d points and %d threads**%n%n", totalPointsCount, threadsCount);
        System.out.println(markdownTable + System.lineSeparator());
    }

    private static String generateMarkdownTable(String[] headers, String[][] data) {
        var table = new StringBuilder();

        // Header row
        for (String header : headers) {
            table.append("| ").append(header).append(" ");
        }

        table.append("|").append(System.lineSeparator());

        // Separator row
        for (String ignored : headers) {
            table.append("| --- ");
        }

        table.append("|").append(System.lineSeparator());

        // Data rows
        for (String[] row : data) {
            for (String cell : row) {
                table.append("| ").append(cell).append(" ");
            }

            table.append("|").append(System.lineSeparator());
        }

        return table.toString();
    }
}
