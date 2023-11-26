package edu.hw7.task1;

public class SynchronizedCounter implements ThreadSafeCounter {
    private int count = 0;

    @Override
    public synchronized void increment() {
        count++;
    }

    @Override
    public synchronized int get() {
        return count;
    }
}
