package edu.project4.postProcessing;

import edu.project4.types.FractalImage;
import edu.project4.types.Pixel;

public class GammaCorrection implements ImageProcessor {
    private static final double GAMMA_CONSTANT = 1.7;

    @Override
    public void process(FractalImage image) {
        double max = 0.0;
        double gamma = GAMMA_CONSTANT;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel.getHitCount() != 0) {
                    pixel.setCorrectionValue(Math.log10(pixel.getHitCount()));
                    if (pixel.getCorrectionValue() > max) {
                        max = pixel.getCorrectionValue();
                    }
                }
            }
        }

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = image.pixel(x, y);
                pixel.setCorrectionValue(pixel.getCorrectionValue() / max);
                pixel.setR((int) (pixel.getR() * Math.pow(pixel.getCorrectionValue(), (1.0 / gamma))));
                pixel.setG((int) (pixel.getG() * Math.pow(pixel.getCorrectionValue(), (1.0 / gamma))));
                pixel.setB((int) (pixel.getB() * Math.pow(pixel.getCorrectionValue(), (1.0 / gamma))));
            }
        }

    }
}
