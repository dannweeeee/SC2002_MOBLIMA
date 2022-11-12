package Moblima;

import Moblima.Admin.Admin;
import Moblima.DataBase.ExampleAdder;
import Moblima.Exceptions.InvalidInputException;
import Moblima.Utils.UtilityInputs;
import Moblima.Utils.UtilityOutput;

/**
 * Entry point for the application
 * 
 */
public class MovieBookerApp {

	/**
	 * Default Consutructor for MovieBookerApp
	 */
	public MovieBookerApp(){}
	/**
	 * Main Method for the entire MovieBooker Application
	 * @param args arguments when running the program
	 */
	public static void main(String[] args) {
		
		MovieBookerInterface movieBooker = new MovieBooker();
		
		try {
			ExampleAdder.readMovieFromTextFile("Moblima/Utils/MovieList.txt");
		}catch (Exception e) {
			System.out.println("Movies File Read Error");
		}
		showUserView(movieBooker);
	}

	/**
	 * Application's main menu 
	 * @param movieBooker movieBooker object
	 */
	public static void showUserView(MovieBookerInterface movieBooker) {
		int option = 0;
		String[] menu = {"-----------------MOBLIMA MAIN MENU---------------", 
				"| 01: Show All Movies                           |",
				"| 02: Search Movie                              |",
				"| 03: View Showtimes                            |",
				"| 04: View Top 5 Movies                         |",
				"| 05: Review/Rate Menu                          |",
				"| 06: Booking  Menu                             |",
				"| 07: Show Booking History                      |",
				"| 08:                                           |",
				"| 09: Initialize/Show Example                   |",
				"| 10: ADMIN LOGIN                               |",
				"-------------------------------------------------",
				""};
		do {
			while(true) {
				UtilityOutput.printMenu(menu);
        		UtilityOutput.printInputMessage("Main Menu - Enter option ('0' to exit app): ");
        		
        		option = UtilityInputs.getIntUserInput();
        		
				try{
					switch (option) {
					case 0:
						UtilityOutput.printMessage("Goodbye!");
						System.exit(0);
						return;
					case 1:
						movieBooker.showAllMovies();
						break;
					case 2:
						movieBooker.searchMovie();
						break;
					case 3:
						movieBooker.showShowTimes();
						break;
					case 4:
						movieBooker.showSortedMovies();
						break;
					case 5:
						movieBooker.createRatingReview();
						break;
					case 6:
						movieBooker.bookingMenu();
						break;
					case 7:
						movieBooker.showBookingHist();
						break;
					case 8:
						
						break;
					case 9:
						movieBooker.addExamples();
						break;
          			case 10:
            			Admin admin = new Admin(movieBooker);
						admin.start();
						return;
					default:
						throw new InvalidInputException("Invalid input, please enter only numbers listed on the menu only");
					}
				} catch(InvalidInputException e){
					UtilityOutput.printMessage(e.getMessage());
				}
        	}
		} while(option != -1);
	}
}
