package Moblima;

import java.util.ArrayList;

public class Movie  {
	 	private String name;
	    private String status;
	    private String synopsis;
	    private String director;
	    private String cast;
	    private String review;
	    private ArrayList<Rating> ratings;
	    private ArrayList<Review> reviews;
	    private movieHandler movieH;
	    private double Ratings;
	    public ArrayList<Ticket> ticketlist;
	    
	  

	    public Movie(String name,String status, String director, movieHandler movieH) {
	        this.name = name;
	        this.status = status;
	        this.director = director;
	        this.ratings = new ArrayList<>();
	        this.reviews = new ArrayList<>();
	        this.ticketlist=new ArrayList<>();
	        movieH.getMovie().add(this);
	    }
	    public void setSynopsis(String synopsis) {
	    	this.synopsis=synopsis;
	    }
	    public void setCast(String cast) {
	    	this.cast=cast;
	    }

	    public String getName() {
	        return name;
	    }
	    
	    public void printMovieDetails() {
	    	System.out.print(name+", ");
	    	System.out.print(director+", ");
	    	System.out.println(status);
	    }

	    public void addRatings(Rating R) {
	        this.ratings.add(R);
	    }
	    public void addReview(Review R) {
	        this.reviews.add(R);
	    }
	    
	
	    public ArrayList<Review> getReview(){
	        return reviews;
	    }
	    public void addticket(Ticket ticket) {
	    	ticketlist.add(ticket);
	    }
	    public int getTicketsNo() {
	    	return ticketlist.size();
	    }
	    public double getAverageRatings() {
	    	Ratings=0;
	    	if(ratings.size()==0) {
	    		return Ratings;
	    	}
	    	else {
	    		for (Rating temp : ratings) {
	    			Ratings+=temp.GetRating();}
	    		return Ratings/ratings.size();
	    }
	    }

}
