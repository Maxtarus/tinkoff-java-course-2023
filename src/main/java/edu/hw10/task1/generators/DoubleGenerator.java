package edu.hw10.task1.generators;

import edu.hw10.task1.utils.GeneratorUtils;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class DoubleGenerator implements Generator<Double> {
    @Override
    public Double generate(Parameter paramAnnotations) {
        var min =
            GeneratorUtils.getMinFromAnnotation(
                    paramAnnotations.getAnnotations(),
                    (long) -Double.MAX_VALUE,
                    (long) Double.MAX_VALUE
                ).doubleValue();
        var max =
            GeneratorUtils.getMaxFromAnnotation(
                    paramAnnotations.getAnnotations(),
                    (long) -Double.MAX_VALUE,
                    (long) Double.MAX_VALUE
                ).doubleValue();
        var nullable = GeneratorUtils.isNullable(paramAnnotations);

        if (nullable && Math.random() < GeneratorUtils.DEFAULT_NULL_PROBABILITY) {
            return null;
        }

        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
