package Moblima;

import java.util.ArrayList;

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
}
