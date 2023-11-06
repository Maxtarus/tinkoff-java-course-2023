package edu.hw4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class AnimalUtils {
    private AnimalUtils() {
    }

    //Task1
    public static List<Animal> getAnimalsByHeightAscending(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    //Task2
    public static List<Animal> getTopKHeaviestAnimals(List<Animal> animals, int k) {
        if (k <= 0 || k > animals.size()) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    //Task3
    public static Map<Animal.Type, Integer> getAnimalAmountByType(List<Animal> animals) {
        EnumMap<Animal.Type, Integer> animalAmountByType = animals.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    () -> new EnumMap<>(Animal.Type.class),
                    Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                )
            );

        EnumSet.allOf(Animal.Type.class)
            .forEach(type -> animalAmountByType.putIfAbsent(type, 0));

        return animalAmountByType;
    }

    //Task4
    public static Animal getAnimalWithLongestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElseThrow();
    }

    //Task5
    public static Animal.Sex getMostCommonSex(List<Animal> animals) {
        EnumMap<Animal.Sex, Long> animalsAmountBySex = animals.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::sex,
                    () -> new EnumMap<>(Animal.Sex.class),
                    Collectors.counting()
                )
            );

        EnumSet.allOf(Animal.Sex.class)
            .forEach(sex -> animalsAmountBySex.putIfAbsent(sex, 0L));

        Set<Long> animalsAmount = new HashSet<>(animalsAmountBySex.values());
        if (animalsAmount.size() == 1) {
            throw new NoSuchElementException();
        }

        return animalsAmountBySex.entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .orElseThrow()
            .getKey();
    }

    //Task6
   public static Map<Animal.Type, Animal> getHeaviestAnimalsByType(List<Animal> animals) {
       EnumMap<Animal.Type, Animal> heaviestAnimalsByType = animals.stream()
           .collect(
               Collectors.groupingBy(
                   Animal::type,
                   () -> new EnumMap<>(Animal.Type.class),
                   Collectors.collectingAndThen(
                       Collectors.maxBy(Comparator.comparingInt(Animal::weight)),
                       Optional::orElseThrow
                   )
               )
           );

       EnumSet.allOf(Animal.Type.class)
           .forEach(type -> heaviestAnimalsByType.putIfAbsent(type, null));

       return heaviestAnimalsByType;
   }

   //Task7
   public static Animal getKthOldestAnimal(List<Animal> animals, int k) {
        if (k <= 0 || k > animals.size()) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .toList()
            .get(k - 1);
   }

   //Task8
   public static Optional<Animal> getHeaviestAnimalBelowHeight(List<Animal> animals, int height) {
        return animals.stream()
            .filter(animal -> animal.height() < height)
            .max(Comparator.comparingInt(Animal::weight));
   }

   //Task9
   public static int getTotalAnimalsPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
   }

   //Task10
    public static List<Animal> getAnimalsWithMismatchedAgeAndPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    //Task11
    public static List<Animal> getBitingAnimalsWithHeightOverHeight(List<Animal> animals, int height) {
        if (height <= 0) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .filter(((Predicate<Animal>) Animal::bites)
                .and(animal -> animal.height() > height))
            .toList();
    }

    //Task12
    public static int getAnimalsAmountWithWeightOverHeight(List<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    //Task13
    public static List<Animal> getAnimalsWithNamesMoreThanKWords(List<Animal> animals, int wordsNumber) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > wordsNumber)
            .toList();
    }

    //Task14
    public static boolean hasDogTallerThanHeight(List<Animal> animals, int height) {
        if (height <= 0) {
            throw new IllegalArgumentException();
        }

        return animals.stream()
            .anyMatch(animal -> animal.type().equals(Animal.Type.DOG) && animal.height() > height);
    }

    //Task15
    public static Map<Animal.Type, Integer> getTotalAnimalsWeightByTypeWithAgeRange(
        List<Animal> animals, int minAge, int maxAge) {
        if (minAge < 0 || minAge >= maxAge) {
            throw new IllegalArgumentException();
        }

        EnumMap<Animal.Type, Integer> animalsWeightByTypeWithAgeRange = animals.stream()
            .filter(animal -> minAge <= animal.age() && animal.age() <= maxAge)
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    () -> new EnumMap<>(Animal.Type.class),
                    Collectors.summingInt(Animal::weight)
                )
            );

        EnumSet.allOf(Animal.Type.class)
            .forEach(type -> animalsWeightByTypeWithAgeRange.putIfAbsent(type, 0));

        return animalsWeightByTypeWithAgeRange;
    }

    //Task16
    public static List<Animal> getAnimalsSortedByTypeThenSexThenNameAscending(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    //Task17
    public static boolean doSpidersBiteMoreOftenThanDogs(List<Animal> animals) {
        long spiders = animals.stream()
            .filter((animal -> animal.type().equals(Animal.Type.SPIDER)))
            .count();

        long dogs = animals.stream()
            .filter((animal -> animal.type().equals(Animal.Type.DOG)))
            .count();

        if (spiders == 0 || dogs == 0) {
            return false;
        }

        long bitingSpiders = animals.stream()
            .filter(((Predicate<Animal>) Animal::bites)
                .and(animal -> animal.type().equals(Animal.Type.SPIDER)))
            .count();

        long bitingDogs = animals.stream()
            .filter(((Predicate<Animal>) Animal::bites)
                .and(animal -> animal.type().equals(Animal.Type.DOG)))
            .count();

        return bitingSpiders > bitingDogs;
    }

    //Task18
    @SafeVarargs public static Animal getHeaviestFishInLists(List<Animal>... animals) {
        return Arrays.stream(animals)
            .flatMap(Collection::stream)
            .filter(animal -> animal.type().equals(Animal.Type.FISH))
            .max(Comparator.comparingInt(Animal::weight))
            .orElseThrow();
    }

    //Task19
    public static Map<String, Set<ValidationError>> getValidationErrors(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(Animal::name, AnimalValidator::getValidationErrors));
    }

    //Task20
    public static Map<String, String> getValidationErrorMessages(List<Animal> animals) {
        return getValidationErrors(animals)
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> entry.getValue()
                        .stream()
                        .map(ValidationError::getMessage)
                        .collect(Collectors.joining(" ")
                    )
                )
            );
    }
}
