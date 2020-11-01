package com.hotel.domain.entities;

import com.hotel.domain.constants.EquipmentType;
import com.hotel.domain.constants.State;

import static com.hotel.domain.constants.State.OFF;
import static com.hotel.domain.constants.State.ON;

/* This class represents an equipment with type as AC/Bulb, with current state as On/Off
and with it's power consumption */
public class Equipment {
    private final EquipmentType type;
    private State state;
    private final PowerConsumption powerConsumption;

    public Equipment(EquipmentType type, State state, PowerConsumption powerConsumption) {
        this.type = type;
        this.state = state;
        this.powerConsumption = powerConsumption;
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

    public PowerConsumption getPowerConsumption() {
        return this.powerConsumption;
    }

    @Override
    public String toString() {
        return type + " : " + state;
    }
}