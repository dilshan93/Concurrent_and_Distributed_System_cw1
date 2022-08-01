package ParkManager;

import DateTime.DateTime;
import Vehicles.Vehicle;

public interface CarParkManager {

    public void addVehicle(Vehicle obj);
    public void printcurrentVehiclesList();
    public void deleteVehicle(String IdPlate);
    public void getVehiclePercentages();
    public void getOldestAndLatestVehicle();
    public void getVehicleBySpecificDate(String dateTime);
    public double calculateChargers(Vehicle obj);
    public void displayCharges(String IdPlate);
}
