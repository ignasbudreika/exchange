package com.ignasbudreika.exchange.model;

import java.math.BigDecimal;

public record Exchange(BigDecimal quantity, String initialCurrency, String finalCurrency, BigDecimal result) {}
