package ParkManager;

import Vehicles.Vehicle;
import com.google.common.util.concurrent.AtomicDouble;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import Enum.VehicleType;

public class FloorStucture implements Comparable<FloorStucture>, Serializable {

    private AtomicDouble maxCapacity;
    private AtomicDouble currentCapacity;
    private AtomicInteger currentNumberOfVehicles;
    private List<Vehicle> vehicleList;
    private List<VehicleType> possibleVehicleType;
    private List<VehicleType> priorityVehicleType;
    final double MAX_FLOOR = 60.0;


    public FloorStucture(List<VehicleType> allowedVehicleTypes, List<VehicleType> priorityVehicleTypes) {

        this.currentCapacity = new AtomicDouble(0.0);
        this.currentNumberOfVehicles = new AtomicInteger(0);
        this.vehicleList = Collections.synchronizedList(new ArrayList<>());
        this.possibleVehicleType = allowedVehicleTypes;
        this.priorityVehicleType = priorityVehicleTypes;
        this.maxCapacity = new AtomicDouble(MAX_FLOOR);
    }

    public double getCurrentCapacity() {
        return currentCapacity.get();
    }

    public AtomicInteger getCurrentNumberOfVehicles() {
        return currentNumberOfVehicles;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public List<VehicleType> getPossibleVehicleType() {
        return possibleVehicleType;
    }

    public List<VehicleType> getPriorityVehicleType() {
        return priorityVehicleType;
    }

    public boolean isSlotsEnough(Vehicle vehicle){
        double neededSlouts = this.currentCapacity.get() + vehicle.getSlotSpace();
        return  neededSlouts <= maxCapacity.get();
    }

    public void saveVehicle(Vehicle vehicle){
        if(!this.isSlotsEnough(vehicle)){
            throw new IllegalStateException("No parking slots available");
        }

        this.currentCapacity.getAndAdd(vehicle.getSlotSpace());
        this.currentNumberOfVehicles.incrementAndGet();
        this.vehicleList.add(vehicle);
    }

    public Vehicle clearVehicalSlot(Vehicle vehicle){
        double freed_space = vehicle.getSlotSpace();
        this.currentCapacity.getAndAdd(-freed_space);
        this.currentNumberOfVehicles.decrementAndGet();
        return vehicle;
    }
    public double getAvailableSlots() {
        return maxCapacity.get() - this.currentCapacity.get();
    }


    @Override
    public int compareTo(FloorStucture o) {
        double currCapacity = this.getCurrentCapacity();
        double otherCapacity = o.getCurrentCapacity();
        return Double.compare(currCapacity, otherCapacity);
    }
}
