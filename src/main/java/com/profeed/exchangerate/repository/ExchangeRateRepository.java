package com.profeed.exchangerate.repository;

import com.profeed.exchangerate.model.Currency;
import com.profeed.exchangerate.model.CurrencyEnum;
import com.profeed.exchangerate.web.dto.response.CurrencyDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<Currency, Long> {

    List<Currency> findBySource(String source);

    List<Currency> findByDate(LocalDateTime date);

    List<Currency> findByDateBefore(LocalDateTime date);

    List<Currency> findByDateAfter(LocalDateTime date);

    List<Currency> findBySourceCurrency(CurrencyEnum sourceCurrency);

    List<Currency> findByTargetCurrency(CurrencyEnum targetCurrency);

    List<Currency> findByRateEquals(Double rate);

    List<Currency> findByRateGreaterThanEqual(Double rate);

    List<Currency> findByRateLessThanEqual(Double rate);



}
