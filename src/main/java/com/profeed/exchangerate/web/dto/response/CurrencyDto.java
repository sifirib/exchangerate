package com.profeed.exchangerate.web.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class CurrencyDto {
        private LocalDateTime date;
        private String sourceWebSite;
        private String sourceCurrency;
        private String targetCurrency;
        private Double rate;
}
