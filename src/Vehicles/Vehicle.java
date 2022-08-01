package Vehicles;

import DateTime.DateTime;
import Enum.VehicleType;

import java.io.Serializable;

public abstract class Vehicle implements Comparable<Vehicle>, Serializable {

    private String idPlate;
    protected DateTime entryTime;
    protected VehicleType vehicleType;
    protected double slotSpace;

    public Vehicle(String plateID) {
        this.entryTime = new DateTime();
        this.idPlate = plateID;
    }

    public String getIdPlate() {
        return idPlate;
    }

    public void setIdPlate(String idPlate) {
        this.idPlate = idPlate;
    }

    public DateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(DateTime entryTime) {
        this.entryTime = entryTime;
    }

    public VehicleType getVehicleType() {
        return this.vehicleType;
    }

    public double getSlotSpace() {
        return this.slotSpace;
    }



    @Override
    public int compareTo(Vehicle o) {
        return 0;
    }
}
