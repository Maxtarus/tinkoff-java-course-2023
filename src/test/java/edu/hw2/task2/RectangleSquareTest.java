package edu.hw2.task2;

import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Тест квадрата и прямоугольника")
class RectangleSquareTest {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        Rectangle rectangle = rect
            .setWidth(20)
            .setHeight(10);
        assertThat(rectangle.area()).isEqualTo(200.0);
    }
}
