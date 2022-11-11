package Moblima.Entities;
/**
 * Class for Cineplex entity
 * @author pc
 * @version 1.0
 */
public class Cineplex {
	private int idCounter = 0;
	private int id;
	private String location;
	private int cineplexNo;
    
    /**
	 * Default Constructor
     * @param location location of cineplex
     */
    public Cineplex(String location) {
        idCounter += 1;
        this.id = idCounter;
    	this.location=location;
    }
    
	/**
	 * Get method for Location
	 * @return location
	 */
	public String getLocation(){
		return this.location;
	}
	
	/**
	 * Get Method for ID
     * @return current ID
     */
    public int getId(){
        return this.id;
    }

	/**
	 * Set method for Cineplex
	 * @param location location of cineplex
	 */
	public void setCineplex(String location) {
        this.location=location;
    }
	
	/**
	 * Set method for CinemaID
     * @param id new value for CinemaID
     */
    public void setId(int id) {
    	this.id = id;
    }
	
	/**
	 * Get method for Cineplex Number
	 * @return cineplex number
	 */
	public int getCineplexNo() {
		return cineplexNo;
	}
	
	/**
	 * Set method for cineplex number
	 * @param cineplexNo cineplex number
	 */
	public void setCineplexNo(int cineplexNo) {
		this.cineplexNo=cineplexNo;
	}
	
	/**
	 * toString() method
	 * @return string of id: location
	 */
	@Override
    public String toString() {
        return id + ": " + location;
    }
}