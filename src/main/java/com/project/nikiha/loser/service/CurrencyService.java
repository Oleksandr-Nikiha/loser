package com.project.nikiha.loser.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.nikiha.loser.data.Currency;
import com.project.nikiha.loser.data.CurrencyDto;
import com.project.nikiha.loser.mapper.CurrencyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final CurrencyMapper currencyMapper;

    @Value("${api.privatbank}")
    private String apiUrl;
    
    @Cacheable(value = "currenciesCache", unless = "#result == null", cacheManager = "cacheManager")
    public List<Currency> getCurrencies() {
        List<Currency> currencies = getCurrenciesByDate(LocalDate.now());
        if (currencies.isEmpty()) {
            currencies = getCurrenciesByDate(LocalDate.now().minusDays(1));
        }
        return currencies;
    }

    public List<Currency> getCurrenciesByDate(LocalDate date) {
        return currencyMapper.map(getApiCurrenciesByDate(date));
    }

    private List<CurrencyDto> getApiCurrenciesByDate(LocalDate date) {
        String requestUrl = buildApiUrlForDate(date);
        return fetchCurrenciesFromApi(requestUrl);
    }

    private String buildApiUrlForDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return String.format(apiUrl, date.format(formatter));
    }

    private List<CurrencyDto> fetchCurrenciesFromApi(String url) {
        try {
            String jsonResponse = restTemplate.getForObject(url, String.class);
            return parseApiResponse(jsonResponse);
        } catch (Exception e) {
            handleException(e);
            return Collections.emptyList();
        }
    }

    private List<CurrencyDto> parseApiResponse(String jsonResponse) throws JsonProcessingException {
        JsonNode root = objectMapper.readTree(jsonResponse);
        JsonNode exchangeRatesNode = root.path("exchangeRate");
        return objectMapper.readValue(exchangeRatesNode.toString(), new TypeReference<>() {});
    }

    private void handleException(Exception e) {
        System.err.println("Error while fetching or parsing currencies: " + e.getMessage());
    }
}
