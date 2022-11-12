package Moblima.Handlers;

import java.util.ArrayList;

import Moblima.Comparator.SortbyRating;
import Moblima.Comparator.SortbyTicket;
import Moblima.Entities.Movie;
import Moblima.Entities.Movie.MovieStatus;
import Moblima.Entities.Movie.MovieType;
import Moblima.Utils.UtilityOutput;


import java.util.*;
/**
 * Movie Handler class to handle all Movies.
 * @author team
 * @version 1.0
 */
public class MovieHandler {
	private ArrayList<Movie> movie;
	private static MovieHandler instance = null;
	
	/**
	 * Get and set Instance of MovieHandler.
	 * @return movieHandler
	 */
    public static MovieHandler getInstance() {
        if (instance == null) {
            instance = new MovieHandler();
        }
        return instance;
    }
	
    /**
     * Default constructor for Movie Handler.
     */
	public MovieHandler() {
		this.movie = new ArrayList<>();
	}
	
	/**
	 * Get a list of movies.
	 * @return movie A list of Movies
	 */
	public ArrayList<Movie> getMovie(){
		return movie;
	}

	/**
	 * Get count of movies.
	 * @return int count of all movies in the MovieHandler
	 */
	public int sizeMovie(){
		return movie.size();
	}
	
	/**
	 * Calls sort by ticket function to show movies in a sorted view.
	 */
	public void sortByTicketSales() {
		Collections.sort(movie, new SortbyTicket().reversed());
    }
	
	/**
	 * Call sort by rating function to show movies in a sorted view.
	 */
	public void sortByRatings() {
		Collections.sort(movie, new SortbyRating().reversed());
    }
	
	/**
	 * Add a Movie to the list of Movies
	 * @param m the Movie object to be added into the list of movies
	 */
	public void addMovie(Movie m){
		movie.add(m);
	}
	
	/**
	 * Iterate list of movies and print the movie names
	 */
	public void printAllMoviesNames() {
		int count =0;
		for (Movie temp : movie) {
			System.out.print(count+": ");
			temp.getName();
			count++;
		}
	}

	public static Movie getMovieByID(ArrayList<Movie> movies, int choice){
		for(Movie m : movies){
			if (m.getId() == choice){
				return m;
			}
		}
		return null;
	}
	
	/**
	 * Return a new list of movies (subset of main list of movies) that matches the search string
	 * @param searchString string of movie name to search for
	 * @return results A list of movies with titles that matches the searchString
	 */
	public ArrayList<Movie> searchMovie(String searchString) {
		ArrayList<Movie> results = new ArrayList<>();
		for (Movie temp : this.getMovie()) {
			if(temp.getName().toLowerCase().contains(searchString)) {
				results.add(temp);
			}
		}
		return results;
	}

	/**
	 * Create a Movie object and add it into the list of movies
	 * @param movieName Title of the movie
	 * @param movieStatus Status of the movie (an enum, COMING_SOON, etc...)
	 * @param movieDirector Director of the movie 
	 * @param movieSynopsis Sypnosis of the movie
	 * @param movieCaString All casts of the movie
	 * @return Object of the new movie
	 */
	public Movie createMovie(String movieName,MovieType movieType, MovieStatus movieStatus, String movieDirector, String movieSynopsis, String movieCaString){

		Movie createMovie = new Movie(movieName, movieType, movieStatus, movieDirector, movieSynopsis, movieCaString);
		movie.add(createMovie);
		return createMovie;
	}

	/**
	 * Remove the movie from the list of movies
	 * @param movieID Get the identifier for the the movie 
	 */
	public void removeMovie(int movieID){
		ArrayList <Movie> movieList = MovieHandler.getInstance().getMovie();
		Iterator<Movie> itr = movieList.iterator(); 
		while (itr.hasNext()) { 
			Movie movie = itr.next();
			if(movie.getId() == movieID){
				itr.remove();
				UtilityOutput.printMessage("DELETED! The Movie has been deleted!");
				return;
			}
		}
		UtilityOutput.printMessage("Invalid input. Please try again from Main Menu.");
	}
}