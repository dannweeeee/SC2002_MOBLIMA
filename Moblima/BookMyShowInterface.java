package Moblima;

import java.io.FileNotFoundException;

public interface BookMyShowInterface {

	void readMovieFromTextFile(String string) throws FileNotFoundException;

	void showAllMovies();

	void searchMovie();

	void showShowTimes();

	void showAllMoviesTicket();

	void createRatingReview();

	void BookMovie();

	void showBookingHist();

	void initializeExample();

	void showExample();
	
	
}
