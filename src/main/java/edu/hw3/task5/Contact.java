package edu.hw3.task5;

import org.jetbrains.annotations.NotNull;

public record Contact(
    String firstName,
    String lastName
) implements Comparable<Contact> {

    public Contact(String firstName) {
        this(firstName, "");
    }

    @Override
    public String toString() {
        if (lastName.isEmpty()) {
            return firstName;
        }

        return firstName + " " + lastName;
    }

    @Override
    public int compareTo(@NotNull Contact otherContact) {
        int firstNameComparison = this.firstName.compareTo(otherContact.firstName);

        if (this.lastName.isEmpty() || otherContact.lastName.isEmpty()) {
            return firstNameComparison;
        }

        int lastNameComparison = this.lastName.compareTo(otherContact.lastName);

        if (lastNameComparison == 0) {
            return firstNameComparison;
        }

        return lastNameComparison;
    }
}
