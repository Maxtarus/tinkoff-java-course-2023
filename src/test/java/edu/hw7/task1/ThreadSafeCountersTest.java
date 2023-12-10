package edu.hw7.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ThreadSafeCountersTest {
    @ParameterizedTest
    @MethodSource("threadSafeCounters")
    @DisplayName("Thread-safe counters tests")
    void testThreadSafeCounters(ThreadSafeCounter counter) {
        testThreadSafeCounter(counter);
    }

    void testThreadSafeCounter(ThreadSafeCounter counter) {
        //Arrange
        int threadsNumber = 5;
        int iterationsNumber = 10;
        int expectedCount = threadsNumber * iterationsNumber;

        List<Thread> threads = new ArrayList<>(threadsNumber);
        for (int i = 0; i < threadsNumber; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < iterationsNumber; j++) {
                    counter.increment();
                }
            }));
        }

        //Act
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }
        });
        int actualCount = counter.get();

        //Assert
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    static Arguments[] threadSafeCounters() {
        return new Arguments[] {
            Arguments.of(new AtomicCounter()),
            Arguments.of(new SynchronizedCounter()),
            Arguments.of(new LockingCounter())
        };
    }
}
