package edu.hw11.task1;


import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ByteBuddyHelloTest {
    @SneakyThrows
    @Test
    @DisplayName("toString method in new class created by ByteBuddy test")
    void byteBuddy_shouldCreateNewClass_withToStringMethod() {
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
        assertThat(dynamicType.getDeclaredConstructor().newInstance().toString()).isEqualTo("Hello, ByteBuddy!");
    }
}
