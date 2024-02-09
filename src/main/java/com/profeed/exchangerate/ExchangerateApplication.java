package com.profeed.exchangerate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;



@SpringBootApplication
public class ExchangerateApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public HttpHeaders httpHeaders() {
		return new HttpHeaders();
	}

	public static void main(String[] args) {
		SpringApplication.run(ExchangerateApplication.class, args);
	}

}
