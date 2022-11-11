package Moblima.Utils;

import java.util.ArrayList;

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
}
