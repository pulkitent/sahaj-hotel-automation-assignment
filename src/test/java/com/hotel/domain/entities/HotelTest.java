package com.hotel.domain.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static com.hotel.domain.constants.CorridorType.MAIN_CORRIDOR;
import static com.hotel.domain.constants.CorridorType.SUB_CORRIDOR;
import static com.hotel.domain.constants.EquipmentType.AIR_CONDITIONER;
import static com.hotel.domain.constants.StateType.ON;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

class HotelTest {
    private Hotel hotel;

    private Floor floor;

    private Corridor subCorridor;

    private Corridor mainCorridor;

    private Equipment equipment;

    private PowerConsumption powerConsumption;

    private Controller mockController;

    @BeforeEach
    void setupTestData() {
        powerConsumption = new PowerConsumption(10);
        equipment = new Equipment(AIR_CONDITIONER, ON, powerConsumption);
        subCorridor = new Corridor(singletonList(equipment), SUB_CORRIDOR);
        mainCorridor = new Corridor(singletonList(equipment), MAIN_CORRIDOR);
        floor = new Floor(asList(subCorridor, mainCorridor));
        mockController = mock(Controller.class);
        hotel = new Hotel(singletonList(floor), mockController);
    }

    @AfterEach
    void deleteTestData() {
        powerConsumption = null;
        equipment = null;
        subCorridor = null;
        mainCorridor = null;
        floor = null;
        hotel = null;
    }

    @Test
    @DisplayName("Should test startController and verify arguments of optimizePowerConsumptionForAllFloors")
    void getFloor_ShouldGiveCorrectFloor() {
        //Arrange
        ArgumentCaptor<List<Floor>> controllerArgumentCaptor = ArgumentCaptor.forClass(List.class);
        doNothing().when(mockController).optimizePowerConsumptionForAllFloors(anyList());
        List<Floor> floors = singletonList(floor);

        //Action
        hotel.startController();
        verify(mockController).optimizePowerConsumptionForAllFloors(controllerArgumentCaptor.capture());

        //Assert
        assertThat(controllerArgumentCaptor.getValue(), is(floors));
    }
}