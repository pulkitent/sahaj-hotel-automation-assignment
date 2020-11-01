package com.hotel.domain.entities;

import com.hotel.domain.constants.CorridorType;
import com.hotel.domain.constants.EquipmentType;

import java.util.List;

public class Corridor {
    private final List<Equipment> equipments;
    private final CorridorType corridorType;

    public Corridor(List<Equipment> equipments, CorridorType corridorType) {
        this.equipments = equipments;
        this.corridorType = corridorType;
    }

    @Override
    public String toString() {
        return corridorType + " " + equipments;
    }

    boolean isCorridorTypeEqualsToGivenType(CorridorType corridorType) {
        return this.corridorType.equals(corridorType);
    }

    void switchOnGivenEquipmentForGivenCorridor(EquipmentType equipmentType, CorridorType corridorType) {
        for (Equipment equipment : equipments) {
            if (isCorridorTypeAndEquipmentTypeMatching(corridorType, equipmentType, equipment)) {
                equipment.turnOn();
            }
        }
    }

    void switchOffGivenEquipmentForGivenCorridor(EquipmentType equipmentType, CorridorType corridorType) {
        for (Equipment equipment : equipments) {
            if (isCorridorTypeAndEquipmentTypeMatching(corridorType, equipmentType, equipment)) {
                equipment.turnOff();
            }
        }
    }

    void switchOffAGivenEquipmentOfType(EquipmentType equipmentType) {
        for (Equipment equipment : equipments) {
            if (equipment.isEquipmentTypeEqualsGivenType(equipmentType)) {
                equipment.turnOff();
                break;
            }
        }
    }

    Consumption calculateConsumptionForACorridor() {
        Integer totalPowerConsumptionValueForCorridor = 0;

        for (Equipment equipment : equipments) {
            if (equipment.isEquipmentOn()) {
                Consumption powerConsumption = equipment.getPowerConsumption();
                totalPowerConsumptionValueForCorridor += powerConsumption.value();
            }
        }

        return new Consumption(totalPowerConsumptionValueForCorridor);
    }

    private boolean isCorridorTypeAndEquipmentTypeMatching(CorridorType corridorType, EquipmentType equipmentType,
                                                           Equipment equipment) {
        return this.corridorType.equals(corridorType) && equipment.isEquipmentTypeEqualsGivenType(equipmentType);
    }
}