package com.profeed.exchangerate.mapper;

import com.profeed.exchangerate.model.Currency;
import com.profeed.exchangerate.web.dto.response.CurrencyDto;
import org.jetbrains.annotations.NotNull;

public class CurrencyFixerMapper {
    public static CurrencyDto toDto(@NotNull Currency currency){
        return CurrencyDto.builder()
                .sourceWebSite(currency.getSource())
                .sourceCurrency(currency.getSourceCurrency().name())
                .targetCurrency(currency.getTargetCurrency().name())
                .rate(currency.getRate())
                .date(currency.getDate())
                .build();

    }
}
