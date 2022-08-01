package Simulator;

import ParkManager.PettahMultiStoryCarParkManage;
import Vehicles.Vehicle;

import java.util.concurrent.ConcurrentLinkedDeque;

public class EnterThread implements Runnable{

    private String name;
    private ConcurrentLinkedDeque queue;
    private final int SLEEP;
    private PettahMultiStoryCarParkManage carParkManage;

    public EnterThread(String name, ConcurrentLinkedDeque queue, int sleep, PettahMultiStoryCarParkManage carParkManage) {
        this.name = name;
        this.queue = queue;
        SLEEP = sleep;
        this.carParkManage = carParkManage;
    }

    @Override
    public void run() {
        while(true){
            Vehicle vehicle = (Vehicle) this.queue.poll();
            try {
                if(vehicle != null){
                    this.carParkManage.addVehicle(vehicle);
                    System.out.println("From "+this
                            .name+" Vehicle :- "+vehicle.getVehicleType() + " With Plate :- "+ vehicle.getIdPlate() + " entered to the Car park");
                }
            } catch (Exception e) {
                System.out.println("Cannot accommodate "+vehicle+" at the moment");
            }
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
