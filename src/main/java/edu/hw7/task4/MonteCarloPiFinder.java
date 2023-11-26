package edu.hw7.task4;

public interface MonteCarloPiFinder {
    double SCALE_FACTOR = 4.0;
    double CIRCLE_RADIUS = 1.0;

    default boolean isPointInsideCircle(double x, double y) {
        return x * x + y * y <= CIRCLE_RADIUS;
    }

    long countTotalPointsInsideCircle();

    double calculatePi();

}
