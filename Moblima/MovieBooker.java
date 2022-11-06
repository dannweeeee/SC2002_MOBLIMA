package Moblima;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.print.event.PrintEvent;

import Moblima.DataBase.ExampleAdder;
import Moblima.Entities.Booking;
import Moblima.Entities.Cinema;
import Moblima.Entities.Cineplex;
import Moblima.Entities.Movie;
import Moblima.Entities.Rating;
import Moblima.Entities.Review;
import Moblima.Entities.Seats;
import Moblima.Entities.Show;
import Moblima.Entities.Ticket;
import Moblima.Entities.User;
import Moblima.Handlers.SeatHandler;
import Moblima.Handlers.ShowHandler;
import Moblima.Handlers.UserHandler;
import Moblima.Handlers.CinemaHandler;
import Moblima.Handlers.CineplexHandler;
import Moblima.Handlers.MovieHandler;
import Moblima.Utils.Settings;
import Moblima.Utils.UtilityInputs;

public class MovieBooker implements MovieBookerInterface{
	
	
	private CinemaHandler CinemaHandler;
	private Cinema cinema;
	private Cineplex cineplex;
	private Scanner in;
	
	private UserHandler userhandler;
	SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");


	
	public MovieBooker() {
		userhandler= new UserHandler();
		in = new Scanner(System.in);
	}
	
	public void showShowTimes(){
		ShowHandler showHandler = ShowHandler.getInstance();
		ShowHandler.printAllShows(showHandler.getAllShows());
	}

	public void showAllMovies() {
		MovieHandler movieHandler = MovieHandler.getInstance();
		System.out.println("Showing all movies...");
		int count =1;
		for (Movie temp : movieHandler.getMovie()) {
			System.out.print(count+": ");
			System.out.println(temp);
			count++;
		}
	}
	
	public void bookingMenu() {
		
	}
	
    public void showAllMoviesTicket() {

		MovieHandler movieHandler = MovieHandler.getInstance();
		int count =1;
		System.out.println("Sort Movies by: \n1.Ticket sales \n2.Ratings");
		System.out.print("Enter Option: ");
        int sortOption = UtilityInputs.getIntUserInput();
        
        if(sortOption==1) {
        	movieHandler.sortByTicketSales();
        	for (Movie temp : movieHandler.getMovie()) {
        		if(count>5) break;
    			System.out.print(count+": ");
    			System.out.print(temp);
    			System.out.println("Ticket Sales: "+temp.getTicketsSize());
    			System.out.println();
    			count++;
    		}
        }
        else {
        	movieHandler.sortByRatings();
	        for (Movie temp : movieHandler.getMovie()) {
	        	if(count>5) break;
	        	System.out.print(count+": ");
				System.out.print(temp);
				System.out.println("Ratings: "+temp.getAverageRatings());
				System.out.println();
				count++;
			}
        }
	}

	public void searchMovie() {
		MovieHandler movieHandler = MovieHandler.getInstance();
		String searchString = UtilityInputs.getSearchString();
		
		System.out.println("Showing results for: "+searchString);
		ArrayList<Movie> searchResult = movieHandler.searchMovie(searchString);
		if (searchResult != null) {
			for (Movie temp : searchResult) {
				System.out.println(temp);
			}
		}else {
			System.out.println("No results found for \""+searchString+"\"");
		}

	}

	// add this to showhandler
	// move to admin module
	public void createShow() {
		CineplexHandler cineplexHandler = CineplexHandler.getInstance();
		MovieHandler movieHandler = MovieHandler.getInstance();
		ShowHandler showHandler = ShowHandler.getInstance();
		SeatHandler seatHandler = SeatHandler.getInstance();
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
		
		Show show1 = showHandler.addShows(showtime,selectedMovie,cineplexHandler.getAllCineplex().get(cineplexOption-1).getHall().get(cinemaOption-1), seatHandler);
		System.out.println(show1);
	}
	
	// move to admin module
	public void createMovie(String fileName) throws IndexOutOfBoundsException {
		MovieHandler movieHandler = MovieHandler.getInstance();
        String movieAddName, movieAddStatus, movieAddDirector, movieAddSynopsis, movieAddCasts;
        System.out.print("Enter full name of Movie: ");
        movieAddName = UtilityInputs.getSearchString();
        System.out.print("Enter status of Movie (Coming Soon, Now Showing): ");
        movieAddStatus = UtilityInputs.getSearchString();
        System.out.print("Enter director of Movie: ");
        movieAddDirector = UtilityInputs.getSearchString();
        System.out.print("Enter synopsis of Movie: ");
        movieAddSynopsis = UtilityInputs.getSearchString();
        System.out.print("Enter casts of Movie (e.g. Steve Rogers, Borat, Mr Bean): ");
        movieAddCasts = UtilityInputs.getSearchString();
        Movie addNewMovie = movieHandler.createMovie(movieAddName, movieAddStatus, movieAddDirector, movieAddSynopsis, movieAddCasts);
    }
	// move to admin module
	public void updateMovie(String fileName){
		MovieHandler movieHandler = MovieHandler.getInstance();
		int movieOption = 0, moviePartOption = 0;
		String movieName, movieStatus, movieDirector, movieSynopsis, movieCasts;
		String movieUpdateName, movieUpdateStatus, movieUpdateDirector, movieUpdateSynopsis, movieUpdateCasts;
		
		showAllMovies();
		do{
			System.out.print("Choose digit of Movie to update (Enter '-1' to go back): ");
			try{
				in = new Scanner(System.in);
				movieOption = in.nextInt();
				if (movieOption > 0 && movieOption < movieHandler.sizeMovie()){
					break;
				}
				else if (movieOption<0){
					movieOption = in.nextInt();
					throw new IllegalArgumentException("Input out of bounds. Please re-enter. ");
				}
				else{
					System.out.println("Input out of bounds. Please re-enter. ");
				}
			}catch(IllegalArgumentException iae){
				System.out.println(iae.getMessage());
				in.next();
			}catch(Exception e) {
				System.out.println("Invalid Input. Please re-enter. ");
				in.next();
			}
		} while(movieOption != -1);

		Movie selectedMovie = movieHandler.getMovie().get(movieOption-1);
		movieName = selectedMovie.getName();
		movieStatus = selectedMovie.getStatus();
		movieDirector = selectedMovie.getDirector();
		movieSynopsis = selectedMovie.getSynopsis();
		movieCasts = selectedMovie.getCast();

		System.out.println("1.Title: " + movieName);
		System.out.println("2.Status: " + movieStatus);
		System.out.println("3.Director: " + movieDirector);
		System.out.println("4.Cast: " + movieCasts);
		System.out.println("5.Synopsis: " + movieSynopsis);

		do{
			System.out.println("Select which to update (Enter '-1' to confirm & exit): ");
			try {
				moviePartOption = in.nextInt();
			}catch(Exception e) {
				System.out.println("Invalid Input. Please re-enter.");
				in.next();
				continue;
			}
			switch(moviePartOption){
				case 1:
					System.out.print("Update full name of Movie: ");
					in = new Scanner(System.in);
					movieUpdateName = in.nextLine();
					selectedMovie.setName(movieUpdateName);
					break;
				case 2:
					System.out.print("Update status of Movie (Coming Soon, Now Showing): ");
					in = new Scanner(System.in);
					movieUpdateStatus = in.nextLine();
					selectedMovie.setStatus(movieUpdateStatus);
					break;
				case 3:
					System.out.print("Update director of Movie: ");
					in = new Scanner(System.in);
					movieUpdateDirector = in.nextLine();
					selectedMovie.setDirector(movieUpdateDirector);
					break;
				case 4:
					System.out.print("Update casts of Movie (e.g. Steve Rogers, Borat, Mr Bean): ");
					in = new Scanner(System.in);
					movieUpdateCasts = in.nextLine();
					selectedMovie.setCast(movieUpdateCasts);
					break;
				case 5:
					System.out.print("Update synopsis of movie: ");
					in = new Scanner(System.in);
					movieUpdateSynopsis = in.nextLine();
					selectedMovie.setSynopsis(movieUpdateSynopsis);
					break;
				default:
					System.out.println("Invalid Input");
			}
		} while(moviePartOption != -1);
		System.out.println(selectedMovie);
	}
	// move to admin module
	public void removeMovie(String fileName) throws IndexOutOfBoundsException {
		MovieHandler movieHandler = MovieHandler.getInstance();
		int movieRemoveOption = 0;
		showAllMovies();
		System.out.print("Which Movie would you like to update? (e.g. 1): ");
		try{
			movieRemoveOption = in.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input. Please re-enter.");
			in.next();
		}
		Movie selectedMovie = movieHandler.getMovie().get(movieRemoveOption-1);
		System.out.println("The following movie has been deleted!");
		System.out.println(selectedMovie);
		movieHandler.removeMovie(movieRemoveOption-1);
	}
	// move to admin module
	public void updateShowTime(){
		ShowHandler showHandler = ShowHandler.getInstance();
		Scanner newScanner = new Scanner(System.in);

		while(true){
			ShowHandler.printAllShows(showHandler.getAllShows());
			System.out.println("Enter which show time you want to edit [-1 to exit] ");
			int choice = UtilityInputs.getIntUserInput();
			if (choice == -1) break;
			Show selectedShow = ShowHandler.getShowByID(showHandler.getAllShows(), choice);
			System.out.println("You have selected\n" + selectedShow.toString());
			System.out.println("Enter new show time for this movie [Format: Monday, Jun 10, 2022 12:00:00 AM] =>  ");
			String dateInString = newScanner.next();
			try{
				Date date = formatter.parse(dateInString);
				selectedShow.setShowTime(date);
			} catch(ParseException e){
				System.out.println("Invalid date input");
				continue;
			}
		} //end of while	
	}
	
	public void showBookingHist() {
		User user_test=null;
		System.out.print("Enter your Email: ");
		String email=in.nextLine();
		for(User temp: userhandler.getUsers()) {
			if(temp.getEmail().contentEquals(email)) {
				user_test=temp;}
		}
		if(user_test==null){
			System.out.print("User doesn't exist");
			return;
		}
		for (Ticket temp : user_test.getTickets()) {
			System.out.println( "Ticket{" +
	                " owner='" +  user_test.getName() +
	                ", bookingTime=" + temp.getBookingTime() +
	                ", Seats booked=" + temp.getSeat().getSeat() +
	                ", bookedShow=" + temp.getBookedShow()+
	                '}');
		}
	}
  
	public void createRatingReview() {
		MovieHandler movieHandler = MovieHandler.getInstance();
		int option=0;
		User useri=null;
		Movie choice=null;
		System.out.print("Enter your Email: ");
		String email=in.nextLine();
		for(User temp: userhandler.getUsers()) {
			if(temp.getEmail().contentEquals(email)) {
				 useri=temp;}
		}
		if(useri==null){
			System.out.print("User doesn't exist");
			return;
		}
		do {
			
            System.out.println();
            System.out.println("----------------REVIEW/RATING MENU---------------");
        	System.out.println("| 01: Rate a Movie                              |");
        	System.out.println("| 02: Review a Movie                            |");
        	System.out.println("| 03: Exit                                      |");
            System.out.println("-------------------------------------------------");
            System.out.println();
            
            option = UtilityInputs.getIntUserInput();
	        
	        switch(option){
			case 1:
				System.out.println("Which Movie would you like to rate?");
				String name=in.nextLine();
				
				for (Movie temp : movieHandler.getMovie()) {
					if(temp.getName().contentEquals(name)) {
						choice=temp;
						break;
					}
				}
				if(choice!=null) {
					System.out.println("Enter your rating from 1 to 5:");
					Double score = UtilityInputs.getDoubleUserInput();
					choice.addRatings(new Rating(score,useri));
					System.out.println("Rating added");}
					else System.out.println("Movie does not exist");
					break;
				
			case 2:
				System.out.println("Which Movie would you like to review?");
				String name1=in.nextLine();
				for (Movie temp : movieHandler.getMovie()) {
					if(temp.getName().contentEquals(name1)) {
						choice=temp;
						break;
					}
				}
				if(choice!=null) {
					System.out.println("Enter your review:");
					String text = in.nextLine();
					choice.addReview(new Review(text,useri));
					System.out.println("Review added");}
					else System.out.println("Movie does not exist");
					break;
				
			case 3:
				break;
			default:
				System.out.println("Invalid Input");
	        }
		}while(option!=3);
	}
  
  	public void addExamples() {
  		ExampleAdder.initializeExample();
  		System.out.println("Example data added.");
  		ExampleAdder.showExample();
  	}
	
}

