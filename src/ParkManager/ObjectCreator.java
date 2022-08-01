package ParkManager;

import DateTime.DateTime;
import Vehicles.*;
import Enum.VehicleType;

import java.awt.*;
import java.util.Scanner;

public class ObjectCreator {

    public Vehicle createVehicle(VehicleType type) {

        Vehicle obj=null;
        Scanner sc=new Scanner(System.in); // Question
        System.out.println("Enter Plate ID :");
        String plateID = sc.next();
//        System.out.println("Enter the Brand :");
//        String brand= sc.next();
//        System.out.println("Enter the model :");
//        String model=sc.next();
//
//        System.out.println("Enter the date and time (DD/MM/YYYY-HH:mm:ss)");
//        String dateTime=sc.next();
        //adding the data in to a string array
//        String[] arr= dateTime.split("-");
//        String[] dateString= arr[0].split("/");
//        String[] timeString=arr[1].split(":");

        switch(type) {
            case Car:

                obj=new Car(plateID);
                break;

            case MotorBike:

                obj=new Motorbike(plateID);
                break;

            case Van:

                obj=new Van(plateID);
                break;

            case Bus:

                obj=new Bus(plateID);
                break;

            case Lorry:

                obj=new Lorry(plateID);
                break;

            case MiniBus:

                obj=new MiniBus(plateID);
                break;

            case MiniLorry:

                obj=new MiniLorry(plateID);
                break;

            default :
                System.out.println("Invalid Choice");

        }
        return obj;
    }
}
