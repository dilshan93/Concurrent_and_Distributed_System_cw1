package ParkManager;

import DateTime.DateTime;
import Vehicles.*;

import java.util.*;

import Enum.VehicleType;

public class PettahMultiStoryCarParkManage implements CarParkManager{
//    private ArrayList<Vehicle> listOfVehicle = new ArrayList<Vehicle>();
    private List<FloorStucture> floorList;
    public static PettahMultiStoryCarParkManage instance = null;
//    private FloorStucture floorStucture;
    private List<Vehicle> vehicleList;


    public PettahMultiStoryCarParkManage() {
        this.floorList = this.createFloors();
        this.vehicleList = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public void addVehicle(Vehicle obj) {

        try {
            FloorStucture availableSlots = this.getBestFloor(obj);
            availableSlots.saveVehicle(obj);
            this.vehicleList.add(obj);
            System.out.println("Vehicle added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(String.format("%.2f", getFreeSlots()) + " slots are avalable in the car park");
        }
    }

    @Override
    public void printcurrentVehiclesList() {
        List<Vehicle> orderedVehicleList = Collections.synchronizedList(this.vehicleList);;
        Collections.sort(orderedVehicleList);
        List<Vehicle> vehicleList = orderedVehicleList;
        if (orderedVehicleList.size() < 1){
            System.out.println("No vehicles parked in the parking");
            return;
        }
        for(Vehicle vehicle: vehicleList){
            VehicleType vehicleType = vehicle.getVehicleType();
            String plateID = vehicle.getIdPlate();
            String entryTime = vehicle.getEntryTime().getDateTime();

            System.out.println(vehicleType +" with plate id - "+plateID+" entered at: "+entryTime);
        }
    }

    @Override
    public void deleteVehicle(String IdPlate) {
        Vehicle vehicle = null;
        double charges = 0;
        try {
            for (Vehicle item : this.vehicleList){
                if (item.getIdPlate().equals(IdPlate)){
                    vehicle = item;
                    charges = calculateChargers(item);
                    break;
                }
            }
            for(FloorStucture currFloor : this.floorList){
                if (vehicle != null){
                    this.vehicleList.remove(vehicle);
                    currFloor.clearVehicalSlot(vehicle);
                }
            }
            System.out.println(vehicle.getVehicleType()+" with plate id - "+IdPlate+" was exited. And Total Payable : - Rs. "+charges);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    public double getFreeSlots() {
        double freeSlots = 0.0;

        for (FloorStucture currFloor : this.floorList) {
            freeSlots += currFloor.getAvailableSlots();
        }
        return freeSlots;
    }


    @Override
    public void getVehiclePercentages() {
      //  int totCar = 0,totVan = 0,totMotobike = 0,totBus = 0,totLorry = 0,totMiniBus = 0,totMiniLorry = 0;
        double totalCount = 0;

        HashMap<VehicleType, Integer> percentage = new HashMap<VehicleType, Integer>();
        for (Vehicle vehicle: vehicleList){
            totalCount++;
            if (!percentage.containsKey(vehicle.getVehicleType())){
                percentage.put(vehicle.getVehicleType(), 1);
            }else{
                percentage.put(vehicle.getVehicleType(), percentage.get(vehicle.getVehicleType())+1);
            }
        }
        double percentageFun = 100/totalCount;
        System.out.println("Percentages of Vehicle Type In Car park");
        System.out.println("==================================");
        if (vehicleList.size() < 1){
            System.out.println(" No Vehicle Parked at the moment ...");
        }else {
            for (VehicleType type : percentage.keySet()) {
                System.out.println(type + " :- " + percentage.get(type) * percentageFun + "%");
                break;
            }
        }
        

    }

    public List<Vehicle> getVehicleList() {
        List<Vehicle> sortedVehicleList = Collections.synchronizedList(this.vehicleList);;
        Collections.sort(sortedVehicleList);
        return vehicleList;
    }

    @Override
    public void getOldestAndLatestVehicle() {
        if(this.vehicleList.size() > 0) {
            Vehicle oldest = vehicleList.get(0);
            Vehicle resent = vehicleList.get(vehicleList.size() - 1);

            System.out.println("Oldest vehicle :- " + oldest.getVehicleType() + " with plate :- " + oldest.getIdPlate() + " entered at: " + oldest.getEntryTime().getDateTime());
            System.out.println("Latest vehicle :- " + resent.getVehicleType() + " with plate :- " + resent.getIdPlate() + " entered at: " + resent.getEntryTime().getDateTime());
        }else {
            System.out.println("NO Vehicles parked Today !! ");
        }

    }

    @Override
    public void getVehicleBySpecificDate(String enterdDate) {

        for (Vehicle vehicle: this.vehicleList){
            if (vehicle.getEntryTime().getDate().equalsIgnoreCase(enterdDate)){
                System.out.println(vehicle.getVehicleType() +" with plate id - "+vehicle.getIdPlate()+" entered on: "+ vehicle.getEntryTime().getDateTime());
            } else {
                System.out.println("No Vehicle found on the enterd date...");
            }
        }
    }

    @Override
    public double calculateChargers(Vehicle vehicle) {

        double firstCharge = 50.0;
        double AdditinalCharge = 75.0;
        double maximumCHarge = 1200.0;

        double totAmount;
        DateTime currentTime = new DateTime();
        double parkedHours = currentTime.compareTo(vehicle.getEntryTime())/(60.0*60.0);
        double neededSlots = vehicle.getSlotSpace();

        if (parkedHours < 1){
            parkedHours = 1;
        }

        if(parkedHours <= 3.0){
            totAmount = firstCharge*neededSlots*parkedHours;
            return capCost(totAmount, maximumCHarge );
        }else if (parkedHours <= 24.0){

            long days = Math.floorDiv((int) parkedHours, 24);

            totAmount = days * maximumCHarge + capCost((parkedHours - (24 * days)) * AdditinalCharge * neededSlots, maximumCHarge);
            return totAmount;

//            totAmount = firstCharge*neededSlots*3;
//            parkedHours -= 3.0;
//            totAmount += AdditinalCharge*neededSlots*parkedHours;
//            return capCost(totAmount, maximumCHarge );
        }else{
            // Cost of first 3 hours
//            totAmount = firstCharge*neededSlots*3;
//            parkedHours -= 3.0;
//            // Cost of next 21 hours
//            totAmount += AdditinalCharge*neededSlots*21;
//            parkedHours -= 3.0;
//            totAmount = capCost(totAmount, maximumCHarge );

//            while(true){
//                if(parkedHours <= 24){
//                    totAmount += capCost(AdditinalCharge*neededSlots*parkedHours, maximumCHarge );
//                }else{
//                    totAmount += capCost(AdditinalCharge*neededSlots*24.0, maximumCHarge );
//                }
//
//                parkedHours -= 24;
//
//                if (parkedHours <= 0){
//                    return totAmount;
//                }
//            }
            totAmount = firstCharge * neededSlots * 3;
            parkedHours -= 3.0;

            totAmount += AdditinalCharge * neededSlots * parkedHours;
            totAmount = capCost(totAmount, maximumCHarge);

            return totAmount;

        }
    }

    @Override
    public void displayCharges(String IdPlate) {
        Vehicle vehicle = null;
        for (Vehicle item : this.vehicleList){
            if (item.getIdPlate().equals(IdPlate)){
                double charges = calculateChargers(item);
                System.out.println(item.getVehicleType()+ " with plate id - " + IdPlate + " has a parking charge of Rs."+ charges );
                break;
            }
        }

    }

    private double capCost(double tot, double maxCharge){
        return Math.min(tot, maxCharge);
    }


    //method which returns an object of same type
    public static PettahMultiStoryCarParkManage getInstance() {
        if(instance == null) {
            synchronized(PettahMultiStoryCarParkManage.class){
                if(instance==null) {
                    instance = new PettahMultiStoryCarParkManage();
                }
            }
        }
        return instance;
    }

    private FloorStucture getBestFloor(Vehicle vehicle) throws Exception {

        List<FloorStucture> possibleFloors = Collections.synchronizedList(new ArrayList<>());
        List<FloorStucture> priorityFloors = Collections.synchronizedList(new ArrayList<>());

        for (FloorStucture floor : this.floorList){
            List<VehicleType> possibalVehicaltypeList = floor.getPossibleVehicleType();
            List<VehicleType> priorityVehicaltypeList = floor.getPriorityVehicleType();

            VehicleType currentVehicle = vehicle.getVehicleType();

            if (priorityVehicaltypeList.contains(currentVehicle) && floor.isSlotsEnough(vehicle)){
                priorityFloors.add(floor);
            } else if (possibalVehicaltypeList.contains(currentVehicle) && floor.isSlotsEnough(vehicle)){
                possibleFloors.add(floor);
            }
        }

        if (priorityFloors.size() > 0){
            Collections.sort(priorityFloors, Collections.reverseOrder());
            return priorityFloors.get(0);
        }else if (possibleFloors.size() > 0){
            Collections.sort(possibleFloors);
            return priorityFloors.get(0);
        }
        else {
            throw new Exception("NO Parking slots available for Vehicle Type");
        }
    }

    private List<FloorStucture> createFloors(){
        List<FloorStucture> list = Collections.synchronizedList(new ArrayList<>());

        List<VehicleType> groundFloorAlowedVehicles = Collections.synchronizedList(Arrays.asList(
                VehicleType.Car, VehicleType.MotorBike, VehicleType.Van,VehicleType.Bus, VehicleType.Lorry ,
                VehicleType.MiniBus, VehicleType.MiniLorry
        ));

        List<VehicleType> groundFloorPriorityVehicles = Collections.synchronizedList(Arrays.asList(
                VehicleType.Bus, VehicleType.Lorry , VehicleType.MiniBus, VehicleType.MiniLorry
        ));

        List<VehicleType> firstToSixFloorAlowedVehicles = Collections.synchronizedList(Arrays.asList(
                VehicleType.Car,  VehicleType.Van, VehicleType.MotorBike
        ));
        List<VehicleType> firstToSecondFloorPriorityVehicles = Collections.synchronizedList(Arrays.asList(
                VehicleType.Van
        ));
        List<VehicleType> seventhToNinethFloorAlowedVehicles = Collections.synchronizedList(Arrays.asList(
                VehicleType.Car
        ));

        FloorStucture groundFloor = new FloorStucture(groundFloorAlowedVehicles, groundFloorPriorityVehicles);
        FloorStucture firstFloor = new FloorStucture(firstToSixFloorAlowedVehicles,firstToSecondFloorPriorityVehicles);
        FloorStucture secondFloor = new FloorStucture(firstToSixFloorAlowedVehicles,firstToSecondFloorPriorityVehicles);
        FloorStucture thirdFloor = new FloorStucture(firstToSixFloorAlowedVehicles,firstToSixFloorAlowedVehicles);
        FloorStucture fourthFloor = new FloorStucture(firstToSixFloorAlowedVehicles,firstToSixFloorAlowedVehicles);
        FloorStucture fifthFloor = new FloorStucture(firstToSixFloorAlowedVehicles,firstToSixFloorAlowedVehicles);
        FloorStucture sixthFloor = new FloorStucture(firstToSixFloorAlowedVehicles,firstToSixFloorAlowedVehicles);
        FloorStucture seventhFloor = new FloorStucture(seventhToNinethFloorAlowedVehicles,seventhToNinethFloorAlowedVehicles);
        FloorStucture eighthFloor = new FloorStucture(seventhToNinethFloorAlowedVehicles,seventhToNinethFloorAlowedVehicles);
        FloorStucture ninethFloor = new FloorStucture(seventhToNinethFloorAlowedVehicles,seventhToNinethFloorAlowedVehicles);

        list.add(groundFloor);
        list.add(firstFloor);
        list.add(secondFloor);
        list.add(thirdFloor);
        list.add(fourthFloor);
        list.add(fifthFloor);
        list.add(sixthFloor);
        list.add(seventhFloor);
        list.add(eighthFloor);
        list.add(ninethFloor);


        return list;
    }

}
