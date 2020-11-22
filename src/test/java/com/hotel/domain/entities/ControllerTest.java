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
import static com.hotel.domain.constants.MotionType.MOVEMENT;
import static com.hotel.domain.constants.MotionType.REST;
import static com.hotel.domain.constants.StateType.OFF;
import static com.hotel.domain.constants.StateType.ON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ControllerTest {
    private PowerConsumption powerConsumption, acPowerConsumption;

    private Equipment corridorLight, corridorAc;

    private List<Equipment> equipments;

    private Corridor floor1MainCorridor1, floor1SubCorridor1, floor1SubCorridor2;

    private Corridor floor2MainCorridor1, floor2SubCorridor1, floor2SubCorridor2;

    private List<Corridor> floor1CorridorsList, floor2CorridorsList;

    private Floor floor1, floor2;

    private List<Floor> floors;

    private Motion movementInFloor1SubCorridor2, noMovementInFloor1SubCorridor2;

    private List<Motion> motions;

    private Controller controller;

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

        floor2MainCorridor1 = getCorridor("1", MAIN_CORRIDOR, ON, ON);
        floor2SubCorridor1 = getCorridor("1", SUB_CORRIDOR, OFF, ON);
        floor2SubCorridor2 = getCorridor("2", SUB_CORRIDOR, OFF, ON);

        floor2CorridorsList = new LinkedList<>();
        floor2CorridorsList.add(floor2MainCorridor1);
        floor2CorridorsList.add(floor2SubCorridor1);
        floor2CorridorsList.add(floor2SubCorridor2);

        floor2 = new Floor("2", floor2CorridorsList);

        movementInFloor1SubCorridor2 = new Motion(floor1, floor1SubCorridor2, MOVEMENT);
        noMovementInFloor1SubCorridor2 = new Motion(floor1, floor1SubCorridor2, REST);

        floors = new LinkedList<>();
        floors.add(floor1);

        motions = new LinkedList<>();
        motions.add(movementInFloor1SubCorridor2);
        motions.add(noMovementInFloor1SubCorridor2);

        controller = new Controller(motions);
    }

    @AfterEach
    void deleteTestData() {
        powerConsumption = acPowerConsumption = null;
        corridorLight = corridorAc = null;
        equipments = null;
        floor1MainCorridor1 = floor1SubCorridor1 = floor1SubCorridor2 = null;
        floor2MainCorridor1 = floor2SubCorridor1 = floor2SubCorridor2 = null;
        floor1CorridorsList = floor2CorridorsList = null;
        floor1 = floor2 = null;
        floors = null;
        movementInFloor1SubCorridor2 = noMovementInFloor1SubCorridor2 = null;
        motions = null;
        controller = null;
    }

    @Test
    @DisplayName("Should Optimize PowerConsumption For Hotel With Two Floors And One Motion")
    void optimizePowerConsumptionForAllFloors_ShouldOptimizePowerConsumptionForHotelWithTwoFloorsAndOneMotion() {
        //Arrange
        movementInFloor1SubCorridor2 = new Motion(floor1, floor1SubCorridor2, MOVEMENT);
        List<Motion> anotherMotionList = new LinkedList<>();
        anotherMotionList.add(movementInFloor1SubCorridor2);

        Corridor expectedFloor1MainCorridor1 = getCorridor("1", MAIN_CORRIDOR, ON, ON);
        Corridor expectedFloor1SubCorridor1 = getCorridor("1", SUB_CORRIDOR, OFF, OFF);
        Corridor expectedFloor1SubCorridor2 = getCorridor("2", SUB_CORRIDOR, ON, ON);

        Corridor expectedFloor2MainCorridor1 = getCorridor("1", MAIN_CORRIDOR, ON, ON);
        Corridor expectedFloor2SubCorridor1 = getCorridor("1", SUB_CORRIDOR, OFF, ON);
        Corridor expectedFloor2SubCorridor2 = getCorridor("2", SUB_CORRIDOR, OFF, ON);
        floors.add(floor2);

        Controller anotherController = new Controller(anotherMotionList);

        //Action
        anotherController.optimizePowerConsumptionForAllFloors(floors);
        Floor actualFloor1 = floors.get(0);
        Floor actualFloor2 = floors.get(1);
        Corridor actualFloor1MainCorridor1 = actualFloor1.getCorridors().get(0);
        Corridor actualFloor1SubCorridor1 = actualFloor1.getCorridors().get(1);
        Corridor actualFloor1SubCorridor2 = actualFloor1.getCorridors().get(2);
        Corridor actualFloor2MainCorridor1 = actualFloor2.getCorridors().get(0);
        Corridor actualFloor2SubCorridor1 = actualFloor2.getCorridors().get(1);
        Corridor actualFloor2SubCorridor2 = actualFloor2.getCorridors().get(2);

        //Assert
        assertThat(actualFloor1MainCorridor1, is(expectedFloor1MainCorridor1));
        assertThat(actualFloor1SubCorridor1, is(expectedFloor1SubCorridor1));
        assertThat(actualFloor1SubCorridor2, is(expectedFloor1SubCorridor2));
        assertThat(actualFloor2MainCorridor1, is(expectedFloor2MainCorridor1));
        assertThat(actualFloor2SubCorridor1, is(expectedFloor2SubCorridor1));
        assertThat(actualFloor2SubCorridor2, is(expectedFloor2SubCorridor2));
    }

    @Test
    @DisplayName("Should Optimize PowerConsumption For Hotel With Two Floors And Two Motion")
    void optimizePowerConsumptionForAllFloors_ShouldOptimizePowerConsumptionForHotelWithTwoFloorsAndTwoMotions() {
        //Arrange
        Corridor expectedFloor1MainCorridor1 = getCorridor("1", MAIN_CORRIDOR, ON, ON);
        Corridor expectedFloor1SubCorridor1 = getCorridor("1", SUB_CORRIDOR, OFF, ON);
        Corridor expectedFloor1SubCorridor2 = getCorridor("2", SUB_CORRIDOR, OFF, ON);
        Corridor expectedFloor2MainCorridor1 = getCorridor("1", MAIN_CORRIDOR, ON, ON);
        Corridor expectedFloor2SubCorridor1 = getCorridor("1", SUB_CORRIDOR, OFF, ON);
        Corridor expectedFloor2SubCorridor2 = getCorridor("2", SUB_CORRIDOR, OFF, ON);
        floors.add(floor2);

        //Action
        controller.optimizePowerConsumptionForAllFloors(floors);

        Floor actualFloor1 = floors.get(0);
        Floor actualFloor2 = floors.get(1);
        Corridor actualFloor1MainCorridor1 = actualFloor1.getCorridors().get(0);
        Corridor actualFloor1SubCorridor1 = actualFloor1.getCorridors().get(1);
        Corridor actualFloor1SubCorridor2 = actualFloor1.getCorridors().get(2);
        Corridor actualFloor2MainCorridor1 = actualFloor2.getCorridors().get(0);
        Corridor actualFloor2SubCorridor1 = actualFloor2.getCorridors().get(1);
        Corridor actualFloor2SubCorridor2 = actualFloor2.getCorridors().get(2);

        //Assert
        assertThat(actualFloor1MainCorridor1, is(expectedFloor1MainCorridor1));
        assertThat(actualFloor1SubCorridor1, is(expectedFloor1SubCorridor1));
        assertThat(actualFloor1SubCorridor2, is(expectedFloor1SubCorridor2));
        assertThat(actualFloor2MainCorridor1, is(expectedFloor2MainCorridor1));
        assertThat(actualFloor2SubCorridor1, is(expectedFloor2SubCorridor1));
        assertThat(actualFloor2SubCorridor2, is(expectedFloor2SubCorridor2));
    }

    @Test
    @DisplayName("Should Optimize PowerConsumption For Hotel With One Floor And Two Motion")
    void optimizePowerConsumptionForAllFloors_ShouldOptimizePowerConsumptionForHotelWithOneFloorAndTwoMotions() {
        //Arrange
        Corridor expectedFloor1MainCorridor1 = getCorridor("1", MAIN_CORRIDOR, ON, ON);
        Corridor expectedFloor1SubCorridor1 = getCorridor("1", SUB_CORRIDOR, OFF, ON);
        Corridor expectedFloor1SubCorridor2 = getCorridor("2", SUB_CORRIDOR, OFF, ON);

        //Action
        controller.optimizePowerConsumptionForAllFloors(floors);

        //Assert
        assertThat(floors.get(0).getCorridors().get(0), is(expectedFloor1MainCorridor1));
        assertThat(floors.get(0).getCorridors().get(1), is(expectedFloor1SubCorridor1));
        assertThat(floors.get(0).getCorridors().get(2), is(expectedFloor1SubCorridor2));
    }

    private Corridor getCorridor(String corridorId, CorridorType corridorType, StateType lightStateType, StateType acStateType) {
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