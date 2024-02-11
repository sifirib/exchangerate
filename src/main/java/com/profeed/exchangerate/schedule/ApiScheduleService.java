package com.profeed.exchangerate.schedule;

import com.profeed.exchangerate.model.Currency;
import com.profeed.exchangerate.repository.ExchangeRateRepository;
import com.profeed.exchangerate.service.currency.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j // log info
@EnableScheduling
@Component
public class ApiScheduleService {
    private final CurrencyService fixerApiServiceImpl;

    private final CurrencyService currencylayerApiServiceImpl;

    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ApiScheduleService(
            @Qualifier("fixerApiServiceImpl") CurrencyService fixerApiServiceImpl,
            @Qualifier("currencylayerApiServiceImpl") CurrencyService currencylayerApiServiceImpl,
            ExchangeRateRepository exchangeRateRepository) {
        this.fixerApiServiceImpl = fixerApiServiceImpl;
        this.currencylayerApiServiceImpl = currencylayerApiServiceImpl;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Scheduled(fixedRate=60*60*1000)
    public void updateEuroAndDollarRates() {
        Currency eur = fixerApiServiceImpl.getExchangeRate("EUR", "TRY");
        Currency usd = currencylayerApiServiceImpl.getExchangeRate("USD", "TRY");
        updateDatabase(eur, usd);
    }

    public void updateDatabase(Currency eur, Currency usd) {
        exchangeRateRepository.save(eur);
        exchangeRateRepository.save(usd);
    }
}
