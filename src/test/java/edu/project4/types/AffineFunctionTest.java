package edu.project4.types;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AffineFunctionTest {

    @Test
    @DisplayName("Тестирование AffineTransformation#transform - преобразование пикселя")
    public void apply_shouldTransformAffinely() {
        AffineFunction affineFunction = new AffineFunction();
        Point given = new Point(5, 5);
        Assertions.assertDoesNotThrow(() -> affineFunction.transform(given));
    }

    @Test
    @DisplayName("Тестирование создания Aфинной функции")
    public void generateRandom_shouldReturnCorrectCoefficient() {
        AffineFunction actual = new AffineFunction();
        assertThat(isAffine(actual.getA(), actual.getB(), actual.getC(), actual.getD(), actual.getE(), actual.getF()))
            .isTrue();
    }

    private static boolean isAffine(double a, double b, double c, double d, double e, double f) {
        return ((a * a + d * d) < 1) && ((b * b + e * e) < 1)
            && ((a * a + b * b + d * d + e * e) < (1 + (a * e - b * d) * (a * e - b * d)));
    }

}
