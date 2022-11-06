package Moblima;

import java.util.InputMismatchException;
import java.util.Scanner;

import Moblima.Admin.Admin;
import Moblima.DataBase.ExampleAdder;
import Moblima.Exceptions.InvalidInputException;

public class MovieBookerApp {
	private static Scanner in;
	
	public static void main(String[] args) {
		
		MovieBookerInterface movieBooker = new MovieBooker();
		
		try {
			ExampleAdder.readMovieFromTextFile("Moblima/Utils/MovieList.txt");
		}catch (Exception e) {
			System.out.println("Movies File Read Error");
		}
		showUserView(movieBooker);
	}

	public static void showUserView(MovieBookerInterface movieBooker) {
		int option = 0;
		in = new Scanner(System.in);
		do {
			while(true) {
	            System.out.println();
	            System.out.println("-----------------MOBLIMA MAIN MENU---------------");
	        	System.out.println("| 01: Show All Movies                           |");
	        	System.out.println("| 02: Search Movie                              |");
	        	System.out.println("| 03: View Showtimes                            |");
	        	System.out.println("| 04: View Top 5 Movies                         |");
	        	System.out.println("| 05: Review/Rate Menu                          |");
	        	System.out.println("| 06: Booking  Menu                             |");
	        	System.out.println("| 07: Show Booking History                      |");
	        	System.out.println("| 08:                                           |");
	        	System.out.println("| 09: Initialize/Show Example                   |");
				System.out.println("| 10: ADMIN Login                               |");
	            System.out.println("-------------------------------------------------");
	            System.out.println();
     
        		System.out.print("Main Menu - Enter option ('-1' to exit):");
        		try {
        			option = in.nextInt();
        		}catch(InputMismatchException e) {
        			System.out.println("Invalid Input. Please re-enter.");
        			in.next();
        			continue;
        		}
				try{
					switch (option) {
					case -1:
						System.out.println("Goodbye!");
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
						movieBooker.showAllMoviesTicket();
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
					System.out.println(e.getMessage());
				}
        	}
		} while(option != -1);
	}
}