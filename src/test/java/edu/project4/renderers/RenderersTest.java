package edu.project4.renderers;

import edu.project4.transformations.SphereTransformation;
import edu.project4.types.AffineFunction;
import edu.project4.types.FractalImage;
import edu.project4.types.Rect;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RenderersTest {

    @Test
    @DisplayName("Тестирование ParallelRenderer#renderAsync")
    public void render_shouldRenderImage_whenUsedMultiThread() {
        FractalImage fractalImage = FractalImage.create(1920, 1080);
        assertDoesNotThrow(() -> ParallelRenderer.renderAsync(
            fractalImage,
            new Rect(-1.777, -1.0, 1.777, 1.0),
            List.of(new SphereTransformation()),
            Stream.generate(AffineFunction::new).limit(3).toList(),
            2,
            10000,
            (short) 5,
            6
        ));
    }

    @Test
    @DisplayName("Тестирование SingleThreadRenderer#renderSync")
    public void render_shouldRenderImage_whenUsedOneThread() {
        FractalImage fractalImage = FractalImage.create(1920, 1080);
        assertDoesNotThrow(() -> SingleThreadRenderer.renderSync(
            fractalImage,
            new Rect(-1.777, -1.0, 1.777, 1.0),
            List.of(new SphereTransformation()),
            Stream.generate(AffineFunction::new).limit(3).toList(),
            2,
            10000,
            (short) 5
        ));
    }
}
