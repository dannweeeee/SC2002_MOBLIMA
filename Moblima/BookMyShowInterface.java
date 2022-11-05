package Moblima;

import java.io.FileNotFoundException;

public interface BookMyShowInterface {
	
	public void readMovieFromTextFile(String fileName) throws FileNotFoundException;

	public void initializeExample();

	public void showExample();

	public void showAllMovies();
	
	public void BookMovie();

	public void showShowTimes();
	
	public void showAllMoviesTicket();

	public void searchMovie(String searchString);

	public void createShow();

	public void writeMovieToTextFile(String fileName);

}
