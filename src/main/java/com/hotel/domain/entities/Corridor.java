package com.hotel.domain.entities;

import com.hotel.domain.constants.CorridorType;
import com.hotel.domain.constants.EquipmentType;
import com.hotel.domain.constants.StateType;

import java.util.List;
import java.util.Objects;

import static com.hotel.domain.constants.StateType.ON;

/* This class represents a corridor with type as main/sub and a list of equipments (AC + Bulb) */
public class Corridor {
    private final String corridorId;
    private final List<Equipment> equipments;
    private final CorridorType corridorType;

    public Corridor(String corridorId, List<Equipment> equipments, CorridorType corridorType) {
        this.corridorId = corridorId;
        this.equipments = equipments;
        this.corridorType = corridorType;
    }

    @Override
    public String toString() {
        return "\n" + corridorType + " " + corridorId +" : "+ equipments + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Corridor)) return false;
        Corridor corridor = (Corridor) o;
        return equipments.equals(corridor.equipments) &&
                corridorType == corridor.corridorType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipments, corridorType);
    }

    boolean isCorridorTypeEqualsToGivenType(CorridorType corridorType) {
        return this.corridorType.equals(corridorType);
    }

    void changeStateAllEquipmentTypeForAGivenCorridorType(EquipmentType equipmentType, CorridorType corridorType,
                                                          StateType finalState) {
        for (Equipment equipment : equipments) {
            if (isCorridorTypeAndEquipmentTypeMatching(corridorType, equipmentType, equipment)) {
                changeStateOfEquipment(finalState, equipment);
            }
        }
    }

    void changeStateOfAGivenEquipmentOfType(EquipmentType equipmentType, StateType finalState) {
        for (Equipment equipment : equipments) {
            if (equipment.isEquipmentTypeEqualsGivenType(equipmentType)) {
                changeStateOfEquipment(finalState, equipment);
                break;
            }
        }
    }

    PowerConsumption calculateConsumptionForACorridor() {
        Integer totalPowerConsumptionValueForCorridor = 0;

        for (Equipment equipment : equipments) {
            if (equipment.isEquipmentOn()) {
                PowerConsumption powerConsumption = equipment.getPowerConsumption();
                totalPowerConsumptionValueForCorridor += powerConsumption.value();
            }
        }

        return new PowerConsumption(totalPowerConsumptionValueForCorridor);
    }

    private void changeStateOfEquipment(StateType finalState, Equipment equipment) {
        if (finalState.equals(ON)) {
            equipment.turnOn();
        } else {
            equipment.turnOff();
        }
    }

    private boolean isCorridorTypeAndEquipmentTypeMatching(CorridorType corridorType, EquipmentType equipmentType,
                                                           Equipment equipment) {
        return this.corridorType.equals(corridorType)
                && equipment.isEquipmentTypeEqualsGivenType(equipmentType);
    }
}