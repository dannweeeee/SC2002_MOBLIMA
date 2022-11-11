package Moblima.Utils;

import java.util.ArrayList;
import java.util.Scanner;

import Moblima.Entities.Seats;
import Moblima.Entities.Show;
import Moblima.Entities.User;
import Moblima.Handlers.ShowHandler;

/**
 * Class for getting all inputs from user
 */
public class UtilityInputs {
	/**
	 * Default Contructor
	 */
    public UtilityInputs(){}

    /**
	 * Get user's input and check for valid integer input
	 * @return int - (-1) for invalid input, else returns user's input
	 */
	public static int getIntUserInput(){
		int intInput = -1;
		String input = "";
		Scanner in = new Scanner(System.in);
		input = in.nextLine();
		try {
			intInput = Integer.parseInt(input);
		}catch (NumberFormatException e) {
			UtilityOutput.printMessage("Invalid input: " + input + " is not a number");
		}
		return intInput;
	}
	
	/**
	 * Get user's input and check for valid Double
	 * @return double - return "" for invalid input, else returns user's input
	 */
	public static Double getDoubleUserInput() {
		Double intInput = null;
		String input = "";
		Scanner in = new Scanner(System.in);
		input = in.nextLine();
		try {
			intInput = Double.parseDouble(input);
		}catch (NumberFormatException e) {
			UtilityOutput.printMessage("Invalid input: " + input + " is not a number");
			UtilityOutput.printMessage("");
		}
		
		return intInput;
	}
    
	/**
	 * Get user's String input
	 * @return user's input
	 */
    public static String getStringUserInput() {
    	Scanner in = new Scanner(System.in);
    	String inputString = in.nextLine();
    	return inputString;
    }

	/**
	 * Prompt user for seat selection for each ticket
	 * @param number - ticket number for prompt E.g. Ticket 2
	 * @return valid seat object user has chosen
	 */
    public static Seats getSeatSelection(int number){
		while(true){
			System.out.print("Seat number for Ticket " + Integer.toString(number+1) + ": ");
			Scanner in = new Scanner(System.in);
			String seats = in.nextLine();

			if (seats.equalsIgnoreCase("XX") || seats.length() != 2){
				System.out.println("Invalid seat");
				continue;
			} else if (seats == "0") return null;

			String row = String.valueOf(seats.charAt(0)).toUpperCase();
			String col = String.valueOf(seats.charAt(1));
			Seats s1 = new Seats(row, col);
			return s1;
		}
	}
	/**
	 * Ask user to select show from the list of shows
	 * @param allShows - list of shows to be seleceted from
	 * @return valid show object user has chosen
	 */
    public static Show getShow(ArrayList<Show> allShows){
        while (true){
            UtilityOutput.printInputMessage("Please enter the show number [Enter '0' to exit] => ");
            int choice = getIntUserInput();
            if (choice == 0) break;

            for (Show s : allShows){
            	if (s.getID() == choice)
            		return ShowHandler.getShowByID(allShows, choice);
            }
            UtilityOutput.printMessage("Invalid input, please try again");
        }
        return null;
    }
	/**
	 * Get number of tickets 
	 * @param category - category for ticket
	 * @return number of ticket
	 */
    public static int getNumberOfTicket(String category){
        int numTickets = -1;
        while (true){
            UtilityOutput.printMessage("");
            UtilityOutput.printInputMessage("How many " + category + " tickets would you like to book? => ");
            numTickets = getIntUserInput();
            if (numTickets >= 0) break;
        }
        return numTickets;
	}
	/**
	 * Get user's information for booking
	 * @return user object
	 */
	public static User getUserInformation(){
		int number = -1;
		String email = "";
        UtilityOutput.printInputMessage("Please enter your name: ");
        String name = UtilityInputs.getStringUserInput();

		while (true){
			UtilityOutput.printInputMessage("Please enter your email: ");
        	email = UtilityInputs.getStringUserInput();
			if (email.contains("@")){
				break;
			}else{
				UtilityOutput.printMessage("Invalid email, make sure it contains '@'");
			}
		}

		while(number == -1){
			UtilityOutput.printInputMessage("Please enter your number: ");
        	number = getIntUserInput();
		}

        return new User(name, email, number);
    }

	/**
	 * Prints out total price and ask for user's confirmation
	 * @param totalPrice - total price of ticket
	 * @return user's input
	 */
	public static char getConfirmation(double totalPrice){
		UtilityOutput.printMessage("Total price: " + Double.toString(totalPrice));
		UtilityOutput.printInputMessage("Confirm ticket booking (Y/N): ");
        char confirmation = UtilityInputs.getStringUserInput().charAt(0);
		return confirmation;
	}
}
