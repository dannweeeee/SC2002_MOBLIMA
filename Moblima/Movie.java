package Moblima;

import java.util.ArrayList;

public class Movie {
	 	private String name;
	    private String status;
	    private String synopsis;
	    private String director;
	    private String cast;
	    private String review;
	    private ArrayList<Rating> ratings;
	    private ArrayList<Review> reviews;
	  

	    public Movie(String name,String status, String director) {
	        this.name = name;
	        this.status = status;
	        this.director = director;
	        this.ratings = new ArrayList<>();
	        this.reviews = new ArrayList<>();
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

	    public void addRatings(Rating R) {
	        this.ratings.add(R);
	    }
	    public void addReview(Review R) {
	        this.reviews.add(R);
	    }
	    public ArrayList<Rating> getRatings(){
	        return ratings;
	    }
	    public ArrayList<Review> getReview(){
	        return reviews;
	    }
	 

}
