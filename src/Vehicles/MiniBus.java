package Vehicles;

import Enum.VehicleType;

public class MiniBus extends Vehicle{
    public MiniBus(String plateID) {
        super(plateID);
        this.vehicleType = VehicleType.MiniBus;
        this.slotSpace = 3.0;
    }
}
