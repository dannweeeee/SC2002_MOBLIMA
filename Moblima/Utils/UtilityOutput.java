package Moblima.Utils;

import java.util.ArrayList;

import Moblima.Entities.Seats;

public class UtilityOutput {
    public UtilityOutput(){}

    public static void printMessage(String message){
        System.out.println(message);
    }

    public static void printInputMessage(String question){
        System.out.print(question);
    }

    public static void printMenu(String[] menu){
        for (String s : menu){
            UtilityOutput.printMessage(s);
        }
    }

    public static void printObjectList(ArrayList<?> objList){
        for (Object o : objList){
            UtilityOutput.printMessage(o.toString());
        }
    }

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
            System.out.print("|  " + seat.getSeat() + "  | ");
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
