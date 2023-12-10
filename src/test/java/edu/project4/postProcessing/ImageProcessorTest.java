package edu.project4.postProcessing;

import edu.project4.renderers.ParallelRenderer;
import edu.project4.transformations.SphereTransformation;
import edu.project4.types.AffineFunction;
import edu.project4.types.FractalImage;
import edu.project4.types.Rect;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ImageProcessorTest {

    @Test
    @DisplayName("Gamma correction test")
    public void process_shouldCorrectImage_whenUsedMultiThread() {
        FractalImage fractalImage = FractalImage.create(7680, 4320);

        ParallelRenderer.renderAsync(
            fractalImage,
            new Rect(-3.5, -2.0, 3.5, 2.0),
            List.of(new SphereTransformation()),
            Stream.generate(AffineFunction::new).limit(3).toList(),
            2,
            10000,
            (short) 5,
            6
        );

        ImageProcessor gammaCorrection = new GammaCorrection();
        assertDoesNotThrow(() -> gammaCorrection.process(fractalImage));
    }

}
