# Java Back-End Developer task

REST currency exchange API written in Java using SpringBoot

## REST API

### `` GET /api/currencies ``

Returns a list of all available currencies and their exchange rates to EUR

#### Success response body structure

```
[
    {
        "name": "name1",
        "exchangeRate": 1
    },
    {
        "name": "name2",
        "exchangeRate": 2
    },
    ...
]
```

### `` GET /api/currencies/{currency} ``

Returns an exchange rate to EUR of provided currency if available

#### Success response body structure

```
{
    "name": "name1",
    "exchangeRate": 1
}
```

### `` GET /api/currencies/calculate ``

#### Required query parameters

<ul>
  <li>initialCurrency</li>
  <li>finalCurrency</li>
  <li>quantity</li>
</ul>

Returns an exchanged quantity of initial currency converted to final currency with an accuracy of 18 digits after the cable

#### Success response body structure

```
{
    "quantity": 1,
    "initialCurrency": "name1",
    "finalCurrency": "name2",
    "result": 1.999999999999999999
}
```

#

#### Unified failure response body structure

```
{
    "error": "some error has occured"
}
```

# Testing 

API endpoints were tested out using REST Assured
