package com.ignasbudreika.exchange;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class CurrenciesControllerEndpointTests extends TestsSetup {
    @Test
    public void getCurrencies_ReturnsSuccess() {
        given().when().get("/currencies").then().statusCode(200);
    }

    @Test
    public void validCurrency_getExchangeRate_ReturnsSuccess() {
        given().when().get("/currencies/usd").then().statusCode(200);
    }

    @Test
    public void invalidCurrency_getExchangeRate_ReturnsBadRequest() {
        given().when().get("/currencies/usdt").then().statusCode(400);
    }

    @Test
    public void validParams_exchangeCurrency_ReturnsSuccess() {
        given().when().get("/currencies/calculate?initialCurrency=usd&finalCurrency=eur&quantity=1").then().statusCode(200);
    }

    @Test
    public void missingParams_exchangeCurrency_ReturnsBadRequest() {
        given().when().get("/currencies/calculate").then().statusCode(400);
    }

    @Test
    public void missingInitialCurrencyParam_exchangeCurrency_ReturnsBadRequest() {
        given().when().get("/currencies/calculate?finalCurrency=eur&quantity=1").then().statusCode(400);
    }

    @Test
    public void missingFinalCurrencyParam_exchangeCurrency_ReturnsBadRequest() {
        given().when().get("/currencies/calculate?initialCurrency=eur&quantity=1").then().statusCode(400);
    }

    @Test
    public void missingQuantityParam_exchangeCurrency_ReturnsBadRequest() {
        given().when().get("/currencies/calculate?initialCurrency=usd&finalCurrency=eur").then().statusCode(400);
    }

    @Test
    public void blankInitialCurrencyParam_exchangeCurrency_ReturnsBadRequest() {
        given().when().get("/currencies/calculate?initialCurrency= &finalCurrency=eur&quantity=1").then().statusCode(400);
    }

    @Test
    public void negativeQuantityParam_exchangeCurrency_ReturnsBadRequest() {
        given().when().get("/currencies/calculate?initialCurrency=usd&finalCurrency=eur&quantity=-1").then().statusCode(400);
    }
}
