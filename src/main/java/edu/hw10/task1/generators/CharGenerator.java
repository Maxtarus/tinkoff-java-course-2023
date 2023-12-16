package edu.hw10.task1.generators;

import edu.hw10.task1.utils.GeneratorUtils;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class CharGenerator implements Generator<Character> {
    @Override
    public Character generate(Parameter paramAnnotations) {
        var min =
            GeneratorUtils.getMinFromAnnotation(
                    paramAnnotations.getAnnotations(),
                    Character.MIN_VALUE,
                    Character.MAX_VALUE
                )
                .intValue();
        var max =
            GeneratorUtils.getMaxFromAnnotation(
                    paramAnnotations.getAnnotations(),
                    Character.MIN_VALUE,
                    Character.MAX_VALUE
                )
                .intValue();

        var nullable = GeneratorUtils.isNullable(paramAnnotations);

        if (nullable && Math.random() < GeneratorUtils.DEFAULT_NULL_PROBABILITY) {
            return null;
        }

        return (char) ThreadLocalRandom.current().nextInt(min, max);
    }
}
