package edu.hw10.task2.proxy;

import java.lang.reflect.Proxy;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CacheProxy {

    @SuppressWarnings("unchecked")
    public static <T> T create(T object, Class<? extends T> clazz) {
        return (T) Proxy.newProxyInstance(
            CacheProxy.class.getClassLoader(),
            new Class[] {clazz},
            new DynamicInvocationHandler(object)
        );
    }
}

