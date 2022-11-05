package Moblima;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MovieGoer {
    private static Scanner in;

    public MovieGoer(){

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
            System.out.println("-----------------MOBLIMA MAIN MENU---------------");
        	System.out.println("| 01: View Movies                               |");
        	System.out.println("| 02: View Showtimes                            |");
        	System.out.println("| 03: Review/Rate Movies                        |");
        	System.out.println("| 04: Create movie/showtime booking             |");
        	System.out.println("| 05: Initialize/Show Example                   |");
        	System.out.println("| 06: Show all Movies                           |");
        	System.out.println("| 07: Booking                                   |");
        	System.out.println("| 08: Show all Movies by ticket sales           |");
        	System.out.println("| 09: Search Movie                              |");
        	System.out.println("| 10: Create Show                               |");
			System.out.println("| 11: ADMIN VIEW                                |");
            System.out.println("-------------------------------------------------");
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
					//BookMyShow.showMovies();
        			return;
        		case 2:
        			break;
        		case 3:
        			break;
        		case 4:
        			break;
        		case 5:
					BookMyShow.initializeExample();
					System.out.println("Done!");
					BookMyShow.showExample();
        			break;
        		case 6:
					BookMyShow.showAllMovies();
        			break;
        		case 7:
					BookMyShow.BookMovie();
        			break;
        		case 8:
					BookMyShow.showAllMoviesTicket();
        			break;
        		case 9:
					String searchString="";
					System.out.print("Enter the movie title: ");
					in.nextLine();
					searchString = in.nextLine();
					BookMyShow.searchMovie(searchString);
        			break;
        		case 10:
					BookMyShow.createShow();
        			break;
        		case 11:
					IDandPasswords idandPasswords = new IDandPasswords();
					LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());
        			break;
        		}
        	}
		} while(option != -1);
    }
}