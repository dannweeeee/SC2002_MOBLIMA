package Moblima.Utils;

public class UtilityOutput {
    public UtilityOutput(){}

    public static void printMenu(String[] menu){
        for (String s : menu){
            System.out.println(s);
        }
    }

    public static void printMessage(String message){
        System.out.println(message);
    }

    public static void printInputMessage(String question){
        System.out.print(question);
    }
}
