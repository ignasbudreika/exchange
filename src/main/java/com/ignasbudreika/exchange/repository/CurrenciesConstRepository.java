package com.ignasbudreika.exchange.repository;

import com.ignasbudreika.exchange.model.Currency;
import com.ignasbudreika.exchange.repository.interfaces.CurrenciesRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class CurrenciesConstRepository implements CurrenciesRepository {
    final Map<String, BigDecimal> currencies = Map.of(
            "EUR", new BigDecimal("1.0"),
            "USD", new BigDecimal("0.809552722"),
            "GBP", new BigDecimal("1.126695"),
            "BTC", new BigDecimal("6977.089657"),
            "FKE", new BigDecimal("0.025"),
            "ETH", new BigDecimal("685.2944747"));

    @Override
    public List<Currency> getCurrencies() {
        List<Currency> currencyList = new ArrayList<>();
        for (var currency: currencies.entrySet()) {
            currencyList.add(new Currency(currency.getKey(), currency.getValue()));
        }

        return currencyList;
    }

    public Currency getCurrency(String name) {
        return currencies.containsKey(name) ?
                new Currency(name, currencies.get(name)) : null;
    }

    @Override
    public BigDecimal getExchangeRate(String name) {
        return currencies.get(name);
    }
}
