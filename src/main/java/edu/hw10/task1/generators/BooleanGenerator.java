package edu.hw10.task1.generators;

import edu.hw10.task1.utils.GeneratorUtils;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class BooleanGenerator implements Generator<Boolean> {
    @Override
    public Boolean generate(Parameter paramAnnotations) {

        var min =
            GeneratorUtils.getMinFromAnnotation(paramAnnotations.getAnnotations(), 0, 1)
                .intValue();
        var max =
            GeneratorUtils.getMaxFromAnnotation(paramAnnotations.getAnnotations(), 0, 1)
                .intValue();

        var nullable = GeneratorUtils.isNullable(paramAnnotations);

        if (nullable && Math.random() < GeneratorUtils.DEFAULT_NULL_PROBABILITY) {
            return null;
        }

        if (min == 1) {
            return true;
        } else if (max == 0) {
            return false;
        }

        return ThreadLocalRandom.current().nextBoolean();
    }

}
