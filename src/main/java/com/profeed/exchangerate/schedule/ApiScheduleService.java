package com.profeed.exchangerate.schedule;

import com.profeed.exchangerate.model.CurrencylayerCurrency;
import com.profeed.exchangerate.model.FixerCurrency;
import com.profeed.exchangerate.service.CurrencylayerApiServiceImpl;
import com.profeed.exchangerate.service.FixerApiServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Slf4j // log info
@EnableScheduling
@Component
public class ApiScheduleService {

    @Autowired
    private FixerApiServiceImpl fixerApiService;

    @Autowired
    private CurrencylayerApiServiceImpl currencylayerApiService;

    @Scheduled(fixedDelay = 10000)
    public void updateEuroAndDollarRates() {
        FixerCurrency euro = fixerApiService.getEuro();
        CurrencylayerCurrency dollar = currencylayerApiService.getDollar();

        log.info(euro.getRates().toString());
        log.info(dollar.getQuotes().toString());

    }
}
