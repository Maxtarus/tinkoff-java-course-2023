package edu.hw2.task4;

public final class CallingInfoUtils {
    private CallingInfoUtils() {
    }

    public static CallingInfo callingInfo() {
        var callerInfo = new Throwable().getStackTrace()[1];
        return new CallingInfo(callerInfo.getClassName(), callerInfo.getMethodName());
    }

    public record CallingInfo(String className, String methodName) {
    }
}
