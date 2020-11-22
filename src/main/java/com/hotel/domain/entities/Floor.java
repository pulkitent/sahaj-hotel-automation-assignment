package com.hotel.domain.entities;

import java.util.List;
import java.util.Objects;

import static com.hotel.domain.constants.CorridorType.SUB_CORRIDOR;

/* This class represents a floor with main & sub corridors */
public class Floor {
    private String floorId;
    private final List<Corridor> corridors;

    public Floor(String floorId, List<Corridor> mainCorridors) {
        this.floorId = floorId;
        this.corridors = mainCorridors;
    }

    @Override
    public String toString() {
        return "\n\n" + "Floor " + floorId + "\n" + corridors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Floor)) return false;
        Floor floor = (Floor) o;
        return getCorridors().equals(floor.getCorridors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCorridors());
    }

    List<Corridor> getCorridors() {
        return corridors;
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