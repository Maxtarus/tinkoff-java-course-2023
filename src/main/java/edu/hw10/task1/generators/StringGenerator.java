package edu.hw10.task1.generators;

import edu.hw10.task1.utils.GeneratorUtils;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator implements Generator<String> {
    @Override
    public String generate(Parameter paramAnnotations) {
        var min =
            GeneratorUtils.getMinFromAnnotation(paramAnnotations.getAnnotations(), Short.MIN_VALUE, Short.MAX_VALUE)
                .shortValue();
        var max =
            GeneratorUtils.getMaxFromAnnotation(paramAnnotations.getAnnotations(), Short.MIN_VALUE, Short.MAX_VALUE)
                .shortValue();
        var nullable = GeneratorUtils.isNullable(paramAnnotations);

        if (nullable && Math.random() < GeneratorUtils.DEFAULT_NULL_PROBABILITY) {
            return null;
        }
        var stringLength = ThreadLocalRandom.current().nextInt(min, max);
        var stringBuilder = new StringBuilder();

        for (int i = 0; i < stringLength; i++) {
            var randomInt = ThreadLocalRandom.current().nextInt(0, Character.MAX_VALUE);
            stringBuilder.append((char) randomInt);
        }

        return stringBuilder.toString();
    }
}
