package com.profeed.exchangerate.web.controller;

import com.profeed.exchangerate.mapper.CurrencyFixerMapper;
import com.profeed.exchangerate.mapper.CurrencyLayerMapper;
import com.profeed.exchangerate.model.Currency;
import com.profeed.exchangerate.service.CurrencylayerApiServiceImpl;
import com.profeed.exchangerate.service.FixerApiServiceImpl;
import com.profeed.exchangerate.service.currency.CurrencyService;
import com.profeed.exchangerate.web.dto.response.CurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateController {

    private CurrencyService fixerApiService;
    private CurrencyService currencylayerApiService;

    @Autowired
    public ExchangeRateController(FixerApiServiceImpl fixerApiService, CurrencylayerApiServiceImpl currencylayerApiService) {
        this.fixerApiService = fixerApiService;
        this.currencylayerApiService = currencylayerApiService;
    }

    @GetMapping("/euro")
    public CurrencyDto getEuro() {
        Currency exchangeRate = fixerApiService.getExchangeRate("USD", "TRY");
        CurrencyDto currencyDto = CurrencyFixerMapper.toDto(exchangeRate);
        return currencyDto;
    }

    @GetMapping("/dollar")
    public CurrencyDto getDollar() {
        Currency exchangeRate = currencylayerApiService.getExchangeRate("USD", "TRY");
        CurrencyDto currencyDto = CurrencyLayerMapper.toDto(exchangeRate);
        return currencyDto;
    }
}

