package edu.project3.parser.logparser;

import edu.project3.model.Log;
import java.util.List;

public interface LogParser {
    List<Log> parseLogs(List<String> logInfo);
}
