package Simulator;

import ParkManager.PettahMultiStoryCarParkManage;
import Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ExitThread implements Runnable{

    private String name;
    private ConcurrentLinkedDeque queue;
    private final int SLEEP;
    private PettahMultiStoryCarParkManage carParkManage;

    public ExitThread(String name, ConcurrentLinkedDeque queue, int sleep, PettahMultiStoryCarParkManage carParkManage) {
        this.name = name;
        this.queue = queue;
        SLEEP = sleep;
        this.carParkManage = carParkManage;
    }

    @Override
    public void run() {

        while (true) {
            List<Vehicle> vehicleList = new ArrayList<>(this.carParkManage.getVehicleList());
            Collections.shuffle(vehicleList);
            if (vehicleList.size() != 0) {
                Vehicle vehicleToRemove = vehicleList.get(0);
                try {
                    this.carParkManage.deleteVehicle(vehicleToRemove.getIdPlate());
                    System.out.println("From "+this
                            .name+" Vehicle :- "+vehicleToRemove.getVehicleType() + " With Plate :- "+ vehicleToRemove.getIdPlate() + " exit to the Car park. Charge amount :- "+
                            this.carParkManage.calculateChargers(vehicleToRemove));
                } catch (Exception e) {
                    System.out.println(vehicleToRemove.getIdPlate() + " already left the car park");
                }
            }
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
