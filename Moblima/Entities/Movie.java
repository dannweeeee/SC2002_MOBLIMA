package Moblima.Entities;

import java.util.ArrayList;

import Moblima.Handlers.MovieHandler;

/**
 * Movie class
 * @author pc
 *
 */
public class Movie  {
	 	private String name;
	    private String status;
	    private String synopsis;
	    private String director;
	    private String cast;
	    private String review;
	    private ArrayList<Rating> ratings;
	    private ArrayList<Review> reviews;
	    private MovieHandler movieH;
	    private double Ratings;
	    public ArrayList<Ticket> ticketlist;

	    public Movie(String name,String status, String director, String synopsis, String cast) {
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
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * @return the synopsis
		 */
		public String getSynopsis() {
			return synopsis;
		}

		/**
		 * @return the director
		 */
		public String getDirector() {
			return director;
		}

		/**
		 * @return the cast
		 */
		public String getCast() {
			return cast;
		}

		/**
		 * @return the ratings
		 */
		public double getRatings() {
			return Ratings;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}

		/**
		 * @param director the director to set
		 */
		public void setDirector(String director) {
			this.director = director;
		}

		/**
		 * @param review the review to set
		 */
		public void setReview(String review) {
			this.review = review;
		}

		/**
		 * @param ratings the ratings to set
		 */
		public void setRatings(double ratings) {
			Ratings = ratings;
		}

		/**
		 * @param set synopsis
		 */
		public void setSynopsis(String synopsis) {
	    	this.synopsis=synopsis;
	    }
	    
	    /**
	     * Set casts of movie
	     * @param  cast
	     */
	    public void setCast(String cast) {
	    	this.cast=cast;
	    }

	    /**
	     * @return name of movie
	     */
	    public String getName() {
	        return name;
	    }

	   
	    /**
	     * add Rating to movie list of ratings
	     * @param Rating 
	     */
	    public void addRatings(Rating Rating) {
	        this.ratings.add(Rating);
	    }
	    
	    /**
	     * adds Review to Movie
	     * @param Review 
	     */
	    public void addReview(Review Review) {
	        this.reviews.add(Review);
	    }
	    
	    /**
	     * @return list of reviews
	     */
	    public ArrayList<Review> getReview(){
	        return reviews;
	    }
	    
	    /**
	     * add ticket to movie
	     * @param  ticket 
	     */
	    public void addticket(Ticket ticket) {
	    	ticketlist.add(ticket);
	    }
	    
	    /**
	     * @return size of ticket list
	     */
	    public int getTicketsSize() {
	    	return ticketlist.size();
	    }
	    
	    /**
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
	        return "Title: " + name + "\n" +
	                "Status: " + status + "\n" +
	                "Director: " + director + "\n" +
	                "Cast: " + cast + "\n" +
	                "Synopsis: " + synopsis + "\n"
	                ;
	    }

		public void removeMovie(int movieID){
			movieH.getMovie().remove(this);
		}
}
