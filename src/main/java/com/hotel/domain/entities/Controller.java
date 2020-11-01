package com.hotel.domain.entities;

import com.hotel.domain.constants.CorridorType;
import com.hotel.domain.constants.EquipmentType;
import com.hotel.domain.constants.MotionType;

import java.util.List;

import static com.hotel.domain.constants.Constants.MAIN_CORRIDOR_RATE;
import static com.hotel.domain.constants.Constants.SUB_CORRIDOR_RATE;
import static com.hotel.domain.constants.CorridorType.SUB_CORRIDOR;
import static com.hotel.domain.constants.EquipmentType.AIR_CONDITIONER;
import static com.hotel.domain.constants.EquipmentType.LIGHT_BULB;

public class Controller {
    private final List<Motion> motions;

    public Controller(List<Motion> motions) {
        this.motions = motions;
    }

    void optimizePowerConsumptionForAllFloors(List<Floor> floors) {
        printOnConsole("Initial state", floors);

        for (Motion motion : motions) {
            MotionType motionType = motion.getType();
            Corridor corridor = motion.getCorridor();
            switch (motionType) {
                case MOVEMENT:
                    corridor.switchOnGivenEquipmentForGivenCorridor(LIGHT_BULB, SUB_CORRIDOR);
                    closeGivenEquipmentForGivenCorridorIfLimitExceeds(floors, AIR_CONDITIONER, SUB_CORRIDOR);
                    printOnConsole("Printing UNREST state", floors);
                    break;
                case REST:
                    corridor.switchOffGivenEquipmentForGivenCorridor(LIGHT_BULB, SUB_CORRIDOR);
                    corridor.switchOnGivenEquipmentForGivenCorridor(AIR_CONDITIONER, SUB_CORRIDOR);
                    printOnConsole("Printing REST state", floors);
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

    private void closeGivenEquipmentForGivenCorridorIfLimitExceeds(List<Floor> floors, EquipmentType equipmentType,
                                                                   CorridorType corridorType) {
        int floorNumber = 0;
        boolean shouldExitWhileLoop = false;

        while (isConsumptionExceedingPowerLimit(floors)) {
            Floor floor = floors.get(floorNumber);
            List<Corridor> corridors = floor.getCorridors();

            for (Corridor corridor : corridors) {
                /* check if this corridor is a sub-corridor */
                if (corridor.isCorridorTypeEqualsToGivenType(corridorType)) {
                    corridor.switchOffAGivenEquipmentOfType(equipmentType);
                    /* check power consumption after switching off the AC */
                    if (!isConsumptionExceedingPowerLimit(floors)) {
                        /* if power consumption after switching off
                        is not exceeding then break the for loop */
                        shouldExitWhileLoop = true;
                        break;
                    }
                }
            }

             /* After breaking for loop break the while loop as power
             consumption after switching off is not exceeding */
            if (shouldExitWhileLoop) {
                break;
            }

            floorNumber++;
        }
    }

    private boolean isConsumptionExceedingPowerLimit(List<Floor> floors) {
        Consumption totalPowerConsumptionOfAllFloors = calculateConsumptionOfAllFloors(floors);

        /* maximumAllowedPowerConsumption Can be cached as part of optimization */
        Consumption maximumAllowedPowerConsumption = calculateAllowedConsumptionForAllFloors(floors);

        return totalPowerConsumptionOfAllFloors.value() > maximumAllowedPowerConsumption.value();
    }

    private Consumption calculateConsumptionOfAllFloors(List<Floor> floors) {
        Integer totalPowerConsumptionValueForHotel = 0;

        for (Floor floor : floors) {
            Consumption powerConsumption = floor.calculateConsumptionForAFloor();
            totalPowerConsumptionValueForHotel += powerConsumption.value();
        }

        return new Consumption(totalPowerConsumptionValueForHotel);
    }

    private Consumption calculateAllowedConsumptionForAllFloors(List<Floor> floors) {
        int allowedPower = 0;
        Floor firstFloor = floors.get(0);

        Integer numberOfMainCorridorsOnSingleFloor = firstFloor.calculateNumberOfMainCorridors();
        Integer numberOfSubCorridorsOnSingleFloor = firstFloor.calculateNumberOfSubCorridors();

        Integer totalNumberOfMainCorridors = numberOfMainCorridorsOnSingleFloor * floors.size();
        Integer totalNumberOfSubCorridors = numberOfSubCorridorsOnSingleFloor * floors.size();

        allowedPower = totalNumberOfMainCorridors * MAIN_CORRIDOR_RATE
                + totalNumberOfSubCorridors * SUB_CORRIDOR_RATE;

        return new Consumption(allowedPower);
    }
}