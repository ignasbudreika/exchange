package com.ignasbudreika.exchange.service.interfaces;

import com.ignasbudreika.exchange.model.Currency;
import com.ignasbudreika.exchange.model.Exchange;

import java.math.BigDecimal;
import java.util.List;

public interface CurrenciesService {
    List<Currency> getCurrencies();
    Currency getCurrency(String currency);
    Exchange exchangeCurrencies(String initialCurrency, String finalCurrency, BigDecimal quantity);
}
