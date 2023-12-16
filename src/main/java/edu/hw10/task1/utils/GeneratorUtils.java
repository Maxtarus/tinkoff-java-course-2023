package edu.hw10.task1.utils;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GeneratorUtils {

    public final static double DEFAULT_NULL_PROBABILITY = 0.01;

    public static Long getMinFromAnnotation(Annotation[] annotations, long minValue, long maxValue) {
        return Arrays.stream(annotations)
            .filter(annotation -> annotation instanceof Min)
            .map(entry -> ((Min) entry).value())
            .min(Long::compareTo)
            .map(value -> Math.clamp(value, minValue, maxValue))
            .orElse(minValue);
    }

    public static Long getMaxFromAnnotation(Annotation[] annotations, long minValue, long maxValue) {
        return Arrays
            .stream(annotations)
            .filter(annotation -> annotation instanceof Max)
            .map(entry -> ((Max) entry).value())
            .max(Long::compareTo)
            .map(value -> Math.clamp(value, minValue, maxValue))
            .orElse(maxValue);
    }

    public static boolean isNullable(Parameter parameter) {
        return !parameter.getType().isPrimitive()
            && !parameter.getType().isArray()
            && parameter.getAnnotationsByType(NotNull.class).length == 0;
    }
}
