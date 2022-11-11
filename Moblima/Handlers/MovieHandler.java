package Moblima.Handlers;

import java.util.ArrayList;

import Moblima.Comparator.SortbyRating;
import Moblima.Comparator.SortbyTicket;
import Moblima.Entities.Movie;

import java.util.*;
public class MovieHandler {
	private ArrayList<Movie> movie;
	private static MovieHandler instance = null;
	
    public static MovieHandler getInstance() {
        if (instance == null) {
            instance = new MovieHandler();
        }
        return instance;
    }
	
	public MovieHandler() {
		this.movie = new ArrayList<>();
	}
	
	public ArrayList<Movie> getMovie(){
		return movie;
	}

	public int sizeMovie(){
		return movie.size();
	}
	
	public void sortByTicketSales() {
		Collections.sort(movie, new SortbyTicket().reversed());
    }
	
	public void sortByRatings() {
		Collections.sort(movie, new SortbyRating().reversed());
    }
	public void addMovie(Movie m){
		movie.add(m);
	}
	public void printAllMoviesNames() {
		int count =0;
		for (Movie temp : movie) {
			System.out.print(count+": ");
			temp.getName();
			count++;
		}
	}
	
	public ArrayList<Movie> searchMovie(String searchString) {
		ArrayList<Movie> results = new ArrayList<>();
		for (Movie temp : this.getMovie()) {
			if(temp.getName().toLowerCase().contains(searchString)) {
				results.add(temp);
			}
		}
		return results;
	}

	public Movie createMovie(String movieName, String movieStatus, String movieDirector, String movieSynopsis, String movieCaString){

		Movie createMovie = new Movie(movieName, movieStatus, movieDirector, movieSynopsis, movieCaString);
		movie.add(createMovie);
		return createMovie;
	}

	public void removeMovie(int movieID){
		ArrayList<Movie> allMovie = getMovie();
		allMovie.remove(movieID);
	}
	
}