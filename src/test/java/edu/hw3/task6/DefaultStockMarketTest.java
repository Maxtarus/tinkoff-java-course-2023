package edu.hw3.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест биржи")
class DefaultStockMarketTest {
    DefaultStockMarket stockMarket;

    @BeforeEach
    void initNewDefaultStockMarket() {
        stockMarket = new DefaultStockMarket();
    }

//    @Test
//    void testEmpty() {
//        assertThat(defaultStockMarket.size()).isEqualTo(0);
//    }

    @Test
    void testAddStock() {
        //given
        Stock stock = new Stock(UUID.randomUUID(), 3500.0);

        //when
        stockMarket.add(stock);

        //then
        assertThat(stockMarket.getStocks().contains(stock)).isTrue();
    }

    @Test
    void testRemoveStock() {
        //given
        Stock stock = new Stock(UUID.randomUUID(), 3500.0);
        stockMarket.add(stock);

        //when
        stockMarket.remove(stock);

        //then
        assertThat(stockMarket.getStocks().contains(stock)).isFalse();
    }

    @Test
    void testMostValuableStock() {
        //given
        List<Stock> stocks = List.of(
            new Stock(UUID.randomUUID(), 400.0),
            new Stock(UUID.randomUUID(), 500.0),
            new Stock(UUID.randomUUID(), 350.0)
        );

        for (Stock stock : stocks) {
            stockMarket.add(stock);
        }

        Stock expectedMostValuableStock = stockMarket.getStocks().peek();

        //when
        Stock actualMostValuableStock = stockMarket.mostValuableStock();

        // Assert
        assertThat(actualMostValuableStock).isEqualTo(expectedMostValuableStock);
    }
}
