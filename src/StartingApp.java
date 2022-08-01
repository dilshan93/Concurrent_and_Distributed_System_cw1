import java.util.Scanner;

import DateTime.DateTime;
import Enum.VehicleType;
import ParkManager.ObjectCreator;
import ParkManager.PettahMultiStoryCarParkManage;
import Vehicles.Vehicle;

public class StartingApp {

    private static PettahMultiStoryCarParkManage pettahMultiStoryCarParkManage =  PettahMultiStoryCarParkManage.getInstance();

    public static void main(String[] args) {

        System.out.println("Welcome to Pettah Multi Story Car Park Manager");
        Scanner sc = new Scanner(System.in);
        while(true) {
            consoleMainOptions();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    deleteVehicle();
                    break;
                case 3:
                    printcurrentVehiclesList();
                    break;
                case 4:
                    pettahMultiStoryCarParkManage.getVehiclePercentages();
                    break;
                case 5:
                    pettahMultiStoryCarParkManage.getOldestAndLatestVehicle();
                    break;
                case 6:
                    parkedByDay();
                    break;
                case 7:
                    calCharge();
                    break;
                case 0:
                    System.out.println("=======================================");
                    System.out.println("<<< Thank you See You Soon >>>>");
                    System.out.println("=======================================");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice");
            }
        }
    }

    public static void consoleMainOptions() {
        System.out.println("Enter number to choose option");
        System.out.println("===============================================");
        System.out.println("Enter '1'  - To add a new vehicle ");
        System.out.println("Enter '2'  - To delete a vehicle vehicle ");
        System.out.println("Enter '3'  - To print current available vehicle ");
        System.out.println("Enter '4'  - Percentage of Vehicles parked");
        System.out.println("Enter '5'  - Latest and the Oldest Vehicle in the Car Park");
        System.out.println("Enter '6'  - Vehicles parked in a specific day");
        System.out.println("Enter '7'  - Charge for the parking");
        System.out.println("Enter '0' - Exit");
        System.out.println("===============================================");
    }

    public static void consoleAddVehicleOptions() {
        System.out.println("Select your choice :");
        System.out.println("===============================================");
        System.out.println("1. To add a Car.");
        System.out.println("2. To add a Motor Bike.");
        System.out.println("3. To add a Van.");
        System.out.println("4. To add a Bus.");
        System.out.println("5. To add a Lorry.");
        System.out.println("6. To add a MiniBus.");
        System.out.println("7. To add a Mini Lorry.");
        System.out.println("===============================================");
    }


    private static void addVehicle() {
        while (true) {
            consoleAddVehicleOptions();

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            VehicleType type = (choice == 1) ? VehicleType.Car : (choice == 2) ?
                    VehicleType.MotorBike : (choice == 3) ? VehicleType.Van : (choice == 4) ?
                    VehicleType.Bus : (choice == 5) ? VehicleType.Lorry : (choice == 6) ?
                    VehicleType.MiniLorry : (choice == 7) ? VehicleType.MiniBus : null;
            if (type == null){
                System.out.println("Invalid Choice of vehicle type! Choose between 1, 2, 3, 4, 5, 6 or 7");
                return;
            }
            ObjectCreator creater = new ObjectCreator();
            Vehicle vehicle = creater.createVehicle(type);
            pettahMultiStoryCarParkManage.addVehicle(vehicle);

            System.out.println("Add another vehicles? (Y/N)");
            String wantToContinue = sc.next();
            if (wantToContinue.toLowerCase().charAt(0) == 'n') {
                return;
            }
        }
    }

    public static void printcurrentVehiclesList() {
        System.out.println("Vehicle list in Chronological Order");
        System.out.println("===============================================");
        pettahMultiStoryCarParkManage.printcurrentVehiclesList();
    }

    public static void deleteVehicle() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the Plate ID :");
        String plateID=input.next();
        pettahMultiStoryCarParkManage.deleteVehicle(plateID);
    }

    private static void parkedByDay() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a Date to Find (DD/MM/YYYY) ");
        String checkThisTime=sc.next();
        //String[] dateString= checkThisTime.split("/");
        //DateTime dateTime = new DateTime(Integer.parseInt(dateString[0]),Integer.parseInt(dateString[1]),Integer.parseInt(dateString[2]));
        pettahMultiStoryCarParkManage.getVehicleBySpecificDate(checkThisTime);
    }

    private static void  calCharge() {
        System.out.println("Enter the Plate ID : ");
        Scanner sc = new Scanner(System.in);
        String plateID = sc.next();
        pettahMultiStoryCarParkManage.displayCharges(plateID);
    }

}
