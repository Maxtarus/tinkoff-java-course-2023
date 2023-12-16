package edu.hw10.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomObjectGeneratorTest {
    @Test
    @DisplayName("Тестирование RandomObjectGenerator#nextObject для POJO")
    public void nextObject_test1() {
        RandomObjectGenerator generator = RandomObjectGenerator.getInstance();
        assertThat(generator.nextObject(TestMapper.class)).isNotNull();
    }

    @Test
    @DisplayName("Тестирование RandomObjectGenerator#nextObject для POJO через fabric method")
    public void nextObject_test2() {
        RandomObjectGenerator generator = RandomObjectGenerator.getInstance();
        assertThat(generator.nextObject(TestMapper.class, "generate")).isNotNull();
    }

    @Test
    @DisplayName("Тестирование RandomObjectGenerator#nextObject для record")
    public void nextObject_test3() {
        RandomObjectGenerator generator = RandomObjectGenerator.getInstance();
        assertThat(generator.nextObject(TestRecord.class)).isNotNull();
    }

    @Test
    @DisplayName("Тестирование правильной работы аннотаций")
    public void nextObject_test4() {
        RandomObjectGenerator generator = RandomObjectGenerator.getInstance();
        TestRecord testRecord = generator.nextObject(TestRecord.class);
        assertTrue(
            testRecord.primitiveInt() < 200 && testRecord.primitiveInt() > 100 && testRecord.string().length() < 40);
    }

}
