package edu.project3.remote;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HttpStatusCodeTest {
    @Test
    @DisplayName("Returning name of response code test")
    public void getByValue_shouldReturnCorrectHttpStatusCode() {
        assertThat(HttpStatusCode.getByValue(200).toString()).isEqualTo("OK");
    }

    @Test
    @DisplayName("Returning name of wrong response code test")
    public void getByValue_shouldThrowException_whenResponseCodeIsWrong() {
        assertThatThrownBy(() -> HttpStatusCode.getByValue(600))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
