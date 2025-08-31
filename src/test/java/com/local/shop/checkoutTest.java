package com.local.shop;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Checkout system.
 *
 */
class CheckoutTest {

    private final Checkout checkout = new Checkout();

    @Test
    void testGivenExampleScenario() {
        List<String> items = List.of("Apple", "Apple", "Orange", "Apple");
        BigDecimal expectedTotal = new BigDecimal("2.05");
        BigDecimal actualTotal = checkout.calculateTotal(items);
        assertEquals(expectedTotal, actualTotal, "Total should be £2.05 for [Apple, Apple, Orange, Apple]");
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
    void testCaseInsensitiveItems() {
        List<String> items = List.of("APPLE", "ORANGE", "Apple");
        BigDecimal expectedTotal = new BigDecimal("1.45"); // 0.60 + 0.25 + 0.60
        BigDecimal actualTotal = checkout.calculateTotal(items);
        assertEquals(expectedTotal, actualTotal, "Total should handle case Insenitive items correctly");
    }

    @Test
    void testUnknownItem() {
        List<String> items = List.of("Apple", "Pinapple", "Orange");
        BigDecimal expectedTotal = new BigDecimal("0.85"); // 0.60 + 0.25
        BigDecimal actualTotal = checkout.calculateTotal(items);
        assertEquals(expectedTotal, actualTotal, "Total should ignore unknown items");
    }
}
