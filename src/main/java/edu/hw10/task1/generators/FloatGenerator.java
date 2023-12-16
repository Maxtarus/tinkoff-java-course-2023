package edu.hw10.task1.generators;

import edu.hw10.task1.utils.GeneratorUtils;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class FloatGenerator implements Generator<Float> {
    @Override
    public Float generate(Parameter paramAnnotations) {
        var min =
            GeneratorUtils.getMinFromAnnotation(
                    paramAnnotations.getAnnotations(),
                    (long) -Float.MAX_VALUE,
                    (long) Float.MAX_VALUE
                ).floatValue();
        var max =
            GeneratorUtils.getMaxFromAnnotation(
                    paramAnnotations.getAnnotations(),
                    (long) -Float.MAX_VALUE,
                    (long) Float.MAX_VALUE
                ).floatValue();

        var nullable = GeneratorUtils.isNullable(paramAnnotations);

        if (nullable && Math.random() < GeneratorUtils.DEFAULT_NULL_PROBABILITY) {
            return null;
        }

        return ThreadLocalRandom.current().nextFloat(min, max);
    }
}
