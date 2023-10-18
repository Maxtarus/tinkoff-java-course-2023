package edu.hw2.task3;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection_exception.ConnectionException;
import edu.hw2.task3.connection_manager.ConnectionManager;
import java.util.Objects;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        Objects.requireNonNull(manager);

        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("Максимальное количество попыток должно быть натуральным числом!");
        }

        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    private void tryExecute(String command) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                break;
            } catch (ConnectionException e) {
                if (attempt == maxAttempts) {
                    throw new ConnectionException("К сожалению, " + maxAttempts + " попыток не хватило,"
                        + "чтобы выполнить команду \"" + command + "\"", e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }
}
