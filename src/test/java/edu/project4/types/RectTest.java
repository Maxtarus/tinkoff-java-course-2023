package edu.project4.types;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RectTest {
    @Test
    @DisplayName("Тестирование Rect#isContains")
    public void contains_shouldReturnTrue() {
        Rect rect = new Rect(0, 0, 10, 10);
        assertThat(rect.isContain(new Point(5, 5))).isTrue();
    }

    @Test
    @DisplayName("Тестирование Rect#isContains")
    public void contains_shouldReturnFalse() {
        Rect rect = new Rect(0, 0, 10, 10);
        assertThat(rect.isContain(new Point(15, 15))).isFalse();
    }

    @Test
    @DisplayName("Тестирование Rect#getRandom")
    public void randomPoint_shouldReturnCorrectPoint() {
        Rect rect = new Rect(0, 0, 10, 10);
        Point actual = rect.getRandom();
        assertThat(rect.isContain(actual)).isTrue();
    }

}
