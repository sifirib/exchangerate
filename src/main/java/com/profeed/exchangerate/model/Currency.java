package com.profeed.exchangerate.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Map;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "exchange_rates")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean success;

    @Column(nullable = false)
    private Long timestamp;

    @Column(nullable = false)
    private String base;

    @Column(nullable = false)
    private LocalDate date;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "currency_rates", joinColumns = @JoinColumn(name = "exchange_rate_id"))
    @MapKeyColumn(name = "currency")
    @Column(name = "rate")
    private Map<String, Double> rates;
}