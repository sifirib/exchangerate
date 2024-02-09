package com.profeed.exchangerate.service.dto.currenylayer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencylayerDto {

    private boolean success;
    private long timestamp;
    private String source;
    private Quotes quotes;

/*        "success": true,
                "terms": "https://currencylayer.com/terms",
                "privacy": "https://currencylayer.com/privacy",
                "timestamp": 1432400348,
                "source": "USD",
                "quotes": {
        "USDAUD": 1.278342,
                "USDEUR": 1.278342,
                "USDGBP": 0.908019,
                "USDPLN": 3.731504*/

}
