package Vehicles;

import Enum.VehicleType;

public class Bus extends Vehicle{
    public Bus(String plateID) {
        super(plateID);
        this.vehicleType = VehicleType.Bus;
        this.slotSpace = 5.0;
    }
}
