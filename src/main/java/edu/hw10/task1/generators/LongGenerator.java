package edu.hw10.task1.generators;

import edu.hw10.task1.utils.GeneratorUtils;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class LongGenerator implements Generator<Long> {
    @Override
    public Long generate(Parameter paramAnnotations) {
        var min =
            GeneratorUtils.getMinFromAnnotation(paramAnnotations.getAnnotations(), Long.MIN_VALUE, Long.MAX_VALUE);
        var max =
            GeneratorUtils.getMaxFromAnnotation(paramAnnotations.getAnnotations(), Long.MIN_VALUE, Long.MAX_VALUE);
        var nullable = GeneratorUtils.isNullable(paramAnnotations);

        if (nullable && Math.random() < GeneratorUtils.DEFAULT_NULL_PROBABILITY) {
            return null;
        }

        return ThreadLocalRandom.current().nextLong(min, max);
    }
}
