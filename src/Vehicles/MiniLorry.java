package Vehicles;

import Enum.VehicleType;

public class MiniLorry extends Vehicle{
    public MiniLorry(String plateID) {
        super(plateID);
        this.vehicleType = VehicleType.MiniLorry;
        this.slotSpace = 3.0;
    }
}
