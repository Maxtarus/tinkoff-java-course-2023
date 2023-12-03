package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    private final Socket clientSocket;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public Client(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    }

    public String sendMessage(String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
        return reader.readLine();
    }

    public void disconnect() throws IOException {
        writer.close();
        reader.close();
        clientSocket.close();
    }
}
