package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public record TestRecord(@NotNull @Min(100) @Max(200) int primitiveInt, Integer wrappedInt,
                         @Min(0) @Max(40) String string, char primitiveChar, short primitiveShort, Byte wrappedByte,
                         Long wrappedLong, Float wrappedFloat, double primitiveDouble) {
}
