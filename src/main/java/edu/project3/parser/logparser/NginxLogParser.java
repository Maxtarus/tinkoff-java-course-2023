package edu.project3.parser.logparser;


import edu.project3.model.Log;
import edu.project3.model.Request;
import edu.project3.model.Response;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NginxLogParser implements LogParser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
        "dd/LLL/yyyy:HH:mm:ss ZZ",
        Locale.US
    );
    private static final Pattern LOG_PATTERN =
        Pattern.compile("(.*) - (.*) \\[(.*)\\] \"(.*) (.*) (.*)\" (\\d+) (\\d+) \"(.*)\" \"(.*)\"");

    @Override
    public List<Log> parseLogs(List<String> logs) {
        return logs.stream().map(this::parse).toList();
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private Log parse(String logInfo) {
        Matcher matcher = LOG_PATTERN.matcher(logInfo);
        if (!matcher.matches()) {
            throw new IllegalStateException("Log " + logInfo + " is invalid");
        }
        Request request = new Request(matcher.group(4), matcher.group(5), matcher.group(6), matcher.group(10));
        Response response = new Response(Integer.parseInt(matcher.group(7)), Integer.parseInt(matcher.group(8)));
        return new Log(
            matcher.group(1),
            matcher.group(2),
            OffsetDateTime.parse(matcher.group(3), DATE_TIME_FORMATTER),
            request,
            response
        );
    }
}
