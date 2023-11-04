package edu.hw3.task6;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public record Stock(
    UUID stockId,
    double price
) implements Comparable<Stock> {
    @Override
    public int compareTo(@NotNull Stock otherStock) {
        return Double.compare(otherStock.price, this.price);
    }
}
