package Moblima.Entities;

import Moblima.Entities.Cinema.HallType;

public class Cinema {
	private static int idCounter=0;
	    private int id;
	    private int seat_capacity;
	    private HallType classtype;
	    private Cineplex cineplex;

		public enum HallType{
			STANDARD, PREMIUM, VIP
		}

	    public Cinema(HallType classtype,int seat_capacity, Cineplex cineplex) {
	        idCounter += 1;
	        this.id = idCounter;
	        this.classtype= classtype;
	        this.seat_capacity = seat_capacity;
			this.cineplex = cineplex;
	    }

		public Cineplex getCineplex(){
			return this.cineplex;
		}

	    public int getCapacity() {
	        return this.seat_capacity;
	    }

	    public int getCinemaID() {
	    	return this.id;
	    }
	    
	    public HallType getCinemaClass() {
	    	return this.classtype;
	    }
	    
	    public void setCinemaID(int id) {
	    	this.id = id;
	    }
	    
	    public void setCapacity(int capacity) {
	    	this.seat_capacity = capacity;
	    }
	    
	    public void setClass(HallType classType) {
	    	this.classtype = classType;
	    }
	    
	    @Override
	    public String toString() {
	    	return  id+": "+
	    			classtype+", "+
	    			seat_capacity;
	    }
}
