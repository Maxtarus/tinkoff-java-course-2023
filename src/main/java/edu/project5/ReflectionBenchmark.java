package edu.project5;

import edu.common.Generated;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;


@SuppressWarnings({"unused", "SpellCheckingInspection", "UncommentedMain"})
@State(Scope.Thread)
public class ReflectionBenchmark {
    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    @SuppressWarnings("rawtypes")
    private Function function;
    private static final String METHOD_NAME = "name";

    @Generated
    public static void main(String[] args) throws RunnerException {

        // CHECKSTYLE:OFF: Disable MagicNumber check
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();
        // CHECKSTYLE:ON: Enable MagicNumber check

        new Runner(options).run();
    }

    @Setup
    public void setup() throws Throwable {
        //noinspection SpellCheckingInspection
        student = new Student("Vladimir", "Dmitriev");
        method = Student.class.getMethod(METHOD_NAME);

        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, METHOD_NAME, MethodType.methodType(String.class));

        CallSite callSite = LambdaMetafactory.metafactory(
            lookup,
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            methodHandle,
            MethodType.methodType(String.class, Student.class)
        );

        //noinspection rawtypes
        function = (Function) callSite.getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(@NotNull Blackhole bh) {
        bh.consume(student.name());
    }

    @Benchmark
    public void reflection(@NotNull Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        bh.consume(method.invoke(student));
    }

    @Benchmark
    public void methodHandles(@NotNull Blackhole bh) throws Throwable {
        bh.consume(methodHandle.invoke(student));
    }

    @Benchmark
    public void lambdaMetaFactory(@NotNull Blackhole bh) {
        //noinspection unchecked
        bh.consume(function.apply(student));
    }
}
