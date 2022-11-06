package Moblima;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookMyShowApp {
	private static Scanner in;
	
	public static void main(String[] args) {
		
		BookMyShowInterface BookMyShow = new BookMyShow();
		
		try {
			BookMyShow.readMovieFromTextFile("MovieList.txt");
		}catch (Exception e) {
			System.out.println("Movies File Read Error");
		}
		
		int option = 0;
		in = new Scanner(System.in);
		do {
			while(true) {
	            System.out.println();
	            System.out.println("-----------------MOBLIMA MAIN MENU---------------");
	        	System.out.println("| 01: Show all Movies                           |");
	        	System.out.println("| 02: View Showtimes                            |");
	        	System.out.println("| 03: Review/Rate Movies                        |");
	        	System.out.println("| 04: Initialize/Show Example                   |");
	        	System.out.println("| 05: View Top 5 Movies                         |");
	        	System.out.println("| 06: Booking  Menu                             |");
	        	System.out.println("| 07: Show Booking History                      |");
	        	System.out.println("| 08: Search Movie                              |");
	        	System.out.println("| 09: Create Show                               |");
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
        		switch (option) {
        		case -1:
        			System.out.println("Goodbye!");
        			return;
        		case 1:
					BookMyShow.showAllMovies();
        			break;
        		case 2:
					BookMyShow.showShowTimes();
        			break;
        		case 3:
        			BookMyShow.createRatingReview();
        			break;
        		case 4:
					BookMyShow.initializeExample();
					System.out.println("Done!");
					BookMyShow.showExample();
        			break;
        		case 5:
        			BookMyShow.showAllMoviesTicket();
        			break;
        		case 6:
					BookMyShow.BookMovie();
        			break;
        		case 7:
        			BookMyShow.showBookingHist();
        			break;
        		case 8:
					String searchString="";
					System.out.print("Enter the movie title: ");
					in.nextLine();
					searchString = in.nextLine();
					BookMyShow.searchMovie(searchString);
        			break;
        		case 9:
					BookMyShow.createShow();
        			break;
        		case 10:
					Admin admin = Admin.getInstance();
					admin.start();
        			return;
        		}
        	}
		} while(option != -1);
	}
}
