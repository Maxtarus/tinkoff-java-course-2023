package edu.hw1;

public final class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(long number) {
        long nonNegativeNumber = (number >= 0) ? number : -number;
        String strNumber = String.valueOf(nonNegativeNumber);

        if (isPalindrome(strNumber)) {
            return true;
        } else if (hasOddDigitsNumber(strNumber)) {
            return false;
        } else {
            while (strNumber.length() > 1) {
                if (isPalindrome(strNumber)) {
                    return true;
                }
                strNumber = getDescendant(strNumber);
            }
        }
        return false;
    }

    private static boolean isPalindrome(String strNumber) {
        String reversedStrNumber = new StringBuilder(strNumber).reverse().toString();
        return strNumber.equals(reversedStrNumber);
    }

    private static String getDescendant(String strNumber) {
        StringBuilder descendant = new StringBuilder();
        for (int i = 0; i < strNumber.length(); i += 2) {
            int sum = Character.getNumericValue(strNumber.charAt(i))
                + Character.getNumericValue(strNumber.charAt(i + 1));
            descendant.append(sum);
        }
        return descendant.toString();
    }

    private static boolean hasOddDigitsNumber(String strNumber) {
        return strNumber.length() % 2 != 0;
    }
}
