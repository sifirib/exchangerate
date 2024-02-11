package com.profeed.exchangerate.web.service;

import com.profeed.exchangerate.web.dto.response.CurrencyDto;

import java.util.List;

public interface ExchangeRateService {

    public List<CurrencyDto> getAll();
}
