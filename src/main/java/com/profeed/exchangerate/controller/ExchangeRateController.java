package com.profeed.exchangerate.controller;

import com.profeed.exchangerate.model.Currency;
import com.profeed.exchangerate.service.FixerApiService;
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

    @GetMapping("/")
    public ResponseEntity<Currency> getEuro(){
        Currency euro = fixerApiService.getEuro();
        return new ResponseEntity<>(euro, HttpStatus.OK);

    }
}

