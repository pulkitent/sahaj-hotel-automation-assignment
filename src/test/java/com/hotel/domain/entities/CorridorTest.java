package com.hotel.domain.entities;

import com.hotel.domain.constants.CorridorType;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CorridorTest {
    private CorridorType corridorType;

    private PowerConsumption lightPowerConsumption, anotherLightPowerConsumption, acPowerConsumption;

    private Equipment corridorLight, anotherCorridorLight, corridorAc;

    private List<Equipment> equipments;

    private Corridor corridor;

    @BeforeEach
    void setupTestData() {
        corridorType = MAIN_CORRIDOR;

        lightPowerConsumption = new PowerConsumption(LIGHT_POWER_CONSUMPTION);
        anotherLightPowerConsumption = new PowerConsumption(LIGHT_POWER_CONSUMPTION);
        acPowerConsumption = new PowerConsumption(AC_POWER_CONSUMPTION);

        corridorLight = new Equipment(LIGHT_BULB, ON, lightPowerConsumption);
        anotherCorridorLight = new Equipment(LIGHT_BULB, ON, anotherLightPowerConsumption);
        corridorAc = new Equipment(AIR_CONDITIONER, ON, acPowerConsumption); //AC should be in ON state for first time

        equipments = new LinkedList<>();
        equipments.add(corridorLight);
        equipments.add(anotherCorridorLight);
        equipments.add(corridorAc);

        corridor = new Corridor("1", equipments, corridorType);
    }

    @AfterEach
    void deleteTestData() {
        corridorType = null;
        lightPowerConsumption = null;
        acPowerConsumption = null;
        corridorLight = null;
        corridorAc = null;
        equipments = null;
        corridor = null;
    }

    @Test
    @DisplayName("Should test isCorridorTypeEqualsToGivenType and Check Equality Of CorridorType")
    void isCorridorTypeEqualsToGivenType_ShouldCheckEqualityOfCorridorType() {
        //Action
        boolean result = corridor.isCorridorTypeEqualsToGivenType(MAIN_CORRIDOR);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should test isCorridorTypeEqualsToGivenType and Check InEquality Of CorridorType")
    void isCorridorTypeEqualsToGivenType_ShouldCheckInEqualityOfCorridorType() {
        //Action
        boolean result = corridor.isCorridorTypeEqualsToGivenType(SUB_CORRIDOR);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should test changeStateAllEquipmentTypeForAGivenCorridorType and turn off all equipments of given type")
    void changeStateAllEquipmentTypeForAGivenCorridorType_ShouldTurnOFFAllEquipmentOfGivenType() {
        //Action
        corridor.changeStateAllEquipmentTypeForAGivenCorridorType(LIGHT_BULB, MAIN_CORRIDOR, OFF);

        //Assert
        assertFalse(equipments.get(0).isEquipmentOn());
        assertFalse(equipments.get(1).isEquipmentOn());
        assertTrue(equipments.get(2).isEquipmentOn());
    }

    @Test
    @DisplayName("Should test changeStateOfAGivenEquipmentOfType and turn off first equipment of given type")
    void changeStateOfAGivenEquipmentOfType_ShouldTurnOFFAEquipmentOfGivenType() {
        //Action
        corridor.changeStateOfAGivenEquipmentOfType(LIGHT_BULB, OFF);

        //Assert
        assertFalse(equipments.get(0).isEquipmentOn());
        assertTrue(equipments.get(1).isEquipmentOn());
        assertTrue(equipments.get(2).isEquipmentOn());
    }

    @Test
    @DisplayName("Should test calculateConsumptionForACorridor and calculate power of consumption for a corridor")
    void calculateConsumptionForACorridor_ShouldCalculatePowerConsumptionOfCorridor() {
        //Arrange
        PowerConsumption expectedPowerConsumption = new PowerConsumption(20);

        //Action
        PowerConsumption actualPowerConsumption = corridor.calculateConsumptionForACorridor();

        ///Assert
        assertThat(actualPowerConsumption, is(expectedPowerConsumption));
    }
}