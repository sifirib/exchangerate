package com.profeed.exchangerate.repository;

import com.profeed.exchangerate.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<Currency, Long> {

}
