package edu.hw10.task2.testData;

import java.util.concurrent.ThreadLocalRandom;
import lombok.SneakyThrows;

public class TestImpl implements TestInterface {
    private int counter = 0;
    @Override
    public long mapTest(int number) {
        counter++;
        return ThreadLocalRandom.current().nextLong();
    }

    @Override
    @SneakyThrows
    public Integer diskTest(int number, boolean bool) {
        counter++;
        return ThreadLocalRandom.current().nextInt();
    }

    @Override public int getCounter() {
        return counter;
    }
}
