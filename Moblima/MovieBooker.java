/**
 * 
 * @author Our team
 * @version 1.0
 */
package Moblima;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import Moblima.DataBase.ExampleAdder;
import Moblima.Entities.Movie;
import Moblima.Entities.Rating;
import Moblima.Entities.Review;

import Moblima.Entities.Show;
import Moblima.Entities.Ticket;
import Moblima.Entities.User;
import Moblima.Exceptions.InvalidInputException;
import Moblima.Handlers.ShowHandler;
import Moblima.Handlers.UserHandler;
import Moblima.Handlers.BookingController;
import Moblima.Handlers.MovieHandler;
import Moblima.Utils.UtilityInputs;
import Moblima.Utils.UtilityOutput;

public class MovieBooker implements MovieBookerInterface{

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public MovieBooker() {}
	
	public void showShowTimes(){
		ShowHandler showHandler = ShowHandler.getInstance();
		ShowHandler.printAllShows(showHandler.getAllShows());
	}

	public void showAllMovies() {
		MovieHandler movieHandler = MovieHandler.getInstance();
		UtilityOutput.printMessage("Showing all movies...");
		UtilityOutput.printObjectList(movieHandler.getMovie());
	}
	
	public void bookingMenu() {
		UserHandler userhandler=UserHandler.getInstance();
		BookingController bookController = new BookingController();
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
	/**
	 * Sorts the array of movies according to either ticket sales or ratings
	 * and prints the list of top 5 movies according to the user's choice of 
	 * ticket sales or ratings
	 * 
	 */
	public void showSortedMovies() {
		MovieHandler movieHandler = MovieHandler.getInstance();
		int count =1;
		
		String[] menu = {"", 
			"----------------SHOW MOVIES BY:------------------",
			"| 01: Top 5 in Ticket sales                     |",
			"| 02: Top 5 in Ratings                          |",
			"| 03: Exit                                      |",
			"-------------------------------------------------",
			""
		};
		UtilityOutput.printMenu(menu);
		UtilityOutput.printMessage("Enter Option: ");

        int sortOption = UtilityInputs.getIntUserInput();
        try {
	        switch(sortOption) {
	        case 1:
	        	count=1;
	        	movieHandler.sortByTicketSales();
	        	for (Movie temp : movieHandler.getMovie()) {
	        		if(count>6) break;
						UtilityOutput.printMessage(Integer.toString(count) + ": " + temp.toString());
						UtilityOutput.printMessage("Ticket Sales: " + Integer.toString(temp.getTicketsSize()));
						UtilityOutput.printMessage("");
						count++;
	    		}
		        break;
	        case 2:
	        	count=1;
		    	movieHandler.sortByRatings();
		        for (Movie temp : movieHandler.getMovie()) {
		        	if(count>6) break;
		        	UtilityOutput.printMessage(count+": "+temp.toString());
					UtilityOutput.printMessage("Ratings: "+temp.getAverageRatings());
					UtilityOutput.printMessage("");
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
		UtilityOutput.printInputMessage("Enter movie name to search [0 to exit] => ");
		String searchString = UtilityInputs.getStringUserInput().toLowerCase();
		ArrayList<Movie> searchResult = movieHandler.searchMovie(searchString);
		
		if (!searchResult.isEmpty()) {
			UtilityOutput.printMessage("Showing results for: "+searchString);
			for (Movie temp : searchResult) {
				System.out.println(temp);
			}
		}else {
			UtilityOutput.printMessage("No results found for \""+searchString+"\"");
		}

	}
	/**
	 * prompts the user for his/her email
	 * searches the user array for his user account
	 * if exists then shows his booking histories for the tickets the user purchased
	 * ticket info includes: name of owner, booking time, seats booked and booked show
	 */
	
	
	public void showBookingHist() {
		UserHandler userhandler= UserHandler.getInstance();
		User user_test=null;
		UtilityOutput.printInputMessage("Enter your Email: ");
		String email = UtilityInputs.getStringUserInput();
		for(User temp: userhandler.getUsers()) {
			if(temp.getEmail().contentEquals(email)) {
				user_test=temp;}
		}
		if(user_test==null){
			UtilityOutput.printInputMessage("User doesn't exist");
			return;
		}
		for (Ticket temp : user_test.getTickets()) {
			UtilityOutput.printMessage(temp.toString());
		}
	}
    /**
     * prompts the user for email
     * if user exists then a menu will be displayed for them to rate/review a movie
     * displays a list of movie for users to rate/review
     * adds the rating/review to the respective Movie
     * 
     */
	public void createRatingReview() {
		MovieHandler movieHandler = MovieHandler.getInstance();
		UserHandler userhandler= UserHandler.getInstance();
		int option=0;
		User useri=null;
		Movie choice=null;
		Double score;
		UtilityOutput.printInputMessage("Enter your Email: ");
		String email=UtilityInputs.getStringUserInput();
		for(User temp: userhandler.getUsers()) {
			if(temp.getEmail().contentEquals(email)) {
				 useri=temp;}
		}
		if(useri==null){
			UtilityOutput.printInputMessage("User doesn't exist");
			return;
		}
		do {
			String[] menu = {"",
			"----------------REVIEW/RATING MENU---------------",
			"| 01: Rate a Movie                              |",
			"| 02: Review a Movie                            |",
			"| 03: Exit                                      |",
			"-------------------------------------------------"};
            UtilityOutput.printMenu(menu);
            UtilityOutput.printInputMessage("Enter option ('3' to return): ");
            option = UtilityInputs.getIntUserInput();
	        
	        switch(option){
			case 1:
				choice=null;
				
				showAllMovies();
				UtilityOutput.printInputMessage("Which Movie would you like to rate? ");
				String name = UtilityInputs.getStringUserInput();
				
				for (Movie temp : movieHandler.getMovie()) {
					if(temp.getName().toLowerCase().contentEquals(name.toLowerCase())) {
						choice=temp;
						break;
					}
				}
				if(choice!=null) {
					
					while(true) {
						UtilityOutput.printInputMessage("Enter your rating from 1 to 5: ");
						score = UtilityInputs.getDoubleUserInput();
						if(score==null)continue;
						else if(score>=1 && score<=5)break;
						else if((score<1 || score>5)) UtilityOutput.printMessage("Rating out of range");
					}
					choice.addRatings(new Rating(score,useri));
					UtilityOutput.printMessage("Rating added");}
				else UtilityOutput.printMessage("Movie does not exist");
					break;
				
			case 2:
				choice=null;
				showAllMovies();
				UtilityOutput.printInputMessage("Which Movie would you like to review? ");
				String name1 = UtilityInputs.getStringUserInput();
				for (Movie temp : movieHandler.getMovie()) {
					if(temp.getName().toLowerCase().contentEquals(name1.toLowerCase())) {
						choice=temp;
						break;
					}
				}
				if(choice!=null) {
					while(true) {
						UtilityOutput.printInputMessage("Enter your review: ");
						String text = UtilityInputs.getStringUserInput();
						if(!text.matches("[0-9]+")) {
							choice.addReview(new Review(text,useri));
							UtilityOutput.printMessage("Review added");
						break;}
						else {UtilityOutput.printMessage("Invalid Input");
						}
					}
				}	
				else UtilityOutput.printMessage("Movie does not exist");
					break;
				
			case 3:
				break;
			default:
				UtilityOutput.printMessage("Invalid Input");
	        }
		}while(option!=3);
	}
  
  	public void addExamples() {
  		ExampleAdder.initializeExample();
  		UtilityOutput.printMessage("Example data added.");
  		ExampleAdder.showExample();
  	}
	
}


