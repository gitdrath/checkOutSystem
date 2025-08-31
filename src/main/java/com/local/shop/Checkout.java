package com.local.shop;

import java.math.BigDecimal;
import java.util.List;

/**
 * A checkout system for a shop selling apples and oranges (with special offers).
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
        if (items == null || items.isEmpty()) {
            return new BigDecimal("0.00");
        }

        long appleCount = 0;
        long orangeCount = 0;

        for (String item : items) {
            String itemName = item.trim().toLowerCase();
            switch (itemName) {
                case "apple":
                    appleCount++;
                    break;
                case "orange":
                    orangeCount++;
                    break;
                default:
                    // Ignore unknown items
                    break;
            }
        }

        // Apply "Buy One, Get One Free" offer for apples
        // The number of paid apples is the total count divided by 2  plus the remainder.
        long totalApplesToBePaid = appleCount / 2 + appleCount % 2;
        BigDecimal applesTotalPrice = APPLE_PRICE.multiply(new BigDecimal(totalApplesToBePaid));

        // Apply "3 for the price of 2" offer for oranges
        // The number of paid oranges is the total count divided by 3 then multiplied by 2, plus the remainder.
        long totalOrangesToBePaid = (orangeCount / 3) * 2 + orangeCount % 3;
        BigDecimal orangesTotalPrice = ORANGE_PRICE.multiply(new BigDecimal(totalOrangesToBePaid));

        return applesTotalPrice.add(orangesTotalPrice);
    }
}