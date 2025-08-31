package com.local.shop;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Checkout system with special offwrs
 *
 *
 */
class CheckoutTest {

    private final Checkout checkout = new Checkout();

    @Test
    void testGivenExampleScenario() {
        List<String> items = List.of("Apple", "Apple", "Orange", "Apple");
        BigDecimal expectedTotal = new BigDecimal("1.45"); // (Apple free) + 0.60 + 0.25 + 0.60
        BigDecimal actualTotal = checkout.calculateTotal(items);
        assertEquals(expectedTotal, actualTotal, "Total should be £1.45 for [Apple, Apple, Orange, Apple] with offers");
    }

    @Test
    void testEmptyList() {
        List<String> items = List.of();
        BigDecimal expectedTotal = new BigDecimal("0.00");
        BigDecimal actualTotal = checkout.calculateTotal(items);
        assertEquals(expectedTotal, actualTotal, "Total for an empty list should be £0.00");
    }

    @Test
    void testSingleApple() {
        List<String> items = List.of("Apple");
        BigDecimal expectedTotal = new BigDecimal("0.60");
        BigDecimal actualTotal = checkout.calculateTotal(items);
        assertEquals(expectedTotal, actualTotal, "Total for a single apple should be £0.60");
    }

    @Test
    void testSingleOrange() {
        List<String> items = List.of("Orange");
        BigDecimal expectedTotal = new BigDecimal("0.25");
        BigDecimal actualTotal = checkout.calculateTotal(items);
        assertEquals(expectedTotal, actualTotal, "Total for a single orange should be £0.25");
    }

    @Test
    void testBuyOneGetOneOnApples() {
        // Two apples, one is free. Total should be £0.60.
        List<String> items = List.of("Apple", "Apple");
        BigDecimal expectedTotal = new BigDecimal("0.60");
        BigDecimal actualTotal = checkout.calculateTotal(items);
        assertEquals(expectedTotal, actualTotal, "Two apples should cost £0.60 ");

        // Three apples, one is free. Total should be £1.20 (2 * 0.60).
        List<String> items2 = List.of("Apple", "Apple", "Apple");
        BigDecimal expectedTotal2 = new BigDecimal("1.20");
        BigDecimal actualTotal2 = checkout.calculateTotal(items2);
        assertEquals(expectedTotal2, actualTotal2, "Three apples should cost £1.20");

    }

    @Test
    void testThreeForTwoOnOranges() {
        // Three oranges, one is free. Total should be £0.50 (2 * 0.25).
        List<String> items = List.of("Orange", "Orange", "Orange");
        BigDecimal expectedTotal = new BigDecimal("0.50");
        BigDecimal actualTotal = checkout.calculateTotal(items);
        assertEquals(expectedTotal, actualTotal, "Three oranges should cost £0.50");

        // Five oranges, one is free. Total should be £1.00 (4 * 0.25).
        List<String> items2 = List.of("Orange", "Orange", "Orange", "Orange","Orange");
        BigDecimal expectedTotal2 = new BigDecimal("1.00");
        BigDecimal actualTotal2 = checkout.calculateTotal(items2);
        assertEquals(expectedTotal2, actualTotal2, "Four oranges should cost £1.00");
    }

    @Test
    void testCombinedAppleOrangeOffers() {
        // 3 Apples + 4 Oranges
        // Apples: £1.20, Oranges: £0.75. Total: £1.95.
        List<String> items = List.of("Apple", "Orange", "Apple", "Orange", "Apple", "Orange","Orange");
        BigDecimal expectedTotal = new BigDecimal("1.95");
        BigDecimal actualTotal = checkout.calculateTotal(items);
        assertEquals(expectedTotal, actualTotal, "Total should be £1.95");

    }
}
