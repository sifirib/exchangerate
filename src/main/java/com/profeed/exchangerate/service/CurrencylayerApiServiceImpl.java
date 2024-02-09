package com.profeed.exchangerate.service;

import com.profeed.exchangerate.model.CurrencylayerCurrency;
import com.profeed.exchangerate.model.FixerCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CurrencylayerApiServiceImpl implements CurrencylayerApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    @Value("${api.currencylayer.dollar_api}")
    private String currencylayerApi;

    @Override
    public CurrencylayerCurrency getDollar() {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<CurrencylayerCurrency> response = restTemplate.exchange(currencylayerApi, HttpMethod.GET, entity, CurrencylayerCurrency.class);

        return response.getBody();
    }
}
