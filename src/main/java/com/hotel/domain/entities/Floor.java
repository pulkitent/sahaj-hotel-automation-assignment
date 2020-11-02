package com.hotel.domain.entities;

import java.util.List;

import static com.hotel.domain.constants.CorridorType.SUB_CORRIDOR;

/* This class represents a floor with main & sub corridors */
public class Floor {
    private final List<Corridor> corridors;

    public Floor(List<Corridor> mainCorridors) {
        this.corridors = mainCorridors;
    }

    public List<Corridor> getCorridors() {
        return corridors;
    }

    @Override
    public String toString() {
        return "Floor" + corridors;
    }

    PowerConsumption calculateConsumptionForAFloor() {
        Integer totalPowerConsumptionValueForAFloor = 0;

        for (Corridor corridor : corridors) {
            PowerConsumption powerConsumption = corridor.calculateConsumptionForACorridor();
            totalPowerConsumptionValueForAFloor += powerConsumption.value();
        }

        return new PowerConsumption(totalPowerConsumptionValueForAFloor);
    }

    Integer calculateNumberOfMainCorridors() {
        /* reuse calculateNumberOfSubCorridors to avoid code duplication */
        Integer numberOfSubCorridors = calculateNumberOfSubCorridors();
        return corridors.size() - numberOfSubCorridors;
    }

    Integer calculateNumberOfSubCorridors() {
        Integer count = 0;
        for (Corridor corridor : corridors) {
            if (corridor.isCorridorTypeEqualsToGivenType(SUB_CORRIDOR)) {
                count++;
            }
        }
        return count;
    }
}