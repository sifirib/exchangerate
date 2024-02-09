package com.profeed.exchangerate.service;

import com.profeed.exchangerate.model.Currency;
import com.profeed.exchangerate.model.CurrencyEnum;
import com.profeed.exchangerate.repository.ExchangeRateRepository;
import com.profeed.exchangerate.service.currency.CurrencyService;
import com.profeed.exchangerate.service.dto.fixer.FixerCurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FixerApiServiceImpl implements CurrencyService {

    private RestTemplate restTemplate;

    private HttpHeaders httpHeaders;

    @Value("${api.fixer.euro_api}")
    private String fixerApi;

    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public FixerApiServiceImpl(RestTemplate restTemplate, HttpHeaders httpHeaders, ExchangeRateRepository exchangeRateRepository) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public Currency getExchangeRate(String from, String to) {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<FixerCurrencyDto> response = restTemplate.exchange(fixerApi, HttpMethod.GET, entity, FixerCurrencyDto.class);
        FixerCurrencyDto fixerCurrencyDto = response.getBody();
        Currency currency = Currency.builder()
                .date(LocalDateTime.now())
                .source("fixer")
                .sourceCurrency(CurrencyEnum.USD)
                .targetCurrency(CurrencyEnum.TRY)
                .rate(fixerCurrencyDto.getRates().getTRY())
                .build();
        Currency savedCurrency = exchangeRateRepository.save(currency);
        return savedCurrency;
    }
}
