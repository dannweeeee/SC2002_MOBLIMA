package Moblima;

import java.util.ArrayList;

import java.util.*;
public class movieHandler {
	private ArrayList<Movie> movie;
	
	public movieHandler() {
		this.movie = new ArrayList<>();
	}
	
	public ArrayList<Movie> getMovie(){
		return movie;
	}
	
	public void sortByTicketSales() {
		Collections.sort(movie, new SortbyTicket());
    }
	
	public void sortByRatings() {
		Collections.sort(movie, new SortbyRating());
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
