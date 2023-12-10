package edu.project4;

import edu.project4.postProcessing.GammaCorrection;
import edu.project4.postProcessing.ImageProcessor;
import edu.project4.renderers.ParallelRenderer;
import edu.project4.transformations.LinearTransformation;
import edu.project4.transformations.SphereTransformation;
import edu.project4.types.AffineFunction;
import edu.project4.types.FractalImage;
import edu.project4.types.ImageFormat;
import edu.project4.types.Rect;
import edu.project4.utils.ImageUtils;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
public class Main {
    public static void main(String[] args) {
        Path root = Path.of("src", "main", "resources", "project4");

        FractalImage fractalImage = FractalImage.create(7680, 4320);

        ParallelRenderer.renderAsync(
            fractalImage,
            new Rect(-3.5, -2.0, 3.5, 2.0),
            List.of(new SphereTransformation(), new LinearTransformation()),
            Stream.generate(AffineFunction::new).limit(5).toList(), 7,
            1000000, (short) 5, 6);

        ImageProcessor gammaCorrection = new GammaCorrection();
        gammaCorrection.process(fractalImage);
        ImageUtils.save(fractalImage, root, "img2", ImageFormat.PNG);
    }
}
