package edu.project1.dictionary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public final class RussianDictionary implements Dictionary {
    private static final String[] WORDS = {"тинькофф", "образование", "проект", "виселица"};
    private static final Random RANDOM = new Random();
    private static final Set<Character> AVAILABLE_LETTERS = new HashSet<>(
        Arrays.asList(
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й',
            'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф',
            'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'
        )
    );

    public static Set<Character> getAvailableLetters() {
        return AVAILABLE_LETTERS;
    }

    public static RussianDictionary getInstance() {
        return DictionaryStorage.instance;
    }

    @Override
    public @NotNull String getRandomWord() {
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }

    private static class DictionaryStorage {
        public static RussianDictionary instance = new RussianDictionary();
    }

}
