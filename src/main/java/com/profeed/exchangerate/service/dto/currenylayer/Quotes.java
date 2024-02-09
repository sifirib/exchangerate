package com.profeed.exchangerate.service.dto.currenylayer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quotes {
    @JsonProperty("USDTRY")
    private Double USDTRY;
}
