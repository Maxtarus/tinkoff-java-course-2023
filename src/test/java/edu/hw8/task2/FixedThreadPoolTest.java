package edu.hw8.task2;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import static org.assertj.core.api.Assertions.assertThat;

class FixedThreadPoolTest {
    private static final int THREADS_NUMBER = 4;
    private static final long[] FIBONACCI_NUMBERS = {
        0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L, 377L, 610L, 987L, 1597L, 2584L, 4181L, 6765L,
        10946L, 17711L, 28657L, 46368L, 75025L, 121393L, 196418L, 317811L, 514229L, 832040L, 1346269L, 2178309L,
        3524578L, 5702887L, 9227465L, 14930352L, 24157817L, 39088169L, 63245986L };

    private FixedThreadPool fixedThreadPool;
    private volatile long[] results;

    @BeforeEach
    void setUp() {
        fixedThreadPool = FixedThreadPool.create(THREADS_NUMBER);
        fixedThreadPool.start();
    }

    @AfterEach
    void close() {
        try {
            Thread.sleep(1000);
            fixedThreadPool.close();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    void testFibonacciParallel() {
        // Arrange
        results = new long[FIBONACCI_NUMBERS.length];
        var latch = new CountDownLatch(FIBONACCI_NUMBERS.length);

        // Act
        for (int i = 0; i < FIBONACCI_NUMBERS.length; i++) {
            final int index = i;
            fixedThreadPool.execute(() -> {
                results[index] = fibonacci(index);
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException ignored) {
        }

        // Assert
        for (int i = 0; i < FIBONACCI_NUMBERS.length; ++i) {
            assertThat(results[i]).isEqualTo(FIBONACCI_NUMBERS[i]);
        }
    }

    private long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        if (results[n] == 0) {
            results[n] = fibonacci(n - 1) + fibonacci(n - 2);
        }

        return results[n];
    }
}
