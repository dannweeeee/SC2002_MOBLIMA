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

import Moblima.DataBase.ExampleAdder;
import Moblima.Entities.Cinema;
import Moblima.Entities.Cineplex;
import Moblima.Entities.Movie;
import Moblima.Entities.Rating;
import Moblima.Entities.Review;

import Moblima.Entities.Show;
import Moblima.Entities.Ticket;
import Moblima.Entities.User;
import Moblima.Exceptions.InvalidInputException;
import Moblima.Handlers.SeatHandler;
import Moblima.Handlers.ShowHandler;
import Moblima.Handlers.UserHandler;
import Moblima.Handlers.BookingController;
import Moblima.Handlers.CinemaHandler;
import Moblima.Handlers.CineplexHandler;
import Moblima.Handlers.MovieHandler;
import Moblima.Utils.UtilityInputs;
import Moblima.Exceptions.InvalidInputException;

public class MovieBooker implements MovieBookerInterface{


	private UserHandler userhandler;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public MovieBooker() {
		userhandler= new UserHandler();
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
		BookingController bookController = new BookingController(userhandler);
		User user1 = UtilityInputs.getUserInformation();
		userhandler.getUsers().add(user1);
		int choice = 0;
		ArrayList<Ticket> tickets = new ArrayList<>();
		while(true){
			BookingController.bookMenu();
			choice = bookController.getMenuChoice();
			if (choice == 4) return;
			ArrayList<Show> shows = bookController.getShowList(choice);
			if (shows == null) {
				continue;
			}
			ArrayList<Ticket> ticket = bookController.bookShow(shows, user1);
			if (ticket == null){
				continue;
			}
			tickets.addAll(ticket);			
		}
	}
	
    public void showSortedMovies() {
		MovieHandler movieHandler = MovieHandler.getInstance();
		int count =1;
		
		System.out.println();
        System.out.println("----------------SHOW MOVIES BY:------------------");
     	System.out.println("| 01: Top 5 in Ticket sales                     |");
     	System.out.println("| 02: Top 5 in Ratings                          |");
     	System.out.println("| 03: Exit                                      |");
        System.out.println("-------------------------------------------------");
        System.out.println();
		System.out.print("Enter Option: ");
        int sortOption = UtilityInputs.getIntUserInput();
        try {
	        switch(sortOption) {
	        case 1:
	        	movieHandler.sortByTicketSales();
	        	for (Movie temp : movieHandler.getMovie()) {
	        		if(count>5) break;
	    			System.out.print(count+": ");
	    			System.out.print(temp);
	    			System.out.println("Ticket Sales: "+temp.getTicketsSize());
	    			System.out.println();
	    			count++;
	    		}
		        break;
	        case 2:
		    	movieHandler.sortByRatings();
		        for (Movie temp : movieHandler.getMovie()) {
		        	if(count>5) break;
		        	System.out.print(count+": ");
					System.out.print(temp);
					System.out.println("Ratings: "+temp.getAverageRatings());
					System.out.println();
					count++;
				}
		        break;
	        case 3:
	        	break;
	        default:
	        	throw new InvalidInputException("Invalid Input, please enter only 1 - 3");
	        }
        }catch(InvalidInputException e){
			System.out.println(e.getMessage());
		}
	}

	public void searchMovie() {
		
		MovieHandler movieHandler = MovieHandler.getInstance();
		String searchString = UtilityInputs.getSearchString();
		ArrayList<Movie> searchResult = movieHandler.searchMovie(searchString);
		
		if (!searchResult.isEmpty()) {
			System.out.println("Showing results for: "+searchString);
			for (Movie temp : searchResult) {
				System.out.println(temp);
			}
		}else {
			System.out.println("No results found for \""+searchString+"\"");
		}

	}
	
	public void showBookingHist() {
		User user_test=null;
		System.out.print("Enter your Email: ");
		String email=UtilityInputs.getStringUserInput();
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
		String email=UtilityInputs.getStringUserInput();
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
				choice=null;
				System.out.println("Which Movie would you like to rate?");
				String name=UtilityInputs.getStringUserInput();
				
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
				choice=null;
				System.out.println("Which Movie would you like to review?");
				String name1=UtilityInputs.getSearchString();
				for (Movie temp : movieHandler.getMovie()) {
					if(temp.getName().contentEquals(name1)) {
						choice=temp;
						break;
					}
				}
				if(choice!=null) {
					System.out.println("Enter your review:");
					String text = UtilityInputs.getStringUserInput();
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

