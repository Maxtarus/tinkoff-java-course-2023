package edu.hw8.task2;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int threadsNumber;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks;
    private volatile boolean isShutdown;

    public static FixedThreadPool create(int threadsNumber) {
        return new FixedThreadPool(threadsNumber);
    }

    private FixedThreadPool(int threadsNumber) {
        this.threadsNumber = threadsNumber;
        this.threads = new Thread[threadsNumber];
        this.tasks = new LinkedBlockingQueue<>();
        this.isShutdown = false;
    }

    @Override
    public void start() {
        for (int i = 0; i < threadsNumber; ++i) {
            threads[i] = new Executor();
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("ThreadPool not started");
        }

        try {
            tasks.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        isShutdown = true;
        Arrays.stream(threads).forEach(Thread::interrupt);
    }

    class Executor extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = tasks.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
