package Moblima;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookMyShowApp {
	private static Scanner in;
	
	public static void main(String[] args) {
		//create empty cineplex, cinema, movies
//		movieHandler movieDB = new movieHandler();
//		Cineplex GoldenVillageJP = new Cineplex("Jurong Point");
//		Cinema hall_1 = new Cinema("Standard", 30, GoldenVillageJP);
//		Cinema hall_2 = new Cinema("platinum", 40, GoldenVillageJP);
		
		BookMyShowInterface BookMyShow = new BookMyShow();
		
		try {
			BookMyShow.readMovieFromTextFile("MovieList.txt");
		}catch (Exception e) {
			System.out.println("Movies File Read Error");
		}
		
		int option = 0;
		in = new Scanner(System.in);
		do {
			System.out.println();
        	System.out.println("----------------MOBLIMA MAIN MENU!----------------");
        	System.out.println("| 01: Admin Login                               |");
        	System.out.println("| 02: View All Movies                           |");
        	System.out.println("| 03: Search A Movie                            |");
        	System.out.println("| 04: View Top 5 Movies		                    |");
        	System.out.println("| 05: Booking Menu                              |");
        	System.out.println("| 06: Rate/Review A Movie                       |");
        	System.out.println("| 07: Initialize/Show Example                   |");
        	System.out.println("| 11: Create Show                               |");
        	System.out.println("--------------------------------------------------");
        	System.out.println();
        	while(true) {
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
        			Admin admin = Admin.getInstance();
        			admin.start();
        			break;
        		case 2:
        			BookMyShow.showAllMovies();
        			break;
        		case 3:
        			String searchString="";
        			System.out.print("Enter the movie title: ");
        			in.nextLine();
        			searchString = in.nextLine();
        			BookMyShow.searchMovie(searchString);
        			break;
        		case 4:
        			BookMyShow.showAllMoviesTicket();
        			break;
        		case 5:
        			BookMyShow.BookMovie();
        			break;
        		case 6:
        			break;
        		case 7:
        			BookMyShow.initializeExample();
        			System.out.println("Done!");
        			BookMyShow.showExample();
        			break;
        		case 11:
        			BookMyShow.createShow();
        			break;
        		}
        	}
		} while(option != -1);
		
	}
}
