package com.hotel.domain.entities;

import com.hotel.domain.constants.CorridorType;
import com.hotel.domain.constants.StateType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static com.hotel.domain.constants.Constants.AC_POWER_CONSUMPTION;
import static com.hotel.domain.constants.Constants.LIGHT_POWER_CONSUMPTION;
import static com.hotel.domain.constants.CorridorType.MAIN_CORRIDOR;
import static com.hotel.domain.constants.CorridorType.SUB_CORRIDOR;
import static com.hotel.domain.constants.EquipmentType.AIR_CONDITIONER;
import static com.hotel.domain.constants.EquipmentType.LIGHT_BULB;
import static com.hotel.domain.constants.StateType.OFF;
import static com.hotel.domain.constants.StateType.ON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class FloorTest {
    private PowerConsumption powerConsumption, acPowerConsumption;
    private Equipment corridorLight, corridorAc;
    private List<Equipment> equipments;
    private Corridor floor1MainCorridor1, floor1SubCorridor1, floor1SubCorridor2;
    private List<Corridor> floor1CorridorsList;
    private Floor floor1;

    @BeforeEach
    void setupTestData() {
        floor1MainCorridor1 = getCorridor("1", MAIN_CORRIDOR, ON, ON);
        floor1SubCorridor1 = getCorridor("1", SUB_CORRIDOR, OFF, ON);
        floor1SubCorridor2 = getCorridor("2", SUB_CORRIDOR, OFF, ON);

        floor1CorridorsList = new LinkedList<>();
        floor1CorridorsList.add(floor1MainCorridor1);
        floor1CorridorsList.add(floor1SubCorridor1);
        floor1CorridorsList.add(floor1SubCorridor2);

        floor1 = new Floor("1", floor1CorridorsList);
    }

    @AfterEach
    void deleteTestData() {
        powerConsumption = acPowerConsumption = null;
        corridorLight = corridorAc = null;
        equipments = null;
        floor1MainCorridor1 = floor1SubCorridor1 = floor1SubCorridor2 = null;
        floor1CorridorsList = null;
        floor1 = null;
    }

    @Test
    @DisplayName("Should calculate PowerConsumption for a given Floor")
    void calculateConsumptionForAFloor_ShouldCalculatePowerConsumptionForAGivenFloor() {
        //Arrange
        PowerConsumption expectedPowerConsumption = new PowerConsumption(35);

        //Action
        PowerConsumption actualPowerConsumption = floor1.calculateConsumptionForAFloor();

        //Assert
        assertThat(actualPowerConsumption, is(expectedPowerConsumption));
    }

    @Test
    @DisplayName("Should calculate count of Main-corridors for a given Floor")
    void calculateNumberOfMainCorridors_ShouldCalculatePowerConsumptionForAGivenFloor() {
        //Arrange
        Integer expectedNumberOfMainCorridors = 1;

        //Action
        Integer actualNumberOfMainCorridors = floor1.calculateNumberOfMainCorridors();

        //Assert
        assertThat(actualNumberOfMainCorridors, is(expectedNumberOfMainCorridors));
    }

    @Test
    @DisplayName("Should calculate count of Sub-corridors for a given Floor")
    void calculateNumberOfSubCorridors_ShouldCalculateNumberOfSubCorridorsForAGivenFloor() {
        //Arrange
        Integer expectedNumberOfSubCorridors = 2;

        //Action
        Integer actualNumberOfSubCorridors = floor1.calculateNumberOfSubCorridors();

        //Assert
        assertThat(actualNumberOfSubCorridors, is(expectedNumberOfSubCorridors));
    }

    private Corridor getCorridor(String corridorId, CorridorType corridorType, StateType lightStateType,
                                 StateType acStateType) {
        powerConsumption = new PowerConsumption(LIGHT_POWER_CONSUMPTION);
        acPowerConsumption = new PowerConsumption(AC_POWER_CONSUMPTION);

        corridorLight = new Equipment(LIGHT_BULB, lightStateType, powerConsumption);
        corridorAc = new Equipment(AIR_CONDITIONER, acStateType, acPowerConsumption); //AC should be in ON state for first time

        equipments = new LinkedList<>();
        equipments.add(corridorLight);
        equipments.add(corridorAc);

        return new Corridor(corridorId, equipments, corridorType);
    }
}