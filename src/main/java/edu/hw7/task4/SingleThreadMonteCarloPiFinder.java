package edu.hw7.task4;

import java.util.Random;

public class SingleThreadMonteCarloPiFinder implements MonteCarloPiFinder {
    private static final Random RANDOM = new Random();
    private final long totalPointsCount;

    public SingleThreadMonteCarloPiFinder(long totalPointsCount) {
        this.totalPointsCount = totalPointsCount;
    }

    @Override
    public double calculatePi() {
        return SCALE_FACTOR * countTotalPointsInsideCircle() / totalPointsCount;
    }

    @Override
    public long countTotalPointsInsideCircle() {
        long pointsInsideCircleCount = 0;

        for (long i = 0; i < totalPointsCount; i++) {
            double x = RANDOM.nextDouble();
            double y = RANDOM.nextDouble();

            if (isPointInsideCircle(x, y)) {
                pointsInsideCircleCount++;
            }
        }

        return pointsInsideCircleCount;
    }
}
