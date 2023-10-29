package edu.hw3.task2;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class BracketsClusteringUtils {
    private BracketsClusteringUtils() {
    }

    private static final Set<Character> BRACKETS = Set.of('(', '[', '{', ')', ']', '}');

    private static boolean isValidString(String str) {
        if (str == null || str.isEmpty() || str.length() % 2 != 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!BRACKETS.contains(c)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isOpeningBracket(char bracket) {
        return bracket == '(' || bracket == '[' || bracket == '{';
    }

    private static boolean firstDequeElementCanBePairedWithClosingBracket(char firstDequeElement, char bracket) {
        return (firstDequeElement == '(' && bracket == ')')
            || (firstDequeElement == '[' && bracket == ']')
            || (firstDequeElement == '{' && bracket == '}');
    }

    private static boolean isBalanced(String str) {
        if (!isValidString(str)) {
            throw new IllegalArgumentException("Неверный формат строки!");
        }

        Deque<Character> deque = new LinkedList<>();

        for (char bracket: str.toCharArray()) {
            if (isOpeningBracket(bracket)) {
                deque.addFirst(bracket);
            } else {
                if (!deque.isEmpty() && firstDequeElementCanBePairedWithClosingBracket(deque.peekFirst(), bracket)) {
                    deque.removeFirst();
                } else {
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }

    public static List<String> clusterize(String str) {
        if (!isBalanced(str)) {
            throw new IllegalArgumentException("Строка не сбалансирована!");
        }

        List<String> clusters = new ArrayList<>();
        int openingBracketsNumber = 0;
        StringBuilder cluster = new StringBuilder();

        for (char bracket: str.toCharArray()) {
            if (isOpeningBracket(bracket)) {
                cluster.append(bracket);
                openingBracketsNumber++;
            } else {
                cluster.append(bracket);
                openingBracketsNumber--;
                if (openingBracketsNumber == 0) {
                    clusters.add(cluster.toString());
                    cluster.setLength(0);
                }
            }
        }

        return clusters;
    }
}
