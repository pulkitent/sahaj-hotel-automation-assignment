import com.hotel.domain.constants.StateType;
import com.hotel.domain.entities.*;

import java.util.LinkedList;
import java.util.List;

import static com.hotel.domain.constants.Constants.AC_POWER_CONSUMPTION;
import static com.hotel.domain.constants.Constants.LIGHT_POWER_CONSUMPTION;
import static com.hotel.domain.constants.CorridorType.MAIN_CORRIDOR;
import static com.hotel.domain.constants.CorridorType.SUB_CORRIDOR;
import static com.hotel.domain.constants.EquipmentType.AIR_CONDITIONER;
import static com.hotel.domain.constants.EquipmentType.LIGHT_BULB;
import static com.hotel.domain.constants.MotionType.REST;
import static com.hotel.domain.constants.MotionType.MOVEMENT;
import static com.hotel.domain.constants.StateType.OFF;
import static com.hotel.domain.constants.StateType.ON;

public class Startup {
    public static void main(String[] args) {

        //Create all equipments for main & sub corridors on 1st floor
        List<Equipment> floor1MainCorridor1Equipments = getEquipments(ON);
        List<Equipment> floor1SubCorridor1Equipments = getEquipments(OFF);
        List<Equipment> floor1SubCorridor2Equipments = getEquipments(OFF);

        //Create main & sub corridors on 1st floor
        Corridor floor1MainCorridor1 = new Corridor("1", floor1MainCorridor1Equipments, MAIN_CORRIDOR);
        Corridor floor1SubCorridor1 = new Corridor("1", floor1SubCorridor1Equipments, SUB_CORRIDOR);
        Corridor floor1SubCorridor2 = new Corridor("2", floor1SubCorridor2Equipments, SUB_CORRIDOR);

        //Create 1st floor
        Floor firstFloor = getFloor("1", floor1MainCorridor1, floor1SubCorridor1, floor1SubCorridor2);

        //Create 2nd floor
        Floor secondFloor = getSecondFloor();

        List<Floor> floors = new LinkedList<>();
        floors.add(firstFloor);
        floors.add(secondFloor);

        Controller controller = new Controller(motions, floors);
        Motion movementInFloor1SubCorridor2 = new Motion(firstFloor, floor1SubCorridor2, MOVEMENT, controller);
        Motion noMovementInFloor2SubCorridor2 = new Motion(firstFloor, floor1SubCorridor2, REST, controller);

        movementInFloor1SubCorridor2.publishMotion();

        List<Motion> motions = new LinkedList<>();
        motions.add(movementInFloor1SubCorridor2);
        motions.add(noMovementInFloor2SubCorridor2);

        Controller controller = new Controller(motions, floors);
        Hotel hotel = new Hotel(floors, controller);
        hotel.startController();
    }

    private static Floor getSecondFloor() {
        List<Equipment> floor2MainCorridor1Equipments = getEquipments(ON);
        List<Equipment> floor2SubCorridor1Equipments = getEquipments(OFF);
        List<Equipment> floor2SubCorridor2Equipments = getEquipments(OFF);

        Corridor floor2MainCorridor1 = new Corridor("1", floor2MainCorridor1Equipments, MAIN_CORRIDOR);
        Corridor floor2SubCorridor1 = new Corridor("1", floor2SubCorridor1Equipments, SUB_CORRIDOR);
        Corridor floor2SubCorridor2 = new Corridor("2", floor2SubCorridor2Equipments, SUB_CORRIDOR);

        return getFloor("2", floor2MainCorridor1, floor2SubCorridor1, floor2SubCorridor2);
    }

    private static Floor getFloor(String floorId, Corridor mainCorridor1, Corridor subCorridor1, Corridor subCorridor2) {
        List<Corridor> corridors = new LinkedList<>();
        corridors.add(mainCorridor1);
        corridors.add(subCorridor1);
        corridors.add(subCorridor2);

        return new Floor(floorId, corridors);
    }

    private static List<Equipment> getEquipments(StateType state) {
        PowerConsumption lightPowerConsumption = new PowerConsumption(LIGHT_POWER_CONSUMPTION);
        PowerConsumption acPowerConsumption = new PowerConsumption(AC_POWER_CONSUMPTION);

        Equipment corridorLight = new Equipment(LIGHT_BULB, state, lightPowerConsumption);
        //AC should be in ON state for first time
        Equipment corridorAc = new Equipment(AIR_CONDITIONER, ON, acPowerConsumption);

        List<Equipment> corridorEquipments = new LinkedList<>();
        corridorEquipments.add(corridorLight);
        corridorEquipments.add(corridorAc);

        return corridorEquipments;
    }
}
