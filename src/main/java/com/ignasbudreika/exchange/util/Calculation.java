package com.ignasbudreika.exchange.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculation {
    public static BigDecimal exchangeCurrencies(BigDecimal quantity, BigDecimal initialRate, BigDecimal finalRate) {
        // formula to convert currencies
        // (quantity * initialRate) / finalRate
        return quantity
                .multiply(initialRate)
                .divide(finalRate, 18, RoundingMode.FLOOR);
    }
}
