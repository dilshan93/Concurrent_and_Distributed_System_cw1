package Vehicles;

import Enum.VehicleType;

public class Car extends Vehicle{

    public Car(String plateID) {
        super(plateID);
        this.vehicleType = VehicleType.Car;
        this.slotSpace = 1.0;
    }
}
