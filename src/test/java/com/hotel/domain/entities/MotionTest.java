package com.hotel.domain.entities;

import com.hotel.domain.constants.MotionType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.hotel.domain.constants.CorridorType.MAIN_CORRIDOR;
import static com.hotel.domain.constants.CorridorType.SUB_CORRIDOR;
import static com.hotel.domain.constants.EquipmentType.AIR_CONDITIONER;
import static com.hotel.domain.constants.MotionType.MOVEMENT;
import static com.hotel.domain.constants.StateType.ON;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MotionTest {
    private Floor floor;

    private Corridor subCorridor;

    private Corridor mainCorridor;

    private Equipment equipment;

    private Motion motion;

    private PowerConsumption powerConsumption;

    @BeforeEach
    void setupTestData() {
        powerConsumption = new PowerConsumption(10);
        equipment = new Equipment(AIR_CONDITIONER, ON, powerConsumption);
        subCorridor = new Corridor("1", singletonList(equipment), SUB_CORRIDOR);
        mainCorridor = new Corridor("1", singletonList(equipment), MAIN_CORRIDOR);
        floor = new Floor("1", asList(subCorridor, mainCorridor));
        motion = new Motion(floor, subCorridor, MOVEMENT);
    }

    @AfterEach
    void deleteTestData() {
        powerConsumption = null;
        equipment = null;
        subCorridor = null;
        mainCorridor = null;
        floor = null;
        motion = null;
    }

    @Test
    @DisplayName("Should return correct corridor value")
    void getCorridor_ShouldGiveCorrectCorridor() {
        //Action
        Corridor actualCorridor = motion.getCorridor();

        //Assert
        assertThat(actualCorridor, is(subCorridor));
    }

    @Test
    @DisplayName("Should return correct motion type")
    void getType_ShouldGiveCorrectMotionType() {
        //Action
        MotionType actualMotionType = motion.getType();

        //Assert
        assertThat(actualMotionType, is(MOVEMENT));
    }

    @Test
    @DisplayName("Should return correct floor")
    void getFloor_ShouldGiveCorrectFloor() {
        //Action
        Floor actualFloor = motion.getFloor();

        //Assert
        assertThat(actualFloor, is(floor));
    }
}