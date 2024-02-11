package com.example.currencyexchanger.service;

import com.example.currencyexchanger.model.ApiResponse;
import com.example.currencyexchanger.model.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CurrencyService {

    @Value("${exchangerate.apikey}")
    private String apiKey;

    public double getExchangeRate(String baseCurrency, String targetCurrency) {
        String apiUrl = "https://open.er-api.com/v6/latest/" + baseCurrency + "?apikey=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(apiUrl, ApiResponse.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            ApiResponse response = responseEntity.getBody();

            if (response != null && response.getRates() != null && response.getRates().containsKey(targetCurrency)) {
                return response.getRates().get(targetCurrency);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Exchange rate not found for " + baseCurrency + " to " + targetCurrency);
            }
        } else if (responseEntity.getStatusCode() == HttpStatus.FORBIDDEN) {
            throw new ResponseStatusException(responseEntity.getStatusCode(),
                    "Invalid api key");
        } else {
            throw new ResponseStatusException(responseEntity.getStatusCode(),
                    "Error: " + responseEntity.getStatusCode());
        }
    }
}