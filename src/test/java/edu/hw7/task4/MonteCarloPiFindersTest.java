package edu.hw7.task4;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MonteCarloPiFindersTest {
    final double accuracy = 0.001;

    @ParameterizedTest
    @ValueSource(longs = {10_000_000L, 100_000_000L, 1_000_000_000L})
    @DisplayName("SingleThreadMonteCarloPiFinder test")
    void testSingleThreadMonteCarloPiFinder(long totalPointsCount) {
        // Arrange
        var piFinder = new SingleThreadMonteCarloPiFinder(totalPointsCount);

        // Act
        double calculatedPi = piFinder.calculatePi();

        // Assert
        assertTrue(Math.abs(Math.PI - calculatedPi) < accuracy);
    }

    @ParameterizedTest
    @ValueSource(longs = {10_000_000L, 100_000_000L, 1_000_000_000L})
    @DisplayName("MultiThreadMonteCarloPiFinder test")
    void testMultiThreadMonteCarloPiFinder(long totalPointsCount) {
        // Arrange
        int optimalThreadsCount = Runtime.getRuntime().availableProcessors();
        var piFinder = new MultiThreadMonteCarloPiFinder(totalPointsCount, optimalThreadsCount);

        // Act
        double calculatedPi = piFinder.calculatePi();

        // Assert
        assertTrue(Math.abs(Math.PI - calculatedPi) < accuracy);
    }

    @Disabled("Comparison test disabled")
    @ParameterizedTest(name = "Test {index} - Monte Carlo Pi calculation comparison for {0} points")
    @ValueSource(longs = {10_000_000L, 100_000_000L, 1_000_000_000L})
    @DisplayName("Monte Carlo Pi calculation comparison")
    void compareSingleThreadAndMultiThreadMonteCarloPiFinders(long totalPointsCount) {
        // Single thread pi calculation
        long singleStartTime = System.nanoTime();
        var singleThreadPiFinder = new SingleThreadMonteCarloPiFinder(totalPointsCount);
        double singleThreadPiCalculationResult = singleThreadPiFinder.calculatePi();
        long singleEndTime = System.nanoTime();

        double singleTotalExecutionTime = (double) (singleEndTime - singleStartTime) / 1_000_000_000;
        double singleDelta = Math.abs(Math.PI - singleThreadPiCalculationResult);

        // Multi thread pi calculation
        int totalThreadsCount = Runtime.getRuntime().availableProcessors();
        var multiThreadPiFinder = new MultiThreadMonteCarloPiFinder(totalPointsCount, totalThreadsCount);

        for (int threadsCount : divideRange(totalThreadsCount)) {
            long multiStartTime = System.nanoTime();
            double multiThreadPiCalculationResult = multiThreadPiFinder.calculatePi();
            long multiEndTime = System.nanoTime();

            double multiTotalExecutionTime = (double) (multiEndTime - multiStartTime) / 1_000_000_000;
            double multiDelta = Math.abs(Math.PI - multiThreadPiCalculationResult);

            ComparisonResultsPrinter.printComparisonResult(
                totalPointsCount,
                threadsCount,
                singleThreadPiCalculationResult,
                singleTotalExecutionTime,
                singleDelta,
                multiThreadPiCalculationResult,
                multiTotalExecutionTime,
                multiDelta
            );
        }
    }

    List<Integer> divideRange(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(2);
        int numberOfElements = Math.min(3, n - 1);
        double interval = (double) (n - 2) / numberOfElements;

        for (int i = 1; i < numberOfElements; i++) {
            int value = (int) Math.ceil(2 + interval * i);

            if (value < n) {
                result.add(value);
            }
        }

        result.add(n);
        return result;
    }
}
