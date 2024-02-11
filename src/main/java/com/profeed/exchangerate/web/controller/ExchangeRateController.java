package com.profeed.exchangerate.web.controller;


import com.profeed.exchangerate.model.CurrencyEnum;

import com.profeed.exchangerate.web.dto.response.CurrencyDto;
import com.profeed.exchangerate.web.service.ExchangeRateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ExchangeRateController {

    private final ExchangeRateServiceImpl exchangeRateServiceImpl;

    @Autowired
    public ExchangeRateController(ExchangeRateServiceImpl exchangeRateServiceImpl) {
        this.exchangeRateServiceImpl = exchangeRateServiceImpl;
    }

    @GetMapping("/filter/by/sourcecurrency/{sourceCurrency}")
    public List<CurrencyDto> filterBySourceCurrency(@PathVariable String sourceCurrency) {
        List<CurrencyDto> currencyDtos = null;

        if (sourceCurrency.equals("all")) {
            currencyDtos = exchangeRateServiceImpl.getAll();
        } else if (sourceCurrency.equals("eur") || sourceCurrency.equals("usd")) {
            currencyDtos = exchangeRateServiceImpl.filterBySourceCurrency(CurrencyEnum.valueOf(sourceCurrency.toUpperCase()));
        }

        return currencyDtos;
    }

    @GetMapping("/filter/by/websource/{webSource}")
    public List<CurrencyDto> filterByWebSource(@PathVariable String webSource) {
        return exchangeRateServiceImpl.filterBySource(webSource);
    }

    @GetMapping("/filter/by/date/{date}/{filterOption}")
    public List<CurrencyDto> filterByDate(@PathVariable String date, @PathVariable(required = false) String filterOption) {

        return switch (filterOption) {
            case "equal" -> exchangeRateServiceImpl.filterByDate(LocalDateTime.parse(date));
            case "before" -> exchangeRateServiceImpl.filterByDateBefore(LocalDateTime.parse(date));
            case "after" -> exchangeRateServiceImpl.filterByDateAfter(LocalDateTime.parse(date));
            default -> exchangeRateServiceImpl.getAll();
        };
    }

    @GetMapping("/filter/by/rate/{rate}/{filterOption}")
    public List<CurrencyDto> filterByRate(@PathVariable String rate, @PathVariable(required = false) String filterOption) {

        return switch (filterOption) {
            case "equal" -> exchangeRateServiceImpl.filterByRate(Double.valueOf(rate));
            case "greater" -> exchangeRateServiceImpl.filterByRateGreaterThanEqual(Double.valueOf(rate));
            case "less" -> exchangeRateServiceImpl.filterByRateLessThanEqual(Double.valueOf(rate));
            default -> exchangeRateServiceImpl.getAll();
        };
    }






}

