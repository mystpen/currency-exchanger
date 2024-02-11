package com.example.currencyexchanger.controller;

import com.example.currencyexchanger.model.CurrencyResponse;
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
    public ResponseEntity<Object> getExchangeRate(@RequestParam String base,
                                                @RequestParam String target) {
        double rate = currencyService.getExchangeRate(base, target);
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setBaseCurrency(base);
        response.setTargetCurrency(target);
        response.setExchangeRate(rate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/currency-convert")
    public ResponseEntity<Object> getCurrency(@RequestParam String base,
                                              @RequestParam String target,
                                              @RequestParam double amount) {
        double rate = currencyService.getExchangeRate(base, target);

        CurrencyResponse response = new CurrencyResponse();
        response.setBaseCurrency(base);
        response.setBaseAmount(amount);
        response.setTargetCurrency(target);
        response.setTargetAmount(rate*amount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}