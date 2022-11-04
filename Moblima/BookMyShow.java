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
	private SeatHandler seatHandler;
	private ShowHandler showHandler;
	private UserHandler userhandler;
	SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
	Scanner scanner = new Scanner(System.in);
	
	public BookMyShow() {
		this.movieHandler = new movieHandler();
		this.cineplexHandler = new cineplexHandler();
		this.showHandler = new ShowHandler();
		this.seatHandler = new SeatHandler();
		this.userhandler = new UserHandler();
		in = new Scanner(System.in);
	}
	
	public void showMovies() {}
	
	public void initializeExample() {
		Cineplex jurong = cineplexHandler.addCineplex("JurongPoint");
		cinemaHandler JurongPoint = new cinemaHandler("JurongPoint");
		JurongPoint.addCinema("Standard", 35, jurong);
		JurongPoint.addCinema("Platinum", 10, jurong);
		cineplexHandler.getAllCineplex().get(cineplexHandler.getSize()-1).setHall(JurongPoint);
		JurongPoint.addCinema("VIP", 1, jurong);
		
		User ayush = new User("Ayush","ayus@gmail.com",3293131);
        
        //Movie ironMan = new Movie("Iron Man","showing","Jon Favreaue", movieHandler);
        //Movie avengers = new Movie("Avengers: End Game", "showing","Jon Favreaue", movieHandler);

		Movie ironMan = new Movie("Iron Man","showing","Jon Favreaue","AAA", "Example Cast...", movieHandler);
        Movie avengers = new Movie("Avengers: End Game", "showing","Jon Favreaue","BBB", "Example Cast...", movieHandler);
        String dateInString = "Friday, Jun 7, 2020 09:00:00 AM";
		try {
			Date date = formatter.parse(dateInString);
			Show show1 = showHandler.addShows(date, ironMan, cineplexHandler.getAllCineplex().get(0).getHall().get(0), seatHandler);
			Show show2 = showHandler.addShows(date,avengers,cineplexHandler.getAllCineplex().get(0).getHall().get(0), seatHandler);
			Show show3 = showHandler.addShows(date,avengers,cineplexHandler.getAllCineplex().get(0).getHall().get(1), seatHandler);
			Show show4 = showHandler.addShows(date,avengers,cineplexHandler.getAllCineplex().get(0).getHall().get(2), seatHandler);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
	}
	
	public void showExample() {
		cineplexHandler.printAllCineplex();
		System.out.println("Show all cinemas:");
		cineplexHandler.getAllCineplex().get(0).printAllCinema();
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
        System.out.print("Please enter your name: ");
        String name = in.nextLine();

        System.out.print("Please enter your email: ");
        String email = in.nextLine();

        System.out.print("Please enter your number: ");
        double number = in.nextDouble();

        return new User(name, email, number);
    }
	
	public void BookMovie() {
		int booking_option = 0;
		Booking newBooking = new Booking();
		ArrayList<Show> allShows = null;
		ArrayList<Ticket> tickets = null;
		User user1 = getUserInformation();
		userhandler.getUsers().add(user1);
		do{
			System.out.println("--------------MOBLIMA BOOKING MENU!--------------");
			System.out.println("| 01: List All Shows                            |");
			System.out.println("| 02: Search Shows by Name                      |");
			System.out.println("| 03: View Movie by Location                    |");
			System.out.println("| 04: Go Back                                   |");
			System.out.println("-------------------------------------------------");
			System.out.print("Enter option ('4' to return):");

			booking_option = in.nextInt();
			switch(booking_option){
				case 1:
					allShows = showHandler.getAllShows();
					tickets = book(allShows, newBooking, user1);
					break;
				case 2:
					System.out.print("Enter movie name to search [0 to exit] => ");
					Scanner newScanner = new Scanner(System.in);
					String searchString = newScanner.nextLine();
					searchString = searchString.toLowerCase();
					allShows = showHandler.searchShows(searchString);
					tickets = book(allShows, newBooking, user1);
					break;
				case 3:
					printAllLocation();
        			System.out.print("Enter Cineplex to book [0 to exit] => ");
					in.nextLine();
        			String userInput = in.nextLine();
					try{
						int choice = Integer.parseInt(userInput);
						allShows = showHandler.getAllShows();
						tickets = book(allShows, newBooking, user1);
					} catch (NumberFormatException e){
						System.out.println("Invalid input: " + userInput + " is not a number");
            			System.out.println("");
						break;
					}
					break;
				case 4:
					break;
				default:
					System.out.println("Invalid Input");
			}
		} while(booking_option != 4);
	}

	public ArrayList<Ticket> book(ArrayList<Show> allShows, Booking newBooking, User user1){
		if (allShows != null){
			if (allShows.size() >= 1){
				ShowHandler.printAllShows(allShows);
				Show showSelected = newBooking.selectShow(allShows);
				if (showSelected != null) return newBooking.bookSeats(showSelected, user1, seatHandler);
			}else {
				System.out.println("No shows found");
			}
		}
		return null;
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

	public void printAllLocation(){
        System.out.println("All Locations: ");
        for (int i = 0; i < cineplexHandler.getSize(); i++){
            Cineplex temp = cineplexHandler.getAllCineplex().get(i);
            System.out.println(Integer.toString(i+1) + ". " + temp.getLocation());
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
	public void showBookingHist() {
		System.out.println("Please enter your name to check your Booking history");
		for (Ticket temp : userhandler.getUsers().get(0).getHist()) {
			System.out.println( "Ticket{" +
	                "owner='" +  userhandler.getUsers().get(0).getName() +
	                ", bookingTime=" + temp.getBookingTime() +
	                ", Seats booked=" + temp.getSeat().getSeat() +
	                ", bookedShow=" + temp.getBookedShow()+
	                '}');
		}
	}
}
