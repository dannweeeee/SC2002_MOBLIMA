package Moblima;

import java.io.FileNotFoundException;

public interface BookMyShowInterface {
	
	public void showMovies();
	
	public void readMovieFromTextFile(String fileName) throws FileNotFoundException;

	public void initializeExample();

	public void showExample();

	public void showAllMovies();
	
	public void BookMovie();
	
	public void showAllMoviesTicket();

	public void searchMovie(String searchString);
}
