package edu.hw10.task1;

import edu.hw10.task1.generators.Generator;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

public class RandomObjectGenerator {
    private static final String ILLEGAL_TYPE_EXCEPTION = "Illegal type ";

    private static final Integer MAX_ARR_LENGTH = 17;

    private RandomObjectGenerator() {
        String packageName = "edu.hw10";

        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends Generator>> subTypes = reflections.getSubTypesOf(Generator.class);

        subTypes.forEach(this::setGenerator);
    }

    public static RandomObjectGenerator getInstance() {
        return RandomObjectGeneratorHolder.INSTANCE;
    }

    private final Map<Class<?>, Generator<?>> typeGenerators = new HashMap<>();

    @SneakyThrows
    public <T> T nextObject(Class<T> clazz) {
        Constructor<T> constructor = (Constructor<T>) clazz.getConstructors()[0];
        var parameters = constructor.getParameters();
        Object[] generatedFields = generateFields(parameters);
        return constructor.newInstance(generatedFields);
    }

    @SneakyThrows
    public <T> T nextObject(Class<T> clazz, String factoryMethodName) {
        Method factoryMethod = getMethodByName(clazz, factoryMethodName);
        if (!clazz.isAssignableFrom(factoryMethod.getReturnType())) {
            throw new IllegalArgumentException("Тип возвращаемого значения метода %s не %s".formatted(
                factoryMethodName,
                clazz.getName()
            ));
        }
        var parameters = factoryMethod.getParameters();
        Object[] generatedFields = generateFields(parameters);
        return (T) factoryMethod.invoke(null, generatedFields);
    }

    private Method getMethodByName(Class<?> clazz, String name) {
        return Arrays
            .stream(clazz.getDeclaredMethods())
            .filter(method -> method.getName().equals(name))
            .findFirst()
            .orElse(null);
    }

    @NotNull private Object[] generateFields(Parameter[] parameters) {
        return Arrays
            .stream(parameters)
            .map(this::generateValue)
            .toArray(Object[]::new);
    }

    private Object generateValue(Parameter parameter) {
        var type = parameter.getType();
        if (type.isArray()) {
            return generateArray(parameter);
        }
        Generator<?> generator = typeGenerators.get(type);
        if (generator == null) {
            throw new IllegalArgumentException(ILLEGAL_TYPE_EXCEPTION + type);
        }
        return generator.generate(parameter);
    }

    private Object generateArray(Parameter parameter) {
        var type = parameter.getType().getComponentType();

        Generator<?> generator = typeGenerators.get(type);
        if (generator == null) {
            throw new IllegalArgumentException(ILLEGAL_TYPE_EXCEPTION + type);
        }

        int length = ThreadLocalRandom.current().nextInt(0, MAX_ARR_LENGTH);
        Object array = Array.newInstance(type, length);

        for (int i = 0; i < length; i++) {
            Array.set(array, i, generator.generate(parameter));
        }
        return array;
    }

    @SneakyThrows
    private void setGenerator(Class<?> clazz) {
        Method generateMethod = clazz.getDeclaredMethod("generate", Parameter.class);
        Class<?> type = generateMethod.getReturnType();
        Generator<?> generator = (Generator<?>) clazz.getConstructor().newInstance();
        typeGenerators.put(type, generator);
        try {
            Field typeField = type.getField("TYPE");
            if (typeField.getType().isInstance(Class.class)) {
                Class<?> secondType = (Class<?>) typeField.get(null);
                typeGenerators.put(secondType, generator);
            }
        } catch (Exception ignored) {
        }
    }

    private static class RandomObjectGeneratorHolder {
        private static final RandomObjectGenerator INSTANCE = new RandomObjectGenerator();

    }

}

