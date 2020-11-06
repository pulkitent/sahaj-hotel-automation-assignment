package com.hotel.domain.entities;

import com.hotel.domain.constants.EquipmentType;
import com.hotel.domain.constants.StateType;

import java.util.Objects;

import static com.hotel.domain.constants.StateType.OFF;
import static com.hotel.domain.constants.StateType.ON;

/* This class represents an equipment with type as AC/Bulb, with current state as On/Off
and with it's power consumption */
public class Equipment {
    private final EquipmentType type;
    private StateType state;
    private final PowerConsumption powerConsumption;

    public Equipment(EquipmentType type, StateType state, PowerConsumption powerConsumption) {
        this.type = type;
        this.state = state;
        this.powerConsumption = powerConsumption;
    }

    @Override
    public String toString() {
        return type + " : " + state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment equipment = (Equipment) o;
        return type == equipment.type &&
                state == equipment.state &&
                getPowerConsumption().equals(equipment.getPowerConsumption());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, state, getPowerConsumption());
    }

    PowerConsumption getPowerConsumption() {
        return this.powerConsumption;
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
}