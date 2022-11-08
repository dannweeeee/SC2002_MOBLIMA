package Moblima.Entities;

import Moblima.Entities.Cinema.HallType;

/**
 * Cinema Class
 * @author team
 *
 */

public class Cinema {
	private static int idCounter=0;
	    private int id;
	    private int seat_capacity;
	    private HallType classtype;
	    private Cineplex cineplex;

		public enum HallType{
			STANDARD, PREMIUM, VIP
		}

	    /**
	     * @param classtype
	     * @param seat_capacity
	     * @param cineplex
	     */
	    public Cinema(HallType classtype,int seat_capacity, Cineplex cineplex) {
	        idCounter += 1;
	        this.id = idCounter;
	        this.classtype= classtype;
	        this.seat_capacity = seat_capacity;
			this.cineplex = cineplex;
	    }

		/**
		 * Get method for Cineplex of current Cinema
		 * @return cineplex 
		 */
		public Cineplex getCineplex(){
			return this.cineplex;
		}

	    /**
	     * Get method for seat_capacity of Cinema
	     * @return seat_capacity
	     */
	    public int getCapacity() {
	        return this.seat_capacity;
	    }

	    /**
	     * Get method for CinemaID
	     * @return CinemaID
	     */
	    public int getCinemaID() {
	    	return this.id;
	    }
	    
	    /**
	     * Get method for classType of Cinema
	     * @return classType
	     */
	    public HallType getCinemaClass() {
	    	return this.classtype;
	    }
	    
	    /**
	     * Set method for CinemaID
	     * @param id 
	     */
	    public void setCinemaID(int id) {
	    	this.id = id;
	    }
	    
	    /**
	     * Set method for seat_capacity of Cinema
	     * @param capacity
	     */
	    public void setCapacity(int capacity) {
	    	this.seat_capacity = capacity;
	    }
	    
	    /**
	     * Set method for classType of Cinema
	     * @param classType 
	     */
	    public void setClass(HallType classType) {
	    	this.classtype = classType;
	    }
	    
	    /**
	     * String format for Cinema class
	     */
	    @Override
	    public String toString() {
	    	return  id+": "+
	    			classtype+", "+
	    			seat_capacity;
	    }
}
