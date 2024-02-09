package com.profeed.exchangerate.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Data
public class Currency {
    private boolean success;
    private long timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;
}


