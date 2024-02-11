package com.profeed.exchangerate.web.controller;

import com.profeed.exchangerate.mapper.CurrencyFixerMapper;
import com.profeed.exchangerate.mapper.CurrencyLayerMapper;
import com.profeed.exchangerate.model.Currency;
import com.profeed.exchangerate.model.CurrencyEnum;
import com.profeed.exchangerate.repository.ExchangeRateRepository;
import com.profeed.exchangerate.service.CurrencylayerApiServiceImpl;
import com.profeed.exchangerate.service.FixerApiServiceImpl;
import com.profeed.exchangerate.service.currency.CurrencyService;
import com.profeed.exchangerate.web.dto.response.CurrencyDto;
import com.profeed.exchangerate.web.service.ExchangeRateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ExchangeRateController {

    private final ExchangeRateServiceImpl exchangeRateServiceImpl;

    @Autowired
    public ExchangeRateController(ExchangeRateServiceImpl exchangeRateServiceImpl) {
        this.exchangeRateServiceImpl = exchangeRateServiceImpl;
    }

    @GetMapping("/get/by/sourcecurrency/{sourceCurrency}")
    public List<CurrencyDto> getCurrency(@PathVariable String sourceCurrency) {
        List<CurrencyDto> currencyDtos = null;

        if (sourceCurrency.equals("all")) {
            currencyDtos = exchangeRateServiceImpl.getAll();
        } else if (sourceCurrency.equals("eur") || sourceCurrency.equals("usd")) {
            currencyDtos = exchangeRateServiceImpl.filterBySourceCurrency(CurrencyEnum.valueOf(sourceCurrency.toUpperCase()));
        }

        return currencyDtos;
    }

    @GetMapping("/get/by/websource/{webSource}")
    public List<CurrencyDto> getUsd(@PathVariable String webSource) {
        return exchangeRateServiceImpl.filterBySource(webSource);
    }


}

