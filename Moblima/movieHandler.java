package Moblima;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.*;
public class movieHandler {
	private ArrayList<Movie> movie;
	
	public movieHandler() {
		this.movie = new ArrayList<>();
		
	}
	
//	public void setMovie() {
//		this.movie = movie;
//	}
	
	public ArrayList<Movie> getMovie(){
		return movie;
	}
	public void SortbyTicketSales() {
		Collections.sort(movie, new SortbyTicket());
        }
	public void SortbyRatings() {
		Collections.sort(movie, new SortbyRating());
        }
	public void PrintMovies() {
		int count =0;
		for (Movie temp : movie) {
			System.out.print(count+": ");
			temp.getName();
			count++;
		}
	}
	
}
