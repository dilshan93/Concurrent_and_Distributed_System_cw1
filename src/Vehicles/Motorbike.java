package Vehicles;

import Enum.VehicleType;

public class Motorbike extends Vehicle{
    public Motorbike(String plateID) {
        super(plateID);
        this.vehicleType = VehicleType.MotorBike;
        this.slotSpace = 1.0/3;
    }
}
