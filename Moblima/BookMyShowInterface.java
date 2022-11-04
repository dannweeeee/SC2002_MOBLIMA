package Moblima;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface BookMyShowInterface {
	
	public void showMovies();
	
	public void readMovieFromTextFile(String fileName) throws FileNotFoundException;

	public void initializeExample();

	public void showExample();

	public void showAllMovies();
	
	public ArrayList<Ticket> BookMovie(User user1, Booking newBooking);
	
	public void showAllMoviesTicket();

	public void searchMovie(String searchString);

	public void createShow();

	public User getUserInformation();
}
