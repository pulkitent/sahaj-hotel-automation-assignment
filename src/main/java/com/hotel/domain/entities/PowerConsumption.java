package com.hotel.domain.entities;

/* This class represents consumption of power in terms of value */
public class PowerConsumption {
    private final Integer value;

    public PowerConsumption(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }
}