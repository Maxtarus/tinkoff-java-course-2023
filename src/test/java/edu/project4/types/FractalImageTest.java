package edu.project4.types;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FractalImageTest {
    @Test
    @DisplayName("Тестирование FractalImage#pixel")
    public void pixel_shouldReturnCorrectPixel() {
        FractalImage image = FractalImage.create(10, 10);
        Pixel expected = image.data[5 * 10 + 5];
        expected.setHitCount(5);
        Pixel actual = image.pixel(5, 5);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тестирование FractalImage#pixel")
    public void pixel_shouldReturnNull() {
        FractalImage image = FractalImage.create(10, 10);
        assertThatThrownBy(() -> image.pixel(15, 15)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Тестирование FractalImage#isContain")
    public void contains_shouldReturnTrue() {
        FractalImage image = FractalImage.create(10, 10);
        assertThat(image.isContain(5, 5)).isTrue();
    }

    @Test
    @DisplayName("Тестирование FractalImage#isContain")
    public void contains_shouldReturnFalse() {
        FractalImage image = FractalImage.create(10, 10);
        assertThat(image.isContain(15, 15)).isFalse();
    }

}
