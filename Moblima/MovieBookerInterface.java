package Moblima;

/**
 * Interface for MovieBookerApp and MovieBooker
 * @author team
 * @version 1.0
 */
public interface MovieBookerInterface {

	/**
	 * Prints all movies 
	 */
	void showAllMovies();

	/**
	 * Search movies and displays them 
	 */
	void searchMovie();
	
	/**
	 * Prints all show times
	 */
	void showShowTimes();
	/**
	 * Sorts the array of movies according to either ticket sales or ratings
	 * and prints the list of top 5 movies according to the user's choice of 
	 * ticket sales or ratings
	 * 
	 */
	void showSortedMovies();
	/**
     * prompts the user for email
     * if user exists then a menu will be displayed for them to rate/review a movie
     * displays a list of movie for users to rate/review
     * adds the rating/review to the respective Movie
     * 
     */
	void createRatingReview();
	/**
	 * Booking Menu to book movie
	 * 
	 */
	void bookingMenu();

	/**
	 * prompts the user for his/her email
	 * searches the user array for his user account
	 * if exists then shows his booking histories for the tickets the user purchased
	 * ticket info includes: name of owner, booking time, seats booked and booked show
	 */
	void showBookingHist();

	/**
	 * Initialize examples
	 * 
	 */
	void addExamples();


	
}
