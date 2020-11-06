package com.hotel.domain.entities;

import java.util.Objects;

/* This class represents consumption of power in terms of value */
public class PowerConsumption {
    private final Integer value;

    public PowerConsumption(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PowerConsumption)) return false;
        PowerConsumption that = (PowerConsumption) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    Integer value() {
        return value;
    }
}