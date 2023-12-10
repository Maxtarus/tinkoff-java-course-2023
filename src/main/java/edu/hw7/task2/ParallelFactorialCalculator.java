package edu.hw7.task2;

import java.util.stream.LongStream;

public final class ParallelFactorialCalculator {
    private ParallelFactorialCalculator() {}

    public static long culculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        if (n == 0) {
            return 1L;
        }

        return LongStream.rangeClosed(1, n)
            .parallel()
            .reduce(1L, (long a, long b) -> a * b);
    }
}
