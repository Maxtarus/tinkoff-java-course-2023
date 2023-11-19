package edu.project3.receiver.http;

import edu.project3.receiver.Receiver;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpLogReceiver implements Receiver {
    private final String http;

    public HttpLogReceiver(String http) {
        this.http = http;
    }

    @Override
    public List<String> receive() {
        HttpRequest request = HttpRequest.newBuilder(URI.create(http)).build();
        try (HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return List.of(response.body().split("\n"));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
