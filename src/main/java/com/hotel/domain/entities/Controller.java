package com.hotel.domain.entities;

import com.hotel.domain.constants.CorridorType;
import com.hotel.domain.constants.EquipmentType;
import com.hotel.domain.constants.MotionType;

import java.util.List;

import static com.hotel.domain.constants.Constants.*;
import static com.hotel.domain.constants.CorridorType.SUB_CORRIDOR;
import static com.hotel.domain.constants.EquipmentType.AIR_CONDITIONER;
import static com.hotel.domain.constants.EquipmentType.LIGHT_BULB;
import static com.hotel.domain.constants.StateType.OFF;
import static com.hotel.domain.constants.StateType.ON;

/* This class represents a controller to control and optimize the
power consumption in the hotel with motions as input from sensor */
public class Controller {
    private final List<Motion> motions;

    private static final Boolean SHOULD_LOG = true;

    public Controller(List<Motion> motions) {
        this.motions = motions;
    }

    void optimizePowerConsumptionForAllFloors(List<Floor> floors) {
        printOnConsole(INITIAL_STATE_OF_ALL_EQUIPMENTS, floors, SHOULD_LOG);
        for (Motion motion : motions) {
            /* Encapsulation breaking can be further reduced
            by moving switch block into Motion class */
            MotionType motionType = motion.getType();
            Floor floorOnWhichMotionOccurred = motion.getFloor();
            Corridor corridorInWhichMotionOccurred = motion.getCorridor();
            switch (motionType) {
                case MOVEMENT:
                    corridorInWhichMotionOccurred.changeStateAllEquipmentTypeForAGivenCorridorType(LIGHT_BULB,
                            SUB_CORRIDOR, ON);
                    /* If power limit exceeds */
                    changeStateOfGivenEquipmentForGivenCorridor(floorOnWhichMotionOccurred, AIR_CONDITIONER,
                            SUB_CORRIDOR, false);

                    printOnConsole(EQUIPMENTS_STATE_AFTER_MOVEMENT, floors, SHOULD_LOG);
                    break;
                case REST:
                    corridorInWhichMotionOccurred.changeStateAllEquipmentTypeForAGivenCorridorType(LIGHT_BULB,
                            SUB_CORRIDOR, OFF);
                    /* If power limit doesn't exceed */
                    changeStateOfGivenEquipmentForGivenCorridor(floorOnWhichMotionOccurred, AIR_CONDITIONER,
                            SUB_CORRIDOR, true);

                    printOnConsole(EQUIPMENTS_STATE_AFTER_REST, floors, SHOULD_LOG);
                    break;
                default:
                    // Do nothing for now
                    break;
            }
        }
    }

    private void changeStateOfGivenEquipmentForGivenCorridor(Floor floor, EquipmentType equipmentType,
                                                             CorridorType corridorType, boolean isSwitchOnScenario) {
        List<Corridor> corridors = floor.getCorridors();
        for (Corridor corridor : corridors) {
            /* check if this corridor is a sub-corridor */
            if (corridor.isCorridorTypeEqualsToGivenType(corridorType)) {
                /* Check is it equipment on scenario or off scenario */
                if (isSwitchOnScenario) {
                    /* switch on the equipment */
                    corridor.changeStateOfAGivenEquipmentOfType(equipmentType, ON);
                    /* check power consumption after switching on the AC */
                    if (isConsumptionExceedingPowerLimit(floor)) {
                        /* if power consumption after switching on
                        is exceeding the limit then break the for loop */
                        break;
                    }
                } else {
                    /* switch off the equipment */
                    corridor.changeStateOfAGivenEquipmentOfType(equipmentType, OFF);
                    /* check power consumption after switching off the AC */
                    if (!isConsumptionExceedingPowerLimit(floor)) {
                        /* if power consumption after switching off
                        is not exceeding the limit then break the for loop */
                        break;
                    }
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

    private void printOnConsole(String message, List<Floor> floors, boolean shouldLog) {
        if (shouldLog) {
            System.out.println(message);
            System.out.println(floors);
        }
    }
}