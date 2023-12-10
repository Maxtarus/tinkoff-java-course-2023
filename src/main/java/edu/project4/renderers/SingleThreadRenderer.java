package edu.project4.renderers;

import edu.project4.transformations.Transformation;
import edu.project4.types.AffineFunction;
import edu.project4.types.FractalImage;
import edu.project4.types.Rect;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SingleThreadRenderer extends AbstractRenderer {
    public static void renderSync(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        List<AffineFunction> affineFunctions,
        int symmetry,
        int samples,
        short iterPerSample
    ) {
        for (int num = 0; num < samples; ++num) {
            renderOneSample(canvas, world, variations, affineFunctions, symmetry, iterPerSample);
        }
    }
}
