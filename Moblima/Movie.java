package Moblima;

public class Movie {
	 	private String name;
	    private int ratings = 0;
	    private String status;
	    private String synopsis;
	    private String director;
	    private String cast;
	    private String review;
	  

	    public Movie(String name,String status, String director) {
	        this.name = name;
	        this.status = status;
	        this.director = director;
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

	    public float getRatings() {
	        return ratings;
	    }

	 

	  

}
