package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HackerNews {
    private static final URI BASE_URI = URI.create("https://hacker-news.firebaseio.com/v0/");
    private static final URI TOP_STORIES_URI = BASE_URI.resolve("topstories.json");
    private static final URI ITEM_URI = BASE_URI.resolve("item/");

    private HackerNews() {}

    public static URI getTopStoriesUri() {
        return TOP_STORIES_URI;
    }

    public static long[] hackerNewsTopStories(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .build();

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return convertResponseBodyLoLongArray(response.body());
        } catch (IOException e) {
            return new long[0];
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static long[] convertResponseBodyLoLongArray(String responseBody) {
        String[] topStoriesIdsStringArray = responseBody.substring(1, responseBody.length() - 1).split(",");
        return Arrays.stream(topStoriesIdsStringArray)
            .mapToLong(Long::parseLong)
            .toArray();
    }

    public static String news(long newsId) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(ITEM_URI.resolve(newsId + ".json"))
            .GET()
            .build();

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Optional<String> newsTitle = parseTitle(response.body());
            return newsTitle.orElseGet(() -> newsTitle.orElse(""));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Optional<String> parseTitle(String newsJson) {
        Pattern pattern = Pattern.compile("\"title\":\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(newsJson);

        if (matcher.find()) {
            return Optional.of(matcher.group(1));
        }

        return Optional.empty();
    }
}
