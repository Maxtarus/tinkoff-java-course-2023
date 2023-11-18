package edu.hw6.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.net.URI;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class HackerNewsTest {
    private static Stream<Arguments> newsTitleById() {
        return Stream.of(
            Arguments.of(
                38318889L,
                "Death by AI – a free Jackbox style party game. AI judges your plans to survive"
            ),
            Arguments.of(
                38316936L,
                "Show HN: Open-source tool for creating courses like Duolingo"),
            Arguments.of(
                38316953L,
                "GTK: Introducing Graphics Offload"),
            Arguments.of(
                38318127L,
                "Show HN: ColBERT Build from Sentence Transformers"),
            Arguments.of(
                38317924L,
                "OpenVMS Account at DECUServe")
        );
    }

    @Test
    @DisplayName("Получение непустого массива идентификаторов наиболее обсуждаемых статей")
    void testHackerNewsTopStories_shouldReturnLongArrayOfNewsTopStoriesIds() {
        //Arrange
        URI rightTopStoriesUri = HackerNews.getTopStoriesUri();

        //Act
        long[] hackerNewsTopStoriesArray = HackerNews.hackerNewsTopStories(rightTopStoriesUri);

        //Assert
        assertThat(hackerNewsTopStoriesArray).isNotEmpty();
    }

    @Test
    @DisplayName("Получение пустого массива идентификаторов наиболее обсуждаемых статей")
    void testHackerNewsTopStories_shouldReturnEmptyLongArray() {
        //Arrange
        URI wrongTopStoriesUri = URI.create("https://hacker-news.com");

        //Act
        long[] hackerNewsTopStoriesArray = HackerNews.hackerNewsTopStories(wrongTopStoriesUri);

        //Assert
        assertThat(hackerNewsTopStoriesArray).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("newsTitleById")
    @DisplayName("Получение названия новости по идентификатору")
    public void testNews_shouldReturnStringOfTitleOfNewsFromJson(long id, String title) {
        assertThat(HackerNews.news(id)).isEqualTo(title);
    }
}
