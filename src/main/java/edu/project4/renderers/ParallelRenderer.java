package edu.project4.renderers;

import edu.project4.transformations.Transformation;
import edu.project4.types.AffineFunction;
import edu.project4.types.FractalImage;
import edu.project4.types.Rect;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ParallelRenderer extends AbstractRenderer {

    @SuppressWarnings("ParameterNumber")
    public static void renderAsync(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        List<AffineFunction> affineFunctions,
        int symmetry,
        int samples,
        short iterPerSample,
        int numberOfThreads
    ) {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<?>> tasks = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            tasks.add(executor.submit(() -> {
                for (int num = 0; num < samples / numberOfThreads; ++num) {
                    renderOneSample(canvas, world, variations, affineFunctions, symmetry, iterPerSample);
                }
            }));
        }

        for (var task : tasks) {
            try {
                task.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Генрация не быа завершена корректно", e);
            }
        }

        executor.shutdownNow();
    }
}
