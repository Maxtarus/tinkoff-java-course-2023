package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public final class AnimalValidator {
    private AnimalValidator() {
    }

    public static Set<ValidationError> getValidationErrors(Animal animal) {
        Set<ValidationError> errorsSet = new HashSet<>();

        if (animal.age() < 0) {
            errorsSet.add(new ValidationError("Возраст животного не может быть меньше нуля!"));
        }

        if (animal.height() < 0) {
            errorsSet.add(new ValidationError("Рост животного не может быть меньше нуля!"));
        }

        if (animal.weight() < 0) {
            errorsSet.add(new ValidationError("Вес животного не может быть меньше нуля!"));
        }

        return errorsSet;
    }
}
