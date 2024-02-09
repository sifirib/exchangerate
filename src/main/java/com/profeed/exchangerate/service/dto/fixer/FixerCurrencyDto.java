package com.profeed.exchangerate.service.dto.fixer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixerCurrencyDto {
    private boolean success;
    private long timestamp;
    private String base;
    private String date;
    private Rates rates;
/*        "success": true,
            "timestamp": 1519296206,
            "base": "USD",
            "date": "2024-02-08",
            "rates": {
        "GBP": 0.72007,
                "JPY": 107.346001,
                "EUR": 0.813399,*/
}
