package com.hotel.domain.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.hotel.domain.constants.EquipmentType.AIR_CONDITIONER;
import static com.hotel.domain.constants.StateType.ON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class EquipmentTest {
    private Equipment equipment;

    private PowerConsumption powerConsumption;

    @BeforeEach
    void setupTestData() {
        powerConsumption = new PowerConsumption(10);
        equipment = new Equipment(AIR_CONDITIONER, ON, powerConsumption);
    }

    @AfterEach
    void deleteTestData() {
        powerConsumption = null;
        equipment = null;
    }

    @Test
    @DisplayName("Should turn on the equipment")
    void turnOn_ShouldTurnOnEquipment() {
        //Action
        equipment.turnOn();

        //Assert
        assertThat(equipment.isEquipmentOn(), is(true));

    }

    @Test
    @DisplayName("Should turn off the equipment")
    void turnOff_ShouldTurnOffEquipment() {
        //Action
        equipment.turnOff();

        //Assert
        assertThat(equipment.isEquipmentOn(), is(false));

    }

    @Test
    @DisplayName("Should turn off the equipment")
    void isEquipmentOn_ShouldCheckIfEquipmentIsOn() {
        //Assert
        assertThat(equipment.isEquipmentOn(), is(true));
    }

    @Test
    @DisplayName("Should turn off the equipment")
    void isEquipmentTypeEqualsGivenType_ShouldCheckIfEquipmentIsOfGivenType() {
        //Assert
        assertThat(equipment.isEquipmentTypeEqualsGivenType(AIR_CONDITIONER), is(true));
    }
}