package edu.hw2.task3;

import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;
import edu.hw2.task3.connection_exception.ConnectionException;
import edu.hw2.task3.connection_manager.DefaultConnectionManager;
import edu.hw2.task3.connection_manager.FaultyConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест исполнителя популярных консольных команд")
class PopularCommandExecutorTest {
    @Test
    @DisplayName("DefaultConnectionManager со стабильным соединением")
    void testDefaultConnectionManagerWithStableConnection() {
        //given
        var manager = new DefaultConnectionManager(0.0, 0.0);

        //when
        var connection = manager.getConnection();

        //then
        assertThat(connection).isInstanceOf(StableConnection.class);
    }

    @Test
    @DisplayName("DefaultConnectionManager с проблемным соединением")
    void testDefaultConnectionManagerWithFaultyConnection() {
        //given
        var manager = new DefaultConnectionManager(1.0, 0.25);

        //when
        var connection = manager.getConnection();

        //then
        assertThat(connection).isInstanceOf(FaultyConnection.class);
    }

    @Test
    @DisplayName("Всегда проблемное соединение")
    void testAlwaysFaultyConnection() {
        //given
        var manager = new FaultyConnectionManager(1.0);
        int maxAttempts = 5;

        //when
        var executor = new PopularCommandExecutor(manager, maxAttempts);

        //then
        assertThrows(ConnectionException.class, executor::updatePackages);
    }

    @Test
    @DisplayName("Всегда стабильное соединение")
    void testAlwaysStableConnection() {
        //given
        var manager = new DefaultConnectionManager(0.0, 0.0);
        int maxAttempts = 5;

        //when
        var executor = new PopularCommandExecutor(manager, maxAttempts);

        //then
        assertDoesNotThrow(executor::updatePackages);
    }

}
