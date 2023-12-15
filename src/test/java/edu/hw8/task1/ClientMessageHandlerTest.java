package edu.hw8.task1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ClientMessageHandlerTest {
    final int maxConnections = 5;
    final String host = "localhost";
    final int port = 8080;
    Server server;

    @BeforeEach
    void setup() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                server = new Server(maxConnections, port);
                server.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
        Thread.sleep(1000);
    }

    @AfterEach
    void shutdown() {
        try {
            server.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Single thread")
    void testClientMessageHandler_whenSingleThread() throws IOException {
        //Arrange
        String[] messages = new String[] {"личности", "оскорбления", "глупый", "интеллект", "incorrect"};
        List<String> expectedAnswers = List.of(
            "Не переходи на личности там, где их нет",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
            "Чем ниже интеллект, тем громче оскорбления",
            "В данный момент сервер не может ответить"
        );

        //Act
        Client client = new Client(host, port);
        List<String> actualAnswers = new ArrayList<>();
        for (String message : messages) {
            actualAnswers.add(client.sendMessage(message));
        }

        //Assert
        assertThat(actualAnswers).containsExactlyInAnyOrderElementsOf(expectedAnswers);
    }

    @Test
    @DisplayName("Several threads")
    void testClientMessageHandler_whenSeveralThreads() {
        //Arrange
        String[] messages = new String[] {"личности", "оскорбления", "глупый", "интеллект", "incorrect"};
        List<String> expectedAnswers = List.of(
            "Не переходи на личности там, где их нет",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
            "Чем ниже интеллект, тем громче оскорбления",
            "В данный момент сервер не может ответить"
        );

        //Act
        List<String> actualAnswers = new ArrayList<>();
        try (ExecutorService executorService = Executors.newFixedThreadPool(maxConnections)) {
            for (int i = 0; i < maxConnections; i++) {
                int messageNumber = i;
                executorService.execute(() -> {
                    try {
                        Client client = new Client(host, port);
                        actualAnswers.add(client.sendMessage(messages[messageNumber]));
                        client.disconnect();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }

        //Assert
        assertThat(actualAnswers).containsExactlyInAnyOrderElementsOf(expectedAnswers);
    }
}
