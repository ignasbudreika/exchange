package com.ignasbudreika.exchange.service;

import com.ignasbudreika.exchange.model.Currency;
import com.ignasbudreika.exchange.model.Exchange;
import com.ignasbudreika.exchange.repository.interfaces.CurrenciesRepository;
import com.ignasbudreika.exchange.service.interfaces.CurrenciesService;
import com.ignasbudreika.exchange.util.Calculation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CurrenciesServiceImpl implements CurrenciesService {
    CurrenciesRepository currenciesRepository;

    CurrenciesServiceImpl(CurrenciesRepository currenciesRepository) {
        this.currenciesRepository = currenciesRepository;
    }

    @Override
    public List<Currency> getCurrencies() { return currenciesRepository.getCurrencies(); }

    @Override
    public Currency getCurrency(String name) {
        Currency currency = currenciesRepository.getCurrency(name.toUpperCase());
        if (currency == null) {
            throw new NoSuchElementException();
        }

        return currency;
    }

    @Override
    public Exchange exchangeCurrencies(String initialCurrencyName, String finalCurrencyName, BigDecimal quantity) {
        BigDecimal initialCurrencyRate = currenciesRepository.getExchangeRate(initialCurrencyName.toUpperCase());
        if (initialCurrencyRate == null) {
            throw new NoSuchElementException();
        }

        BigDecimal finalCurrencyRate = currenciesRepository.getExchangeRate(finalCurrencyName.toUpperCase());
        if (finalCurrencyRate == null) {
            throw new NoSuchElementException();
        }

        return new Exchange(quantity, initialCurrencyName, finalCurrencyName,
                Calculation.exchangeCurrencies(quantity, initialCurrencyRate, finalCurrencyRate));
    }
}
