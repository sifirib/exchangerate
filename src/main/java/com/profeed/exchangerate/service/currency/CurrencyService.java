package com.profeed.exchangerate.service.currency;

import com.profeed.exchangerate.model.Currency;

public interface CurrencyService {
    Currency getExchangeRate(String from, String to);
}
