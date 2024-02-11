package com.example.currencyexchanger.service;

import com.example.currencyexchanger.model.ApiResponse;
import com.example.currencyexchanger.model.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    @Value("${exchangerate.apikey}")
    private String apiKey;

    public double getExchangeRate(String baseCurrency, String targetCurrency) {
        String apiUrl = "https://open.er-api.com/v6/latest/" + baseCurrency + "?apikey=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        ApiResponse response = restTemplate.getForObject(apiUrl, ApiResponse.class);

        if (response != null && response.getRates() != null && response.getRates().containsKey(targetCurrency)) {
            return response.getRates().get(targetCurrency);
        } else {
            throw new RuntimeException("Unable to fetch exchange rate for " + baseCurrency + " to " + targetCurrency);
        }
    }
}