import ParkManager.PettahMultiStoryCarParkManage;
import Simulator.EnterThread;
import Simulator.ExitThread;
import Simulator.GenarateVehicleRunnable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import Enum.VehicleType;

public class ConcurrentSimulator {

    private ConcurrentLinkedDeque queue1, queue2, queue3, queue4;
    private PettahMultiStoryCarParkManage carParkManage;

    public ConcurrentSimulator() {
        this.carParkManage = PettahMultiStoryCarParkManage.getInstance();
        this.queue1 = new ConcurrentLinkedDeque();
        this.queue2 = new ConcurrentLinkedDeque();
        this.queue3 = new ConcurrentLinkedDeque();
        this.queue4 = new ConcurrentLinkedDeque();
    }

    public void startSimulation(){
        List<VehicleType> northEntranceAlowedVehicles = Collections.synchronizedList(Arrays.asList(
                VehicleType.Car, VehicleType.MotorBike, VehicleType.Van, VehicleType.Bus, VehicleType.Lorry ,
                VehicleType.MiniBus, VehicleType.MiniLorry));
        List<VehicleType> westEntrance1AlowedVehicles = Collections.synchronizedList(Arrays.asList(
                VehicleType.Car,  VehicleType.Van, VehicleType.MotorBike
        ));
        List<VehicleType> westEntrance2AlowedVehicles = Collections.synchronizedList(Arrays.asList(
                VehicleType.Car
        ));

        Thread randVehicleGenarate1 = new Thread(new GenarateVehicleRunnable("North Entry Queue 1", this.queue1,500, northEntranceAlowedVehicles));
        Thread randVehicleGenarate2 = new Thread(new GenarateVehicleRunnable("North Entry Queue 2", this.queue2,500, northEntranceAlowedVehicles));
        Thread randVehicleGenarate3 = new Thread(new GenarateVehicleRunnable("West Entry Queue 3", this.queue3,500, westEntrance1AlowedVehicles));
        Thread randVehicleGenarate4 = new Thread(new GenarateVehicleRunnable("West Entry Queue 4", this.queue4,500, westEntrance2AlowedVehicles));

        // Create Gates
        Thread entrance1 = new Thread(new EnterThread("Entrance 1", this.queue1, 500, this.carParkManage));
        Thread entrance2 = new Thread(new EnterThread("Entrance 2", this.queue2, 500, this.carParkManage));
        Thread entrance3 = new Thread(new EnterThread("Entrance 3", this.queue3, 500, this.carParkManage));
        Thread entrance4 = new Thread(new EnterThread("Entrance 4", this.queue4, 500, this.carParkManage));

        // Create Exists
        Thread exit1 = new Thread(new ExitThread("Exit 1", this.queue1, 2000, this.carParkManage));
        Thread exit2 = new Thread(new ExitThread("Exit 2", this.queue1, 2000, this.carParkManage));
        Thread exit3 = new Thread(new ExitThread("Exit 3", this.queue1, 2000, this.carParkManage));
        Thread exit4 = new Thread(new ExitThread("Exit 4", this.queue1, 2000, this.carParkManage));



        entrance1.start();
        randVehicleGenarate1.start();
        exit1.start();

        entrance2.start();
        randVehicleGenarate2.start();
        exit2.start();

        entrance3.start();
        randVehicleGenarate3.start();
        exit3.start();

        entrance4.start();
        randVehicleGenarate4.start();
        exit4.start();

    }

    public static void main(String[] args) {

        ConcurrentSimulator simulator = new ConcurrentSimulator();
        simulator.startSimulation();

    }
}
