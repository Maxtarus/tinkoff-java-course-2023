package edu.hw3.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест шифра Атбаш")
class AtbashEncryptionUtilsTest {
    @Test
    @DisplayName("В сообщении только латинские буквы")
    void atbash_shouldGetEncryptedMessage_whenOnlyLatinLetters() {
        //given
        String message = "Hello world!";
        String encryptedMessage = "Svool dliow!";

        //when
        String result = AtbashEncryptionUtils.atbash(message);

        //then
        assertThat(result).isEqualTo(encryptedMessage);
    }

    @Test
    @DisplayName("В сообщении есть русские буквы")
    void atbash_shouldGetEncryptedMessage_whenRussianLetters() {
        //given
        String message = "АбВгДежз";

        //when
        String result = AtbashEncryptionUtils.atbash(message);

        //then
        assertThat(result).isEqualTo(message);
    }

    @Test
    @DisplayName("Null-строка")
    void atbash_shouldThrowNullPointerException() {
        assertThrows(IllegalArgumentException.class, () -> AtbashEncryptionUtils.atbash(null));
    }
}
