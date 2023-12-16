
package edu.hw10.task2.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hw10.task2.annotations.Cache;
import edu.hw10.task2.types.MethodSignature;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class DynamicInvocationHandler implements InvocationHandler {

    private static final String SIGNATURE_RETURN_DELIMITER = "/";
    private static final String DESERIALIZE_EXCEPTION = "Невозможно десериализовать запись";
    private final Map<MethodSignature, Object> cache = new HashMap<>(100);
    private final Object object;
    private final ObjectMapper mapper = new ObjectMapper();
    private Path cacheFile;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            if (method.getAnnotation(Cache.class).persist()) {
                return getCacheFromDisk(method, args);
            } else {
                return getCacheFromMap(method, args);
            }
        } else {
            return method.invoke(object, args);
        }
    }

    @SneakyThrows
    private Object getCacheFromDisk(Method method, Object[] args) {
        if (cacheFile == null) {
            cacheFile = Files.createTempFile("cache", ".txt");
        }
        Optional<?> cachedReturnValue = extractCache(method, args);
        if (cachedReturnValue.isEmpty()) {
            return writeCacheToDisk(method, args);
        }
        return cachedReturnValue.get();
    }

    @SneakyThrows
    private Optional<?> extractCache(Method method, Object[] args) {
        return Files.lines(cacheFile)
            .filter(entry -> isSignatureMatched(method, entry.split(SIGNATURE_RETURN_DELIMITER)[0], args))
            .map(entry -> readValue(method, entry))
            .findFirst();
    }

    private boolean isSignatureMatched(Method method, String serializedSignature, Object[] args) {
        try {
            return mapper.readValue(serializedSignature, MethodSignature.class)
                .equals(new MethodSignature(method.getName(), method.getParameterTypes(), args));
        } catch (Exception e) {
            throw new RuntimeException(DESERIALIZE_EXCEPTION, e);
        }
    }

    private Object readValue(Method method, String entry) {
        try {
            return mapper.readValue(entry.split(SIGNATURE_RETURN_DELIMITER)[1], method.getReturnType());
        } catch (Exception e) {
            throw new RuntimeException(DESERIALIZE_EXCEPTION, e);
        }
    }

    @SneakyThrows
    private Object writeCacheToDisk(Method method, Object[] args) {
        Object returnValue = method.invoke(object, args);
        var signature = new MethodSignature(method.getName(), method.getParameterTypes(), args);
        String writeToFile =
            mapper.writeValueAsString(signature) + SIGNATURE_RETURN_DELIMITER
                + mapper.writeValueAsString(returnValue) + "\n";
        Files.writeString(cacheFile, writeToFile, StandardOpenOption.APPEND);
        return returnValue;
    }

    @SneakyThrows
    private Object getCacheFromMap(Method method, Object[] args) {
        MethodSignature methodSignature = new MethodSignature(method.getName(), method.getParameterTypes(), args);
        if (cache.containsKey(methodSignature)) {
            return cache.get(methodSignature);
        }
        Object returnValue = method.invoke(object, args);
        cache.put(methodSignature, returnValue);
        return returnValue;
    }

}
