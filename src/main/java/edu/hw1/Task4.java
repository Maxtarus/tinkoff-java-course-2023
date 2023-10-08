package edu.hw1;

public final class Task4 {
    private Task4() {
    }

    public static String fixString(String wrongString) {
        //Проверка на null
        if (wrongString == null) {
            throw new IllegalArgumentException("Строка не может быть null!");
        }

        StringBuilder rightString = new StringBuilder();
        int wrongStringLength = wrongString.length();

        for (int i = 0; i < wrongStringLength; i += 2) {
            if (i != wrongStringLength - 1) {
                rightString.append(wrongString.charAt(i + 1)).append(wrongString.charAt(i));
            } else {
                rightString.append(wrongString.charAt(i));
            }
        }

        return rightString.toString();
    }
}
