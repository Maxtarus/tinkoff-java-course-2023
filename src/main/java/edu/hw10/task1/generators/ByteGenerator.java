package edu.hw10.task1.generators;

import edu.hw10.task1.utils.GeneratorUtils;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class ByteGenerator implements Generator<Byte> {
    @Override
    public Byte generate(Parameter paramAnnotations) {
        var min =
            GeneratorUtils.getMinFromAnnotation(paramAnnotations.getAnnotations(), Byte.MIN_VALUE, Byte.MAX_VALUE)
                .byteValue();
        var max =
            GeneratorUtils.getMaxFromAnnotation(paramAnnotations.getAnnotations(), Byte.MIN_VALUE, Byte.MAX_VALUE)
                .byteValue();
        var nullable = GeneratorUtils.isNullable(paramAnnotations);

        if (nullable && Math.random() < GeneratorUtils.DEFAULT_NULL_PROBABILITY) {
            return null;
        }

        return (byte) ThreadLocalRandom.current().nextInt(min, max);
    }
}
