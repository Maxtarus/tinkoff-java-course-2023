package edu.hw10.task1.generators;

import java.lang.reflect.Parameter;

@FunctionalInterface
public interface Generator<T> {
    T generate(Parameter paramAnnotations);
}


