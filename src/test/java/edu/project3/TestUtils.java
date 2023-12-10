package edu.project3;

import edu.project3.model.Log;
import edu.project3.model.Request;
import edu.project3.model.Response;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class TestUtils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
        "dd/LLL/yyyy:HH:mm:ss ZZ",
        Locale.US
    );

    public static final List<Log> logsForTests = List.of(
        new Log(
            "11.71.87.42",
            "-",
            OffsetDateTime.parse("23/Sep/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
            new Request(
                "GET",
                "/multi-state/orchestration.png",
                "HTTP/1.1",
                "Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_8_5) AppleWebKit/5312 (KHTML, like Gecko) Chrome/39.0.812.0 Mobile Safari/5312"
            ),
            new Response(400, 38)
        ),
        new Log(
            "141.96.175.104",
            "-",
            OffsetDateTime.parse("25/Sep/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
            new Request(
                "GET",
                "/architecture/attitude-oriented/success/Cross-platform-neutral.css",
                "HTTP/1.1",
                "Mozilla/5.0 (Macintosh; PPC Mac OS X 10_5_9) AppleWebKit/5321 (KHTML, like Gecko) Chrome/39.0.800.0 Mobile Safari/5321"
            ),
            new Response(200, 2134)
        ),
        new Log(
            "165.138.198.30",
            "-",
            OffsetDateTime.parse("27/Sep/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
            new Request(
                "GET",
                "/info-mediaries.php",
                "HTTP/1.1",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_6 rv:4.0; en-US) AppleWebKit/533.42.6 (KHTML, like Gecko) Version/4.0 Safari/533.42.6"
            ),
            new Response(200, 2778)
        ),
        new Log(
            "185.253.246.248",
            "-",
            OffsetDateTime.parse("30/Sep/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
            new Request(
                "GET",
                "/Focused-encoding.svg",
                "HTTP/1.1",
                "Mozilla/5.0 (Windows; U; Windows NT 4.0) AppleWebKit/532.48.2 (KHTML, like Gecko) Version/4.2 Safari/532.48.2"
            ),
            new Response(200, 2468)
        ),
        new Log(
            "204.196.83.88",
            "-",
            OffsetDateTime.parse("02/Oct/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
            new Request(
                "PUT",
                "/Future-proofed/Customer-focused/Upgradable/internet%20solution_Re-contextualized.css",
                "HTTP/1.1",
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/5360 (KHTML, like Gecko) Chrome/38.0.850.0 Mobile Safari/5360"
            ),
            new Response(200, 992)
        ),
        new Log(
            "72.153.133.99",
            "-",
            OffsetDateTime.parse("05/Oct/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
            new Request(
                "GET",
                "/exuding-Secured/contingency%20Future-proofed.css",
                "HTTP/1.1",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2 rv:3.0) Gecko/1969-17-05 Firefox/35.0"
            ),
            new Response(200, 2024)
        ),
        new Log(
            "72.153.133.97",
            "-",
            OffsetDateTime.parse("07/Oct/2023:06:10:36 +0000", DATE_TIME_FORMATTER),
            new Request(
                "GET",
                "/exuding-Secured/contingency%20Future-proofed.css",
                "HTTP/1.1",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2 rv:3.0) Gecko/1969-17-05 Firefox/35.0"
            ),
            new Response(200, 2024)
        )
    );
}
