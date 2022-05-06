package com.ignasbudreika.exchange.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ignasbudreika.exchange.model.ErrorMessage;
import com.ignasbudreika.exchange.model.Exchange;
import com.ignasbudreika.exchange.service.interfaces.CurrenciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

@RestController
@Validated
@RequestMapping("/currencies")
public class CurrenciesController {
    CurrenciesService currenciesService ;

    @Autowired
    CurrenciesController(CurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
    }

    @GetMapping("")
    public ResponseEntity<String> getExchangeRates() {
        return ResponseEntity.ok().body(new Gson().toJson(currenciesService.getCurrencies()));
    }

    @GetMapping("/{currency}")
    public ResponseEntity<String> getExchangeRate(@PathVariable @NotBlank(message = "currency is required") String currency) {
        return ResponseEntity.ok().body(new Gson().toJson(currenciesService.getCurrency(currency)));
    }

    @GetMapping("/calculate")
    public ResponseEntity<String> exchangeCurrency(
            @RequestParam @NotBlank(message = "initial currency is required") String initialCurrency,
            @RequestParam @NotBlank(message = "final currency is required") String finalCurrency,
            @RequestParam @NotNull(message = "quantity is required") @Positive(message = "quantity must be greater than 0") BigDecimal quantity) {
        Exchange result = currenciesService.exchangeCurrencies(initialCurrency, finalCurrency, quantity);
        if (result == null) {
            return handleBadRequest("could not calculate the result");
        }

        return ResponseEntity.ok().body(new Gson().toJson(result));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return handleBadRequest("invalid request parameter: " + e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<String> handleNoSuchElementException() {
        return handleBadRequest("currency not found");
    }

    ResponseEntity<String> handleBadRequest(String message) {
        return ResponseEntity.badRequest().body(new Gson().toJson(new ErrorMessage(message)));
    }
}
