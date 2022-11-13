package Moblima.Entities;

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

		/**
		 * Enum Hall Type 
		 */
		public enum HallType{
			/**
			 * Standard Hall Type
			 */
			STANDARD, 
			/**
			 * Premium Hall Type
			 */
			PREMIUM, 
			/**
			 * VIP Hall Type
			 */
			VIP,
			/**
			 * 3D Movies Hall Type
			 */
			IMAX_3D
		}

	    /**
		 * Contructor
	     * @param classtype HallType for Cinema
	     * @param seat_capacity total number of seats
	     * @param cineplex cineplex object
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
	     * @param id value to update CinemaID
	     */
	    public void setCinemaID(int id) {
	    	this.id = id;
	    }
	    
	    /**
	     * Set method for seat_capacity of Cinema
	     * @param capacity value to update seat capacity
	     */
	    public void setCapacity(int capacity) {
	    	this.seat_capacity = capacity;
	    }
	    
	    /**
	     * Set method for classType of Cinema
	     * @param classType HallType value to update classType for Cinema
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
