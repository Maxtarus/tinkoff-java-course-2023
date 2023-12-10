package edu.project3;

import edu.project3.model.Log;
import edu.project3.model.metrics.Metric;
import edu.project3.model.metrics.MetricBuilder;
import edu.project3.model.metrics.MetricIpInfoBuilder;
import edu.project3.model.metrics.MetricMainInfoBuilder;
import edu.project3.model.metrics.MetricRequestMethodsInfoBuilder;
import edu.project3.model.metrics.MetricResourcesInfoBuilder;
import edu.project3.model.metrics.MetricResponseCodesInfoBuilder;
import edu.project3.parser.argsparser.ParseFormat;
import edu.project3.parser.logparser.LogParser;
import edu.project3.parser.logparser.NginxLogParser;
import edu.project3.printer.MetricPrinter;
import edu.project3.receiver.Receiver;
import edu.project3.receiver.http.HttpLogReceiver;
import edu.project3.receiver.path.PathLogReceiver;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import static edu.project3.parser.argsparser.ArgsParser.getParseFormat;
import static edu.project3.parser.pathparser.PathParser.getPaths;

public class LogParserApplication {
    private OffsetDateTime fromDateOffset;
    private OffsetDateTime toDateOffset;

    public void run(String[] args) {
        ParseFormat parseFormat = getParseFormat(String.join(" ", args));
        Receiver receiver;
        List<String> paths = new ArrayList<>();
        if (parseFormat.path().startsWith("http")) {
            paths.add(parseFormat.path());
            receiver = new HttpLogReceiver(parseFormat.path());
        } else {
            List<Path> pathsToLogs = getPaths(parseFormat.path());
            paths = pathsToLogs.stream().map(Path::toString).toList();
            receiver = new PathLogReceiver(pathsToLogs);
        }
        getFromDate(parseFormat);
        getToDate(parseFormat);
        LogParser logParser = new NginxLogParser();
        List<Log> logs = filterLogsByDate(logParser.parseLogs(receiver.receive()));
        List<Metric> metrics = getAllMetrics(logs, paths);
        MetricPrinter printer = new MetricPrinter();
        printer.printMetrics(parseFormat.format(), metrics);
    }

    private List<Log> filterLogsByDate(List<Log> logs) {
        return logs.stream()
            .filter(log -> (log.date().isBefore(toDateOffset) && log.date().isAfter(fromDateOffset)))
            .toList();
    }

    private List<Metric> getAllMetrics(List<Log> logs, List<String> paths) {
        MetricBuilder metricMainInfoBuilder = new MetricMainInfoBuilder(
            fromDateOffset, toDateOffset, paths);
        MetricBuilder metricResourcesInfoBuilder = new MetricResourcesInfoBuilder();
        MetricBuilder metricResponseCodesInfoBuilder = new MetricResponseCodesInfoBuilder();
        MetricBuilder metricRequestMethodsInfoBuilder = new MetricRequestMethodsInfoBuilder();
        MetricBuilder metricIpInfoBuilder = new MetricIpInfoBuilder();
        return List.of(
            metricMainInfoBuilder.build(logs),
            metricResourcesInfoBuilder.build(logs),
            metricResponseCodesInfoBuilder.build(logs),
            metricRequestMethodsInfoBuilder.build(logs),
            metricIpInfoBuilder.build(logs)
        );
    }

    private void getFromDate(ParseFormat parseFormat) {
        if (!parseFormat.fromDate().isEmpty()) {
            LocalDate localDate = LocalDate.parse(parseFormat.fromDate());
            fromDateOffset = localDate.atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
        } else {
            fromDateOffset = OffsetDateTime.MIN;
        }
    }

    private void getToDate(ParseFormat parseFormat) {
        if (!parseFormat.toDate().isEmpty()) {
            LocalDate localDate = LocalDate.parse(parseFormat.toDate());
            toDateOffset = localDate.atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
        } else {
            toDateOffset = OffsetDateTime.MAX;
        }
    }
}
