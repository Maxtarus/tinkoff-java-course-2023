package edu.hw10.task2.testData;

import edu.hw10.task2.annotations.Cache;

public interface TestInterface {
    @Cache(persist = false)
    long mapTest(int number);

    @Cache(persist = true)
    Integer diskTest(int number, boolean bool);

    int getCounter();


}
