package com.profeed.exchangerate.web.controller;


import com.profeed.exchangerate.model.CurrencyEnum;

import com.profeed.exchangerate.web.dto.response.CurrencyDto;
import com.profeed.exchangerate.web.service.ExchangeRateServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
@RequestMapping("/filter/by/")
@RestController
public class ExchangeRateController {

    private final ExchangeRateServiceImpl exchangeRateServiceImpl;

    @Autowired
    public ExchangeRateController(ExchangeRateServiceImpl exchangeRateServiceImpl) {
        this.exchangeRateServiceImpl = exchangeRateServiceImpl;
    }

    @GetMapping("sourcecurrency/{sourceCurrency}")
    public List<CurrencyDto> filterBySourceCurrency(@PathVariable @NotNull final String sourceCurrency) {
        List<CurrencyDto> currencyDtos = null;

        if (sourceCurrency.equals("all")) {
            currencyDtos = exchangeRateServiceImpl.getAll();
        } else if (sourceCurrency.equals("eur") || sourceCurrency.equals("usd")) {
            currencyDtos = exchangeRateServiceImpl.filterBySourceCurrency(CurrencyEnum.valueOf(sourceCurrency.toUpperCase()));
        }

        return currencyDtos;
    }

    @GetMapping("websource/{webSource}")
    public List<CurrencyDto> filterByWebSource(@PathVariable @NotNull final String webSource) {
        return exchangeRateServiceImpl.filterBySource(webSource);
    }

    @GetMapping("date/{date}/{filterOption}")
    public List<CurrencyDto> filterByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") @NotNull final LocalDate date,
                                          @PathVariable(required = false) @NotNull final String filterOption) {

        return switch (filterOption) {
            case "equal" -> exchangeRateServiceImpl.filterByDate(date.atTime(0, 0, 0));
            case "before" -> exchangeRateServiceImpl.filterByDateBefore(date.atTime(0, 0, 0));
            case "after" -> exchangeRateServiceImpl.filterByDateAfter(date.atTime(0, 0, 0));
            default -> exchangeRateServiceImpl.getAll();
        };
    }

    @GetMapping("rate/{rate}/{filterOption}")
    public List<CurrencyDto> filterByRate(@PathVariable @NotNull final String rate,
                                          @PathVariable(required = false) @NotNull final String filterOption) {

        return switch (filterOption) {
            case "equal" -> exchangeRateServiceImpl.filterByRate(Double.valueOf(rate));
            case "greater" -> exchangeRateServiceImpl.filterByRateGreaterThanEqual(Double.valueOf(rate));
            case "less" -> exchangeRateServiceImpl.filterByRateLessThanEqual(Double.valueOf(rate));
            default -> exchangeRateServiceImpl.getAll();
        };
    }

}

