package com.profeed.exchangerate.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "exchange_rates")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    //hangi siteden gelecğei
    @Column(nullable = false)
    private String source;

    @Column(name = "sourceCurreny")
    @Enumerated(EnumType.STRING)
    private CurrencyEnum sourceCurrency;   //USD

    @Column(name = "targetCurrency")
    @Enumerated(EnumType.STRING)
    private CurrencyEnum targetCurrency;  //TL

    @Column(name = "rate")
    private Double rate;            //33
}