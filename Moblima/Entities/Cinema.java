package Moblima.Entities;

import Moblima.Entities.Cinema.HallType;

/**
 * @author pc
 *
 */
/**
 * @author pc
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
		 * @return
		 */
		public Cineplex getCineplex(){
			return this.cineplex;
		}

	    /**
	     * @return
	     */
	    public int getCapacity() {
	        return this.seat_capacity;
	    }

	    /**
	     * @return
	     */
	    public int getCinemaID() {
	    	return this.id;
	    }
	    
	    /**
	     * @return
	     */
	    public HallType getCinemaClass() {
	    	return this.classtype;
	    }
	    
	    /**
	     * @param id
	     */
	    public void setCinemaID(int id) {
	    	this.id = id;
	    }
	    
	    /**
	     * @param capacity
	     */
	    public void setCapacity(int capacity) {
	    	this.seat_capacity = capacity;
	    }
	    
	    /**
	     * @param classType
	     */
	    public void setClass(HallType classType) {
	    	this.classtype = classType;
	    }
	    
	    /**
	     *
	     */
	    @Override
	    public String toString() {
	    	return  id+": "+
	    			classtype+", "+
	    			seat_capacity;
	    }
}
