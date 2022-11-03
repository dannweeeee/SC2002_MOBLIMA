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
	    
	  

	    public Movie(String name,String status, String director, String synopsis, String cast, movieHandler movieH) {
	        this.name = name;
	        this.status = status;
	        this.director = director;
	        this.synopsis = synopsis;
	        this.cast = cast;
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
	    	System.out.print(name+" | ");
	    	System.out.print(status+" | ");
	    	System.out.println(director);
	    }
	    
	    public void printFullMovieDetails() {
	    	System.out.println("Title: "+name);
	    	System.out.println("Status: "+status);
	    	System.out.println("Director: "+director);
	    	System.out.println("Cast: "+cast);
	    	System.out.println("Synopsis: "+synopsis);
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
