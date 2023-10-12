package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task0 {
    private static final Logger LOGGER = LogManager.getLogger();

    private Task0() {
    }

    public static void printHelloWorld() {
        LOGGER.info("Привет, мир!");
    }

}
