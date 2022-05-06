package com.ignasbudreika.exchange.repository.interfaces;

import com.ignasbudreika.exchange.model.Currency;

import java.math.BigDecimal;
import java.util.List;

public interface CurrenciesRepository {
    List<Currency> getCurrencies();
    Currency getCurrency(String name);
    BigDecimal getExchangeRate(String currency);
}
