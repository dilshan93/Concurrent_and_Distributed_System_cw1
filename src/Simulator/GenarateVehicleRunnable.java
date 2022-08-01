package Simulator;

import Vehicles.*;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import Enum.VehicleType;

public class GenarateVehicleRunnable implements Runnable{

    private String name;
    private ConcurrentLinkedDeque queue;
    private final int SLEEP;

    List<VehicleType> allowedVehicles;

    public GenarateVehicleRunnable(String name, ConcurrentLinkedDeque queue, int sleep, List<VehicleType> allowedVehicles) {
        this.name = name;
        this.queue = queue;
        SLEEP = sleep;
        this.allowedVehicles = allowedVehicles;
    }

    private String getGenaratedPlateID(int enterLength){
        // Vocabulary to generate the ID from
        String Pattern = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder plateID = new StringBuilder();
        Random random = new Random();
        while (plateID.length() < enterLength){
            int index = (int) (random.nextFloat() * Pattern.length());
            plateID.append(Pattern.charAt(index));
        }
        return plateID.toString();
    }

    private Vehicle getgeneratedVehicle(){
        String plateID = getGenaratedPlateID(6);
        Collections.shuffle(this.allowedVehicles);


        Vehicle vehicle = null;
        VehicleType type = this.allowedVehicles.get(0);

        switch (type) {
            case Car:
                vehicle = new Car(plateID);
                break;
            case Van:
                vehicle = new Van(plateID);
                break;
            case MotorBike:
                vehicle = new Motorbike(plateID);
                break;
            case Bus:
                vehicle = new Bus(plateID);
                break;
            case Lorry:
                vehicle = new Lorry(plateID);
                break;
            case MiniBus:
                vehicle = new MiniBus(plateID);
                break;
             case MiniLorry:
                vehicle = new MiniLorry(plateID);
                break;
        }

        return vehicle;
    }

    @Override
    public void run() {
        while(true){
            Vehicle vehicle = this.getgeneratedVehicle();
            try {
                queue.add(vehicle);
                System.out.println("From "+this
                        .name+" Vehicle :- "+vehicle.getVehicleType() + " With Plate :- "+ vehicle.getIdPlate() + " added the Queue");
            } catch (Exception e) {
                System.out.println("Error when adding vehicle to queue.");
            }
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
