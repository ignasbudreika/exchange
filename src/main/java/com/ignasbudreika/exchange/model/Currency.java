package com.ignasbudreika.exchange.model;

import java.math.BigDecimal;

public record Currency(String name, BigDecimal exchangeRate) {}
