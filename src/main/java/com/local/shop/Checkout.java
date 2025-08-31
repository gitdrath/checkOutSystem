package com.local.shop;

import java.math.BigDecimal;
import java.util.List;

/**
 * A checkout system for a shop selling apples and oranges.
 */
public class Checkout {

    private static final BigDecimal APPLE_PRICE = new BigDecimal("0.60");
    private static final BigDecimal ORANGE_PRICE = new BigDecimal("0.25");

    /**
     * Calculates the total cost of a list of items.
     * Apples cost 60p and oranges cost 25p. Unknown items are ignored.
     *
     * @param items The list of items to be scanned at the till.
     * @return The total cost as a BigDecimal to ensure precision.
     */
    public BigDecimal calculateTotal(List<String> items) {
        BigDecimal total = new BigDecimal("0.00");

        if (items == null || items.isEmpty()) {
            return total;
        }

        for (String item : items) {
            String itemName = item.trim().toLowerCase();
            switch (itemName) {
                case "apple":
                    total = total.add(APPLE_PRICE);
                    break;
                case "orange":
                    total = total.add(ORANGE_PRICE);
                    break;
                default:
                    // Ignore unknown items
                    break;
            }
        }
        return total;
    }
}