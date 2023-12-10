package edu.project3.parser.argsparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ArgsParser {
    private ArgsParser() {
    }

    public static ParseFormat getParseFormat(String args) {
        String path = "";
        String fromDate = "";
        String toDate = "";
        String format = "";
        Pattern pattern = Pattern.compile("--(path|from|to|format)\\s(\\S+)");
        Matcher matcher = pattern.matcher(args);
        while (matcher.find()) {
            String command = matcher.group(1);
            String value = matcher.group(2);
            switch (command) {
                case "path":
                    path = value;
                    break;
                case "from":
                    fromDate = value;
                    break;
                case "to":
                    toDate = value;
                    break;
                case "format":
                    format = value;
                    break;
                default:
                    break;
            }
        }
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Path shouldn't be empty");
        }
        return new ParseFormat(path, fromDate, toDate, format);
    }
}
