package edu.project3.model.metrics;

import edu.project3.model.Log;
import java.util.List;

public interface MetricBuilder {
    Metric build(List<Log> logs);
}
