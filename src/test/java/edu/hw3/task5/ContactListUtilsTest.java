package edu.hw3.task5;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест сортировки списка контактов")
class ContactListUtilsTest {
    @ParameterizedTest
    @ValueSource(strings = {"ASC", "asc"})
    @DisplayName("Контакты с полным именем, с сортировкой по возрастанию")
    void parseContacts_shouldReturnSortedList_whenASCSortOrder(String sortOrder) {
        //given
        String[] fullNames = new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};

        List<Contact> expectedSortedContacts = List.of(
            new Contact("Thomas", "Aquinas"),
            new Contact("Rene", "Descartes"),
            new Contact("David", "Hume"),
            new Contact("John", "Locke")
        );

        //when
        List<Contact> actualSortedContacts = ContactListUtils.parseContacts(fullNames, sortOrder);

        //then
        assertThat(actualSortedContacts).isEqualTo(expectedSortedContacts);
    }

    @ParameterizedTest
    @ValueSource(strings = {"DESC", "desc"})
    @DisplayName("Контакты с полным именем, с сортировкой по убыванию")
    void parseContacts_shouldReturnSortedList_whenDESCSortOrder(String sortOrder) {
        //given
        String[] fullNames = new String[]{"Paul Erdos", "Leonhard Euler", "Carl Gauss"};

        List<Contact> expectedSortedContacts = List.of(
            new Contact("Carl", "Gauss"),
            new Contact("Leonhard", "Euler"),
            new Contact("Paul", "Erdos")
        );

        //when
        List<Contact> actualSortedContacts = ContactListUtils.parseContacts(fullNames, sortOrder);

        //then
        assertThat(actualSortedContacts).isEqualTo(expectedSortedContacts);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ASC", "asc"})
    @DisplayName("У некоторых контактов отсутствует фамилия, с сортировкой по возрастанию")
    void parseContacts_shouldReturnSortedList_whenSeveralContactsHaveNoLastnameAndASCSortOrder(String sortOrder) {
        //given
        String[] fullNames = new String[]{"John Locke", "Thomas", "David Hume", "Rene"};

        List<Contact> expectedSortedContacts = List.of(
            new Contact("David", "Hume"),
            new Contact("John", "Locke"),
            new Contact("Rene"),
            new Contact("Thomas")
        );

        //when
        List<Contact> actualSortedContacts = ContactListUtils.parseContacts(fullNames, sortOrder);

        //then
        assertThat(actualSortedContacts).isEqualTo(expectedSortedContacts);
    }

    @ParameterizedTest
    @ValueSource(strings = {"DESC", "desc"})
    @DisplayName("У всех контактов отсутствует фамилия, с сортировкой по убыванию")
    void parseContacts_shouldReturnSortedList_whenLastnameIsMissedAndASCSortOrder(String sortOrder) {
        //given
        String[] fullNames = new String[]{"Leonhard", "Carl", "Paul"};

        List<Contact> expectedSortedContacts = List.of(
            new Contact("Paul"),
            new Contact("Leonhard"),
            new Contact("Carl")
        );

        //when
        List<Contact> actualSortedContacts = ContactListUtils.parseContacts(fullNames, sortOrder);

        //then
        assertThat(actualSortedContacts).isEqualTo(expectedSortedContacts);
    }

    @ParameterizedTest
    @ArgumentsSource(NullOrEmptyContactArrayProvider.class)
    @DisplayName("Пустой или null-массив имён")
    void parseContacts_shouldReturnEmptyList_whenNullOrEmptyFullNamesArray(
        String[] invalidNames, String sortOrder) {
        assertThat(ContactListUtils.parseContacts(invalidNames, sortOrder))
            .isEqualTo(Collections.emptyList());

    }

    @ParameterizedTest
    @ArgumentsSource(InvalidContactArrayProvider.class)
    @DisplayName("Некорректное имя, любая сортировка")
    void parseContacts_shouldThrowIllegalArgumentException_whenInvalidFullName(
        String[] invalidNames, String sortOrder) {
        assertThrows(IllegalArgumentException.class,
            () -> ContactListUtils.parseContacts(invalidNames, sortOrder));
    }

    @Test
    @DisplayName("Некорректный порядок сортировки")
    void parseContacts_shouldThrowIllegalArgumentException_whenInvalidSortOrder() {
        //given
        String[] fullNames = new String[]{"John Locke", "Thomas", "David Hume", "Rene"};

        //then
        assertThrows(IllegalArgumentException.class,
            () -> ContactListUtils.parseContacts(fullNames, "sortOrder"));
    }

    static class InvalidContactArrayProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of(new String[]{"Иванов 1"}, "ASC"),
                Arguments.of(new String[]{"* Иванов"}, "asc"),
                Arguments.of(new String[]{"B Иванов"}, "DESC"),
                Arguments.of(new String[]{"Bладимир И"}, "desc"),
                Arguments.of(new String[]{"ВасяПупкин"}, "ASC"),
                Arguments.of(new String[]{"вася Пупкин"}, "asc"),
                Arguments.of(new String[]{"Вася пупкин"}, "DESC"),
                Arguments.of(new String[]{" "}, "desc"),
                Arguments.of(new String[]{""}, "desc")
            );
        }
    }

    static class NullOrEmptyContactArrayProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of(new String[]{}, "ASC"),
                Arguments.of(null, "ASC")
            );
        }
    }
}
