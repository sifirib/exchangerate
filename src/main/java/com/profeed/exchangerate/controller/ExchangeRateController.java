package com.profeed.exchangerate.controller;

import com.profeed.exchangerate.model.CurrencylayerCurrency;
import com.profeed.exchangerate.model.FixerCurrency;
import com.profeed.exchangerate.service.CurrencylayerApiService;
import com.profeed.exchangerate.service.CurrencylayerApiServiceImpl;
import com.profeed.exchangerate.service.FixerApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateController {

    @Autowired
    private FixerApiServiceImpl fixerApiService;

    @Autowired
    private CurrencylayerApiServiceImpl currencylayerApiService;

    @GetMapping("/euro")
    public ResponseEntity<FixerCurrency> getEuro() {
        FixerCurrency euro = fixerApiService.getEuro();
        return new ResponseEntity<>(euro, HttpStatus.OK);
    }

    @GetMapping("/dollar")
    public ResponseEntity<CurrencylayerCurrency> getDollar() {
        CurrencylayerCurrency dollar = currencylayerApiService.getDollar();
        return new ResponseEntity<>(dollar, HttpStatus.OK);
    }
}

