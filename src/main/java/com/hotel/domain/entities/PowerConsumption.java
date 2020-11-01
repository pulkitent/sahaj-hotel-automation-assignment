package com.hotel.domain.entities;

public class PowerConsumption {
    private final Integer value;

    public PowerConsumption(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }
}