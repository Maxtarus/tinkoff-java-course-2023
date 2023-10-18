package edu.hw2.task3.connection_manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;

public class FaultyConnectionManager implements ConnectionManager {
    private final double failureChance;

    public FaultyConnectionManager(double failureChance) {
        if (failureChance < 0.0 || failureChance > 1.0) {
            throw new IllegalArgumentException("Вероятность неудачи при проблемном соединении должна быть в диапазоне"
                + " от нуля до единицы!");
        }
        this.failureChance = failureChance;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(failureChance);
    }
}
