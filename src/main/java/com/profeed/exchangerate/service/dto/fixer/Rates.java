package com.profeed.exchangerate.service.dto.fixer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rates {
    @JsonProperty("TRY")
    private Double TRY;
}
