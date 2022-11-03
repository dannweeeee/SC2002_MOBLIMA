package Moblima;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookMyShow implements BookMyShowInterface{
	
	private movieHandler movieHandler;
	private cineplexHandler cineplexHandler;
	private cinemaHandler cinemaHandler;
	private Cinema cinema;
	private Cineplex cineplex;
	private Scanner in;
	SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
	Scanner scanner = new Scanner(System.in);
	
	public BookMyShow() {
		
		movieHandler = new movieHandler();
		cineplexHandler = new cineplexHandler();
		in = new Scanner(System.in);
	
	}
	
	public void showMovies() {
		
	}
	
	public void initializeExample() {
		cineplexHandler.addCineplex("JurongPoint");
		cineplexHandler.addCineplex("Yishun");
		cinemaHandler JurongPoint = new cinemaHandler("JurongPoint");
		JurongPoint.addCinema("Standard", 30);
		JurongPoint.addCinema("Platinum", 10);
		cineplexHandler.getAllCineplex().get(cineplexHandler.getSize()-1).setHall(JurongPoint);
		JurongPoint.addCinema("VIP", 1);
		
		User ayush = new User("Ayush","ayus@gmail.com",3293131);
        
        Movie ironMan = new Movie("Iron Man","showing","Jon Favreaue","AAA", "Example Cast...", movieHandler);
        Movie avengers = new Movie("Avengers: End Game", "showing","Jon Favreaue","BBB", "Example Cast...", movieHandler);

        String dateInString = "Friday, Jun 7, 2020 09:00:00 AM";
		try {
			Date date = formatter.parse(dateInString);
//			Show show1 = new Show(date,ironMan,cineplexHandler.getAllCineplex().get(1).getHall().get(0));
//			Show show2 = new Show(date,avengers,cineplexHandler.getAllCineplex().get(1).getHall().get(0));
//			Show show3 = new Show(date,avengers,cineplexHandler.getAllCineplex().get(1).getHall().get(1));
//			Show show4 = new Show(date,avengers,cineplexHandler.getAllCineplex().get(1).getHall().get(2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
	}
	
	public void showExample() {
		cineplexHandler.printAllCineplex();
		System.out.println("Show all cinemas:");
		cineplexHandler.getAllCineplex().get(1).printAllCinema();
	}
	
	public void showAllMovies() {
		System.out.println("Show all Movies");
		int count =1;
		for (Movie temp : movieHandler.getMovie()) {
			System.out.print(count+": ");
			temp.printMovieDetails();
			count++;
		}
	}
	
	public void readMovieFromTextFile(String fileName) throws FileNotFoundException{
	    	FileReader movieDatabase = new FileReader(fileName);
	    	Scanner read = new Scanner(movieDatabase);
	    	read.useDelimiter("\\||\\r\\n");
	    	read.nextLine();
	    	String movieName, movieStatus, movieDirector, movieSynopsis, movieCast;
	    	Movie newMovie;
	    	while(read.hasNext()) {
	    		//read.next();
	    		movieName = read.next();
	    		movieStatus = read.next();
	    		movieDirector = read.next();
	    		movieSynopsis = read.next();
	    		movieCast = read.next();
	    		newMovie = new Movie(movieName, movieStatus, movieDirector, movieSynopsis, movieCast, movieHandler);
	    	}
	    	read.close();
    }
	public User getUserInformation(){
		Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Please enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Please enter your number: ");
        double number = scanner.nextDouble();

        scanner.nextLine();
        return new User(name, email, number);
    }
	
	public void BookMovie() {
		int booking_option = 0;
		BookingOptions newBooking = new BookingOptions();
		ArrayList<Ticket> ticketlist;
		User user1 = getUserInformation();
		do{
			System.out.println("--------------MOBLIMA BOOKING MENU!--------------");
			System.out.println("| 01: List All Movies                           |");
			System.out.println("| 02: Search Movies by Name                     |");
			System.out.println("| 03: View Movie by Location                    |");
			System.out.println("| 04: Go Back                                   |");
			System.out.println("-------------------------------------------------");
			System.out.print("Enter option ('-1' to exit):");

			booking_option = in.nextInt();
			switch(booking_option){
				case 1:
					ticketlist = newBooking.listAllMovies(cineplexHandler.getAllCineplex(), user1);
					break;
				case 2:
					ticketlist = newBooking.searchMoviebyName(cineplexHandler.getAllCineplex(), user1);
					break;
				case 3:
					ticketlist = newBooking.searchMovieByLocation(cineplexHandler.getAllCineplex(), user1);
					break;
				case 4:
					break;
				default:
					System.out.println("Invalid Input");
			}
		} while(booking_option != 4);
		System.out.println("You are now back at the main menu.");
	}
	public void showAllMoviesTicket() {
		System.out.println("Sort Movies by: 1.Ticket sales, 2.Ratings");
		int sort = scanner.nextInt();
        scanner.nextLine();
        if(sort==1) {
        	movieHandler.SortbyTicketSales();
        }
        else {movieHandler.SortbyRatings();}
		int count =1;
		for (Movie temp : movieHandler.getMovie()) {
			System.out.print(count+": ");
			temp.printMovieDetails();
			count++;
		}
	}
	
	public void searchMovie(String searchString) {
		System.out.println("Showing results for: "+searchString);
		for (Movie temp : movieHandler.getMovie()) {
			if(temp.getName().contentEquals(searchString)) {
				temp.printFullMovieDetails();
				System.out.print("Average Movie Rating: ");
				System.out.println(temp.getAverageRatings());
				System.out.println("Most Recent Reviews:");
				System.out.println(temp.getReview());
			}
		}
	}

	public void createShow() {
		int movieOption = -1, cineplexOption =-1, cinemaOption =-1;
		String dateInString;
		Date showtime = null;
		
		showAllMovies();
		System.out.print("Select Movie to create show:");
		try{
			movieOption = in.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input. Please re-enter.");
			in.next();
		}
		Movie selectedMovie = movieHandler.getMovie().get(movieOption-1);
		
		System.out.print("Enter date and showtime (E.g. Friday, Jun 7, 2020 09:00:00 AM): ");
		in.nextLine();
		dateInString = in.nextLine();
		try {
			showtime = formatter.parse(dateInString);
			System.out.println(showtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		cineplexHandler.printAllCineplex();
		System.out.print("Select Cineplex: ");
		try{
			cineplexOption = in.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input. Please re-enter.");
			in.next();
		}
		
		cineplexHandler.getAllCineplex().get(cineplexOption-1).printAllCinema();
		System.out.print("Select Cinema: ");
		try{
			cinemaOption = in.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input. Please re-enter.");
			in.next();
		}
		
		Show show1 = new Show(showtime,selectedMovie,cineplexHandler.getAllCineplex().get(cineplexOption-1).getHall().get(cinemaOption-1));
		System.out.println(show1);
	}
}
