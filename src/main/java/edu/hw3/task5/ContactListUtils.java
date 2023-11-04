package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ContactListUtils {
    private ContactListUtils() {
    }

    public static List<Contact> parseContacts(String[] fullNames, String sortOrder) {
        if (!isValidSortOrder(sortOrder)) {
            throw new IllegalArgumentException("Порядок сортировки должен быть \"ASC\" или \"DESC\"!");
        }

        if (!isFullNameArrayNotEmpty(fullNames)) {
            return Collections.emptyList();
        }

        List<Contact> contacts = createContacts(fullNames);
        sortContacts(contacts, SortOrder.valueOf(sortOrder.toUpperCase()));

        return contacts;
    }

    private static boolean isFullNameArrayNotEmpty(String[] fullNames) {
        return fullNames != null && fullNames.length != 0;
    }

    private static boolean isValidSortOrder(String sortOrder) {
        return sortOrder.equals(String.valueOf(SortOrder.ASC))
            || sortOrder.equals(String.valueOf(SortOrder.DESC))
            || sortOrder.equals(String.valueOf(SortOrder.ASC).toLowerCase())
            || sortOrder.equals(String.valueOf(SortOrder.DESC).toLowerCase());
    }

    private static List<Contact> createContacts(String[] fullNames) {
        List<Contact> contacts = new ArrayList<>(fullNames.length);
        String fullNameRegex = "^(([A-Z]|[А-Я])([a-z]|[а-я])+)(\\s([A-Z]|[А-Я])([a-z]|[а-я])+)?$";
        Pattern fullNamePattern = Pattern.compile(fullNameRegex);

        for (String fullName : fullNames) {
            Matcher fullNamePatternMatcher = fullNamePattern.matcher(fullName);

            if (!fullNamePatternMatcher.matches()) {
                throw new IllegalArgumentException("Некорректное имя!");
            }

            if (fullName.split(" ").length == 2) {
                String firstName = fullName.split(" ")[0];
                String lastName = fullName.split(" ")[1];
                contacts.add(new Contact(firstName, lastName));
            } else {
                contacts.add(new Contact(fullName));
            }
        }

        return contacts;
    }

    private static void sortContacts(List<Contact> contacts, SortOrder sortOrder) {
        Comparator<Contact> contactComparator = switch (sortOrder) {
            case ASC -> Comparator.naturalOrder();
            case DESC -> Comparator.reverseOrder();
        };

        contacts.sort(contactComparator);
    }
}
