package com.profeed.exchangerate.web.service;

import com.profeed.exchangerate.mapper.CurrencyFixerMapper;
import com.profeed.exchangerate.model.Currency;
import com.profeed.exchangerate.model.CurrencyEnum;
import com.profeed.exchangerate.repository.ExchangeRateRepository;
import com.profeed.exchangerate.web.dto.response.CurrencyDto;
import com.profeed.exchangerate.web.service.filter.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService, FilterService {

    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    private List<CurrencyDto> currencyToDto (List<Currency> currencies) {
        List<CurrencyDto> currencyDtos = new ArrayList<>();

        for (Currency currency : currencies) {
            currencyDtos.add(CurrencyFixerMapper.toDto(currency));
        }

        return currencyDtos;
    }


    @Override
    public List<CurrencyDto> getAll() {
        List<Currency> exchangeRates = exchangeRateRepository.findAll();

        return currencyToDto(exchangeRates);
    }

    @Override
    public List<CurrencyDto> filterBySource(String source) {
        List<Currency> exchangeRates = exchangeRateRepository.findBySource(source);

        return currencyToDto(exchangeRates);
    }

    @Override
    public List<CurrencyDto> filterByDate(LocalDateTime date) {
        List<Currency> exchangeRates = exchangeRateRepository.findByDate(date);

        return currencyToDto(exchangeRates);
    }

    @Override
    public List<CurrencyDto> filterBySourceCurrency(CurrencyEnum sourceCurrency) {
        List<Currency> exchangeRates = exchangeRateRepository.findBySourceCurrency(sourceCurrency);

        return currencyToDto(exchangeRates);
    }

    @Override
    public List<CurrencyDto> filterTargetCurrency(CurrencyEnum targetCurrency) {
        List<Currency> exchangeRates = exchangeRateRepository.findByTargetCurrency(targetCurrency);

        return currencyToDto(exchangeRates);
    }

    @Override
    public List<CurrencyDto> filterByRate(Double rate) {
        List<Currency> exchangeRates = exchangeRateRepository.findByRateEquals(rate);

        return currencyToDto(exchangeRates);
    }


}
