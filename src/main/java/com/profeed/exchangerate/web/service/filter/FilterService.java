package com.profeed.exchangerate.web.service.filter;

import com.profeed.exchangerate.model.Currency;
import com.profeed.exchangerate.model.CurrencyEnum;
import com.profeed.exchangerate.web.dto.response.CurrencyDto;

import java.time.LocalDateTime;
import java.util.List;

public interface FilterService {

    List<CurrencyDto> filterByDate(LocalDateTime date);
    List<CurrencyDto> filterBySource(String source);
    List<CurrencyDto> filterByRate(Double rate);
    List<CurrencyDto> filterBySourceCurrency(CurrencyEnum sourceCurrency);
    List<CurrencyDto> filterTargetCurrency(CurrencyEnum targetCurrency);
}
