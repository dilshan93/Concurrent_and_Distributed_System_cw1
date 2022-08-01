package Vehicles;

import Enum.VehicleType;

public class Lorry extends Vehicle{
    public Lorry(String plateID) {
        super(plateID);
        this.vehicleType = VehicleType.Lorry;
        this.slotSpace = 5.0;
    }
}
