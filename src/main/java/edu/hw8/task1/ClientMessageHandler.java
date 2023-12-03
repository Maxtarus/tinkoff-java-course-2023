package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;

public class ClientMessageHandler extends Thread {
    private static final Map<String, String> SERVER_MESSAGES = Map.of(
        "личности",
        "Не переходи на личности там, где их нет",
        "оскорбления",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект",
        "Чем ниже интеллект, тем громче оскорбления"
    );
    public static final String DEFAULT_SERVER_MESSAGE = "В данный момент сервер не может ответить";
    private final Socket clientSocket;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public ClientMessageHandler(Socket socket) throws IOException {
        this.clientSocket = socket;
        this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                String answer = SERVER_MESSAGES.getOrDefault(message, DEFAULT_SERVER_MESSAGE);
                write(answer);
            }

            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException ignored) {
        }
    }

    private void write(String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
    }
}
