package edu.project4.renderers;

import edu.project4.transformations.Transformation;
import edu.project4.types.AffineFunction;
import edu.project4.types.FractalImage;
import edu.project4.types.Pixel;
import edu.project4.types.Point;
import edu.project4.types.Rect;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static edu.project4.utils.PointUtils.rotate;

public class AbstractRenderer {

    private static final int FOCUS_STEPS = 20;

    protected AbstractRenderer() {
    }

    protected static void renderOneSample(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        List<AffineFunction> affineFunctions,
        int symmetry,
        short iterPerSample
    ) {
        var random = ThreadLocalRandom.current();
        Point point = world.getRandom();
        for (short step = -FOCUS_STEPS; step < iterPerSample; ++step) {

            AffineFunction func = affineFunctions.get(random.nextInt(affineFunctions.size()));
            point = func.transform(point);

            Transformation transformation = variations.get(random.nextInt(variations.size()));

            point = transformation.apply(point);

            if (step > 0) {
                double theta2 = 0.0;
                for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                    var pwr = rotate(point, theta2);

                    if (world.isContain(pwr)) {
                        Pixel curr = getPixel(pwr, world, canvas);
                        curr.updatePixel(func.getRgb());
                    }
                }
            }
        }
    }

    private static Pixel getPixel(Point point, Rect world, FractalImage canvas) {
        int centerCanvasY = canvas.getHeight() / 2;
        int centerCanvasX = canvas.getWidth() / 2;

        int newY = (int) ((point.y() / ((world.height() - world.y()) / 2)) * centerCanvasY);
        int newX = (int) ((point.x() / ((world.width() - world.x()) / 2)) * centerCanvasX);

        return canvas.pixel(
            newX + centerCanvasX,
            newY + centerCanvasY
        );
    }
}
