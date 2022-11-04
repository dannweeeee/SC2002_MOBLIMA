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
			System.out.println();
        	System.out.println("----------------MOBLIMA MAIN MENU-----------------");
        	System.out.println("| 01: Admin Login                                |");
        	System.out.println("| 02: Movie Goer                                 |");
        	System.out.println("| -1: Exit                                       |");
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
					IDandPasswords idandPasswords = new IDandPasswords();
					LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());
					return;
        		case 2:
					MovieGoer movieGoer = new MovieGoer();
					return;
        		}
        	}
		} while(option != -1);
		
	}
}
