package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class TestMapper {

    private final boolean primitiveBoolean;
    private final Integer wrappedInteger;
    private final int primitiveInteger;
    private final int[] primitiveArray;

    public static TestMapper generate(
        @Min(100) @Max(200) boolean primitiveBoolean,
        Integer wrappedInteger,
        int primitiveInteger,
        int[] primitiveArray
    ) {
        return new TestMapper(primitiveBoolean, wrappedInteger, primitiveInteger, primitiveArray);
    }

}



