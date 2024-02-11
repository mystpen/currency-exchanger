package com.example.currencyexchanger.controller;

import com.example.currencyexchanger.model.ExchangeRateResponse;
import com.example.currencyexchanger.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class Controller {

    private final CurrencyService currencyService;

    @Autowired
    public Controller(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/exchange-rate")
    public ExchangeRateResponse getExchangeRate(@RequestParam String baseCurrency, @RequestParam String targetCurrency) {
        double rate = currencyService.getExchangeRate(baseCurrency, targetCurrency);
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setBaseCurrency(baseCurrency);
        response.setTargetCurrency(targetCurrency);
        response.setExchangeRate(rate);
        return response;
    }

    @GetMapping("/currency-convert")
    public ResponseEntity<Object> getCurrency(){
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}