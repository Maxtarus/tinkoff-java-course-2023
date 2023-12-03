package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService executorService;
    private final ServerSocket serverSocket;

    public Server(int maxConnections, int port) throws IOException {
        executorService = Executors.newFixedThreadPool(maxConnections);
        serverSocket = new ServerSocket(port);
    }

    public void start() {
        while (true) {
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
                executorService.execute(new ClientMessageHandler(clientSocket));
            } catch (IOException ignored) {
                return;
            }
        }
    }

    public void stop() throws IOException {
        executorService.shutdown();
        serverSocket.close();
    }
}
