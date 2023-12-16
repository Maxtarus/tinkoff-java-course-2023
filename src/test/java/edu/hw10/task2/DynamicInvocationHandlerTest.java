package edu.hw10.task2;

import edu.hw10.task2.proxy.CacheProxy;
import edu.hw10.task2.testData.TestImpl;
import edu.hw10.task2.testData.TestInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicInvocationHandlerTest {
    @Test
    @DisplayName("Тестирование CacheProxy withDisk")
    public void cacheProxy_test1() {
        TestInterface proxy = new TestImpl();
        proxy = CacheProxy.create(proxy, TestInterface.class);
        Integer value = proxy.diskTest(10, true);

        for (int i = 0; i < 3; i++) {
            Integer newVal = proxy.diskTest(10, true);
            assertEquals(value, newVal);
        }

        assertEquals(1, proxy.getCounter());
    }

    @Test
    @DisplayName("Тестирование CacheProxy withMap")
    public void cacheProxy_test2() {
        TestInterface proxy = new TestImpl();
        proxy = CacheProxy.create(proxy, TestInterface.class);
        long value = proxy.mapTest(10);

        for (int i = 0; i < 3; i++) {
            long newVal = proxy.mapTest(10);
            assertEquals(value, newVal);
        }

        assertEquals(1, proxy.getCounter());
    }

}
