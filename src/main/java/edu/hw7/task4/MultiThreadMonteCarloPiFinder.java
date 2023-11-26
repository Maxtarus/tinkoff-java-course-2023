package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class MultiThreadMonteCarloPiFinder implements MonteCarloPiFinder {
    private final long totalPointsCount;
    private final int threadsNumber;

    public MultiThreadMonteCarloPiFinder(long totalPointsCount, int threadsNumber) {
        this.totalPointsCount = totalPointsCount;
        this.threadsNumber = threadsNumber;
    }

    @Override
    public double calculatePi() {
        return SCALE_FACTOR * countTotalPointsInsideCircle() / totalPointsCount;
    }

    @Override
    public long countTotalPointsInsideCircle() {
        List<Future<Long>> tasksResults = getTasksResults();
        long totalPointsInsideCircleCount = 0;

        for (Future<Long> taskResult : tasksResults) {
            try {
                totalPointsInsideCircleCount += taskResult.get();
            } catch (InterruptedException | ExecutionException ignored) {
            }
        }

        return totalPointsInsideCircleCount;
    }

    private List<Future<Long>> getTasksResults() {
        List<Future<Long>> tasksResults = new ArrayList<>(threadsNumber);
        ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);

        for (int i = 0; i < threadsNumber; i++) {
            Future<Long> taskResult = executorService.submit(
                () -> countPointsInsideCircle(totalPointsCount / threadsNumber)
            );
            tasksResults.add(taskResult);
        }

        executorService.shutdown();

        return tasksResults;
    }

    private long countPointsInsideCircle(long pointsCount) {
        long pointsInsideCircleCount = 0;

        for (int i = 0; i < pointsCount; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            if (isPointInsideCircle(x, y)) {
                pointsInsideCircleCount++;
            }
        }
        return pointsInsideCircleCount;
    }
}
