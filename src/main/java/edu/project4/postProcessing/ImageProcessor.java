package edu.project4.postProcessing;

import edu.project4.types.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}
