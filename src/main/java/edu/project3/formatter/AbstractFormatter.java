package edu.project3.formatter;

import java.util.List;

public abstract class AbstractFormatter implements FormatPrinter {
    protected static final String REGEX_SPLITTER = "\\|";
    protected static final String SPLITTER = "|";

    protected int[] getMaxSpacesForEachColumn(List<String> table) {
        int columnsCount = table.get(0).split(REGEX_SPLITTER).length;
        int[] maxSpaces = new int[columnsCount];
        for (String row : table) {
            String[] rowString = row.split(REGEX_SPLITTER);
            for (int i = 0; i < rowString.length; i++) {
                if (rowString[i].length() > maxSpaces[i]) {
                    maxSpaces[i] = rowString[i].length();
                }
            }
        }
        return maxSpaces;
    }

    protected void appendHeader(StringBuilder metricSb, String headerLine, String header) {
        metricSb.append(headerLine).append(header).append("\n".repeat(2));
    }
}
