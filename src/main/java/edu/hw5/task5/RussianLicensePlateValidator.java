package edu.hw5.task5;

import java.util.Objects;
import java.util.regex.Pattern;

public final class RussianLicensePlateValidator {
    /**
     * Примеры правильных номерных знаков:
     * А123ВЕ777
     * О777ОО177
     * Примеры неправильных номерных знаков:
     * 123АВЕ777
     * А123ВГ77
     * А123ВЕ7777
     */
    private final static Pattern RUSSIAN_LICENSE_PLATE_PATTERN
        = Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$");

    private RussianLicensePlateValidator() {}

    public static boolean isRussianLicensePlateValid(String licensePlate) {
        Objects.requireNonNull(licensePlate);
        return RUSSIAN_LICENSE_PLATE_PATTERN.matcher(licensePlate).matches();
    }
}
