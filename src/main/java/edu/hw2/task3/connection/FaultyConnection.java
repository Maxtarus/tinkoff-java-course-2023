package edu.hw2.task3.connection;

import edu.hw2.task3.connection_exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    private final double failureChance;

    public FaultyConnection(double failureChance) {
        if (failureChance < 0.0 || failureChance > 1.0) {
            throw new IllegalArgumentException("Вероятность неудачи при проблемном соединении должна быть в "
                + "диапазоне от нуля до единицы!");
        }
        this.failureChance = failureChance;
    }

    @Override
    public void execute(String command) {
        if (Math.random() < failureChance) {
            throw new ConnectionException("К сожалению, выполнить команду" + command + " не удалось.");
        }

        LOGGER.info(command + " успешно выполнена!");
    }

    @Override
    public void close() {
        LOGGER.info("Проблемное соединение закрыто.");
    }
}
