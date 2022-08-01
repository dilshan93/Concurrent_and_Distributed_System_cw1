package Vehicles;
import Enum.VehicleType;

public class Van extends Vehicle{
    public Van(String plateID) {
        super(plateID);
        this.vehicleType = VehicleType.Van;
        this.slotSpace = 2.0;

    }
}
