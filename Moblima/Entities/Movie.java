package Moblima.Entities;

import java.util.ArrayList;

import Moblima.Handlers.MovieHandler;

/**
 * Movie class
 * @author pc
 * @version 1.0
 */
public class Movie  {
		private static int idCounter=0;
		private int id;
	 	private String name;
	    private MovieStatus status;
	    private String synopsis;
	    private String director;
	    private String cast;
	    private String review;
	    private ArrayList<Rating> ratings;
	    private ArrayList<Review> reviews;
	    private MovieHandler movieH;
	    private double Ratings;
	    private ArrayList<Ticket> ticketlist;
	    
	    /**
		 * Enum Movie Status
		 */
		public enum MovieStatus{
			/**
			 * Movie coming soon.
			 */
			COMING_SOON, 
			/**
			 * Movie currently showing.
			 */
			NOW_SHOWING, 
			/**
			 * Movie sneak preview.
			 */
			PREVIEW,
			/**
			 * Movie no longer screened.
			 */
			END_OF_SHOWING
		}
	    
		/**
		 * Constructor for Movie class
		 * @param name name of movie
		 * @param status status of movie (showing, preview)
		 * @param director director's name
		 * @param synopsis synopsis of movie
		 * @param cast movie cast
		 */
	    public Movie(String name,MovieStatus status, String director, String synopsis, String cast) {
	    	idCounter += 1;
	        this.id = idCounter;
	    	this.name = name;
	    	this.status = status;
	        this.director = director;
	        this.synopsis = synopsis;
	        this.cast = cast;
	        this.ratings = new ArrayList<>();
	        this.reviews = new ArrayList<>();
	        this.ticketlist=new ArrayList<>();
	    }
	    
	    /**
		 * Get method for Movie ID
		 * @return the movie ID
		 */
		public int getId() {
			return this.id;
		}
	    
	    /**
		 * Get method for Status
		 * @return the status
		 */
		public MovieStatus getStatus() {
			return this.status;
		}

		/**
		 * Get method for Synopsis
		 * @return the synopsis
		 */
		public String getSynopsis() {
			return this.synopsis;
		}

		/**
		 * Get method for Director
		 * @return the director
		 */
		public String getDirector() {
			return this.director;
		}

		/**
		 * Get Method for Cast
		 * @return the cast
		 */
		public String getCast() {
			return this.cast;
		}

		/**
		 * Get method for Ratings
		 * @return the ratings
		 */
		public double getRatings() {
			return Ratings;
		}
		
		/**
		 * Set method for ID
		 * @param id the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}
		
		/**
		 * Set method for movie name
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * Set method for Status
		 * @param status the status to set
		 */
		public void setStatus(MovieStatus status) {
			this.status = status;
		}

		/**
		 * Set method for director
		 * @param director the director to set
		 */
		public void setDirector(String director) {
			this.director = director;
		}

		/**
		 * Set method for review
		 * @param review the review to set
		 */
		public void setReview(String review) {
			this.review = review;
		}

		/**
		 * Set method for ratings
		 * @param ratings the ratings to set
		 */
		public void setRatings(double ratings) {
			Ratings = ratings;
		}

		/**
		 * Set method for synopsis
		 * @param synopsis updated value of synopsis 
		 */
		public void setSynopsis(String synopsis) {
	    	this.synopsis=synopsis;
	    }
	    
	    /**
	     * Set casts of movie
	     * @param cast updated value of cast
	     */
	    public void setCast(String cast) {
	    	this.cast=cast;
	    }

	    /**
		 * Get method for movie name
	     * @return name of movie
	     */
	    public String getName() {
	        return name;
	    }

	   
	    /**
	     * add Rating to movie list of ratings
	     * @param rating updated rating
	     */
	    public void addRatings(Rating rating) {
	        this.ratings.add(rating);
	    }
	    
	    /**
	     * adds Review to Movie
	     * @param review review to be added 
	     */
	    public void addReview(Review review) {
	        this.reviews.add(review);
	    }
	    
	    /**
		 * Get reviews for movie
	     * @return list of reviews
	     */
	    public ArrayList<Review> getReview(){
	        return reviews;
	    }
	    
	    /**
	     * add ticket to movie
	     * @param ticket ticket to be added to movie
	     */
	    public void addticket(Ticket ticket) {
	    	ticketlist.add(ticket);
	    }
	    
	    /**
		 * get total number of ticket
	     * @return size of ticket list
	     */
	    public int getTicketsSize() {
	    	return ticketlist.size();
	    }
	    
	    /**
		 * get avg rating of movie
	     * @return AverageRatings of movie
	     */
	    public String getAverageRatings() {
	    	Ratings=0;
	    	if(ratings.size()<2) {
	    		return "NA";
	    	}
	    	else {
	    		for (Rating temp : ratings) {
	    			Ratings+=temp.GetRating();}
	    		return Double.toString(Ratings/ratings.size());
	    	}
	    }
	    
	    /**
	     *String format for movie
	     */
	    @Override
	    public String toString() {
	        return 	id + ". "+
	        		name + "\n" +
	                "Status: " + status + "\n" +
	                "Director: " + director + "\n" +
	                "Cast: " + cast + "\n" +
	                "Synopsis: " + synopsis + "\n"
	                ;
	    }
		/**
		 * remove this movie
		 * @param movieID movie ID to remove
		 */
		public void removeMovie(int movieID){
			movieH.getMovie().remove(this);
		}
}
