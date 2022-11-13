package Moblima.Utils;

import java.util.ArrayList;

import Moblima.Entities.Seats;

/**
 * Class for displaying all outputs for user
 */
public class UtilityOutput {
    /**
     * Default Constructor
     */
    public UtilityOutput(){}

    /**
     * Print message on console for user to read
     * @param message - String - e.g. "SUCCESS! Movie has been booked."
     */
    public static void printMessage(String message){
        System.out.println(message);
    }

    /**
     * Print question on console for user to read
     * @param question - String - e.g. 'Enter Number of Adult Tickets:'
     */
    public static void printInputMessage(String question){
        System.out.print(question);
    }

    /**
     * Print menu on console for user to view
     * @param menu - e.g. 
     */
    public static void printMenu(String[] menu){
        for (String s : menu){
            UtilityOutput.printMessage(s);
        }
    }
    
    public static void printObject(Object obj) {
    	UtilityOutput.printMessage(obj.toString());
    }

    /**
     * Print array list of objects on console for user to view
     * @param objList - List of objects - e.g. List of any object which shows all Movie Listings
     */
    public static void printObjectList(ArrayList<?> objList){
        for (Object o : objList){
            UtilityOutput.printMessage(o.toString());
        }
    }

    /**
     * Print array list of seating arrangement for user booking
     * @param seats - List of Seats - e.g. the list which shows the available seating arrangement of the Cinema
     */
    public static void printSeatingForBooking(ArrayList<Seats> seats){
        System.out.println("Seats still available: ");
        int i = 0;
        int x = 10;

        if (seats.size() < 10){
            x = seats.size();
        }
        while (i < x){
            System.out.print("  ----   ");
            i++;
        }

        System.out.println();
        i = 0;
        for(Seats seat : seats){
            System.out.print("|  " + seat.toString() + "  | ");
            if((i+1) % 10 == 0 && i != 0){
                System.out.println("");
                i = 0;
                while (i < 10){
                    System.out.print("  ----   ");
                    i++;
                }
                System.out.println();
                i = -1;
            }
            i++;
        }
        System.out.println();
        for (int j = 0; j < i ; j++){
            System.out.print("  ----   ");
        }
    }
}
