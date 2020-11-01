package com.hotel.domain.entities;

import com.hotel.domain.constants.EquipmentType;
import com.hotel.domain.constants.State;

import static com.hotel.domain.constants.State.OFF;
import static com.hotel.domain.constants.State.ON;

public class Equipment {
    private final EquipmentType type;
    private State state;
    private final Consumption consumption;

    public Equipment(EquipmentType type, State state, Consumption consumption) {
        this.type = type;
        this.state = state;
        this.consumption = consumption;
    }

    void turnOn() {
        this.state = ON;
    }

    void turnOff() {
        this.state = OFF;
    }

    boolean isEquipmentOn() {
        return this.state.equals(ON);
    }

    boolean isEquipmentTypeEqualsGivenType(EquipmentType equipmentType) {
        return this.type.equals(equipmentType);
    }

    public Consumption getPowerConsumption() {
        return this.consumption;
    }

    @Override
    public String toString() {
        return type + " : " + state;
    }
}