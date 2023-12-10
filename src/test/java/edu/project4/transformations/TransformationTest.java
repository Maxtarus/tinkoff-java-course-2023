package edu.project4.transformations;

import edu.project4.types.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class TransformationTest {

    @Test
    @DisplayName("Тестирование LinearTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformLinearly() {
        LinearTransformation transformation = new LinearTransformation();
        Point expected = new Point(5, 5);
        Point actual = transformation.apply(expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тестирование DiskTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformDisk() {
        DiskTransformation transformation = new DiskTransformation();
        Point given = new Point(5, 5);
        Point actual = transformation.apply(given);
        assertThat(actual).isEqualTo(new Point(-0.05535396184412049, -0.24379487055342172));
    }

    @Test
    @DisplayName("Тестирование TangentTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformTangent() {
        TangentTransformation transformation = new TangentTransformation();
        Point given = new Point(5, 5);
        Point actual = transformation.apply(given);
        assertThat(actual).isEqualTo(new Point(-3.380515006246586, -3.380515006246586));
    }

    @Test
    @DisplayName("Тестирование HeartTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformHeart() {
        HeartTransformation transformation = new HeartTransformation();
        Point given = new Point(5, 5);
        Point actual = transformation.apply(given);
        assertThat(actual).isEqualTo(new Point(-4.7132755554838255, -5.2711510638643855));
    }

    @Test
    @DisplayName("Тестирование SineTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformSine() {
        SinTransformation transformation = new SinTransformation();
        Point given = new Point(5, 5);
        Point actual = transformation.apply(given);
        assertThat(actual).isEqualTo(new Point(-0.9589242746631385, -0.9589242746631385));
    }

    @Test
    @DisplayName("Тестирование SphereTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformSphere() {
        SphereTransformation transformation = new SphereTransformation();
        Point given = new Point(5, 5);
        Point actual = transformation.apply(given);
        assertThat(actual).isEqualTo(new Point(0.1, 0.1));
    }

}
