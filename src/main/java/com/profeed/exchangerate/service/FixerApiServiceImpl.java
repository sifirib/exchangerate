package com.profeed.exchangerate.service;

import com.profeed.exchangerate.model.FixerCurrency;
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
    public FixerCurrency getEuro() {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<FixerCurrency> response = restTemplate.exchange(fixerApi, HttpMethod.GET, entity, FixerCurrency.class);

        return response.getBody();
    }
}
