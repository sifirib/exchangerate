package com.profeed.exchangerate.service;

import com.profeed.exchangerate.model.Currency;
import org.hibernate.engine.spi.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FixerApiServiceImpl implements FixerApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    @Value("${api.fixer.euro_api}")
    private String fixerApi;

    @Override
    public Currency getEuro() {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Currency> response = restTemplate.exchange(fixerApi, HttpMethod.GET, entity, Currency.class);

        return response.getBody();
    }
}
