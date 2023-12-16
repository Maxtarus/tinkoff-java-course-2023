package edu.hw10.task1.generators;

import edu.hw10.task1.utils.GeneratorUtils;

import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class IntegerGenerator implements Generator<Integer> {
    @Override
    public Integer generate(Parameter paramAnnotations) {
        var min =
            GeneratorUtils.getMinFromAnnotation(paramAnnotations.getAnnotations(), Integer.MIN_VALUE, Integer.MAX_VALUE)
                .intValue();
        var max =
            GeneratorUtils.getMaxFromAnnotation(paramAnnotations.getAnnotations(), Integer.MIN_VALUE, Integer.MAX_VALUE)
                .intValue();
        var nullable = GeneratorUtils.isNullable(paramAnnotations);

        if (nullable && Math.random() < GeneratorUtils.DEFAULT_NULL_PROBABILITY) {
            return null;
        }

        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
