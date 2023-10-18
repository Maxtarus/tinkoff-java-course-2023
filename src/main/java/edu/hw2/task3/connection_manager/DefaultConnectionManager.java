package edu.hw2.task3.connection_manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;

public class DefaultConnectionManager implements ConnectionManager {
    private final double faultyConnectionChance;
    private final double faultyConnectionFailureChance;

    public DefaultConnectionManager(double faultyConnectionChance, double faultyConnectionFailureChance) {
        if (faultyConnectionChance < 0.0 || faultyConnectionChance > 1.0) {
            throw new IllegalArgumentException("Вероятность проблемного соединения должна быть в диапазоне "
                + "от нуля до единицы!");
        }

        if (faultyConnectionFailureChance < 0.0 || faultyConnectionFailureChance > 1.0) {
            throw new IllegalArgumentException("Вероятность неудачи при проблемном соединении должна быть в диапазоне"
                + " от нуля до единицы!");
        }

        this.faultyConnectionChance = faultyConnectionChance;
        this.faultyConnectionFailureChance = faultyConnectionFailureChance;
    }

    @Override
    public Connection getConnection() {
        if (Math.random() < faultyConnectionChance) {
            return new FaultyConnection(faultyConnectionFailureChance);
        }

        return new StableConnection();
    }
}
