package com.profeed.exchangerate.service;

import com.profeed.exchangerate.model.Currency;
import com.profeed.exchangerate.model.CurrencyEnum;
import com.profeed.exchangerate.repository.ExchangeRateRepository;
import com.profeed.exchangerate.service.currency.CurrencyService;
import com.profeed.exchangerate.service.dto.currenylayer.CurrencylayerDto;
import com.profeed.exchangerate.service.dto.fixer.FixerCurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CurrencylayerApiServiceImpl implements CurrencyService {

    private RestTemplate restTemplate;

    private HttpHeaders httpHeaders;

    @Value("${api.currencylayer.dollar_api}")
    private String currencylayerApi;

    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public CurrencylayerApiServiceImpl(RestTemplate restTemplate, HttpHeaders httpHeaders, ExchangeRateRepository exchangeRateRepository) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public Currency getExchangeRate(String from, String to) {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<CurrencylayerDto> response = restTemplate.exchange(currencylayerApi, HttpMethod.GET, entity, CurrencylayerDto.class);
        CurrencylayerDto currencylayerDto =  response.getBody();
        Currency currency = Currency.builder()
                .sourceCurrency(CurrencyEnum.USD)
                .targetCurrency(CurrencyEnum.TRY)
                .rate(currencylayerDto.getQuotes().getUSDTRY())
                .date(LocalDateTime.now())
                .source("CurrencyLayer")
                .build();
        Currency savedCurrency = exchangeRateRepository.save(currency);
        return savedCurrency;
    }
}
