package com.hotel.domain.entities;

import com.hotel.domain.constants.CorridorType;
import com.hotel.domain.constants.EquipmentType;
import com.hotel.domain.constants.MotionType;

import java.util.List;

import static com.hotel.domain.constants.Constants.*;
import static com.hotel.domain.constants.CorridorType.SUB_CORRIDOR;
import static com.hotel.domain.constants.EquipmentType.AIR_CONDITIONER;
import static com.hotel.domain.constants.EquipmentType.LIGHT_BULB;

/* This class represents a controller to control and optimize the
power consumption in the hotel with motions as input from sensor */
public class Controller {
    private final List<Motion> motions;

    public Controller(List<Motion> motions) {
        this.motions = motions;
    }

    void optimizePowerConsumptionForAllFloors(List<Floor> floors) {
        printOnConsole(INITIAL_STATE_OF_ALL_EQUIPMENTS, floors);
        for (Motion motion : motions) {
            MotionType motionType = motion.getType();
            Floor floorOnWhichMotionOccurred = motion.getFloor();
            Corridor corridorInWhichMotionOccurred = motion.getCorridor();
            switch (motionType) {
                case MOVEMENT:
                    corridorInWhichMotionOccurred.switchOnGivenEquipmentForGivenCorridor(LIGHT_BULB, SUB_CORRIDOR);
                    switchOffGivenEquipmentForGivenCorridorIfLimitExceeds(floorOnWhichMotionOccurred, AIR_CONDITIONER,
                            SUB_CORRIDOR);

                    printOnConsole(EQUIPMENTS_STATE_AFTER_MOVEMENT, floors);
                    break;
                case REST:
                    corridorInWhichMotionOccurred.switchOffGivenEquipmentForGivenCorridor(LIGHT_BULB, SUB_CORRIDOR);
                    switchOnGivenEquipmentForGivenCorridorIfLimitNotExceeds(floorOnWhichMotionOccurred, AIR_CONDITIONER,
                            SUB_CORRIDOR);

                    printOnConsole(EQUIPMENTS_STATE_AFTER_REST, floors);
                    break;
                default:
                    // Do nothing for now
                    break;
            }
        }
    }

    private void printOnConsole(String message, List<Floor> floors) {
        System.out.println(message);
        System.out.println(floors);
    }

    private void switchOnGivenEquipmentForGivenCorridorIfLimitNotExceeds(Floor floor, EquipmentType equipmentType,
                                                                         CorridorType corridorType) {
        List<Corridor> corridors = floor.getCorridors();
        for (Corridor corridor : corridors) {
            /* check if this corridor is a sub-corridor */
            if (corridor.isCorridorTypeEqualsToGivenType(corridorType)) {
                corridor.switchOnAGivenEquipmentOfType(equipmentType);
                /* check power consumption after switching on the AC */
                if (!isConsumptionExceedingPowerLimit(floor)) {
                        /* if power consumption after switching on
                        is exceeding then break the for loop */
                    break;
                }
            }
        }
    }

    private void switchOffGivenEquipmentForGivenCorridorIfLimitExceeds(Floor floor, EquipmentType equipmentType,
                                                                       CorridorType corridorType) {
        List<Corridor> corridors = floor.getCorridors();
        for (Corridor corridor : corridors) {
            /* check if this corridor is a sub-corridor */
            if (corridor.isCorridorTypeEqualsToGivenType(corridorType)) {
                corridor.switchOffAGivenEquipmentOfType(equipmentType);
                /* check power consumption after switching off the AC */
                if (!isConsumptionExceedingPowerLimit(floor)) {
                        /* if power consumption after switching off
                        is not exceeding then break the for loop */
                    break;
                }
            }
        }
    }

    private boolean isConsumptionExceedingPowerLimit(Floor floor) {
        PowerConsumption totalPowerPowerConsumptionOfAllFloors = floor.calculateConsumptionForAFloor();

        /* maximumAllowedPowerConsumption can be cached
        as part of optimization if it's same for all floors */
        PowerConsumption maximumAllowedPowerPowerConsumption = calculateAllowedConsumptionForAFloor(floor);

        return totalPowerPowerConsumptionOfAllFloors.value() > maximumAllowedPowerPowerConsumption.value();
    }

    private PowerConsumption calculateAllowedConsumptionForAFloor(Floor floor) {
        int allowedPower = 0;

        Integer numberOfMainCorridorsOnAFloor = floor.calculateNumberOfMainCorridors();
        Integer numberOfSubCorridorsOnAFloor = floor.calculateNumberOfSubCorridors();

        allowedPower = ((numberOfMainCorridorsOnAFloor * MAIN_CORRIDOR_RATE)
                + (numberOfSubCorridorsOnAFloor * SUB_CORRIDOR_RATE));

        return new PowerConsumption(allowedPower);
    }
}