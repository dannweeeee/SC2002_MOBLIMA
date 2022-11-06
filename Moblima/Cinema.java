package Moblima;

import java.util.ArrayList;

public class Cinema {
	private static int idCounter=0;
	    private int id;
	    private int seat_capacity;
	    private String classtype;
	    private Cineplex cineplex;

	    public Cinema(String classtype,int seat_capacity, Cineplex cineplex) {
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
	    
	    public String getCinemaClass() {
	    	return this.classtype;
	    }
	    
	    public void setCinemaID(int id) {
	    	this.id = id;
	    }
	    
	    public void setCapacity(int capacity) {
	    	this.seat_capacity = capacity;
	    }
	    
	    public void setClass(String classType) {
	    	this.classtype = classType;
	    }
	    
	    @Override
	    public String toString() {
	    	return  id+": "+
	    			classtype+", "+
	    			seat_capacity;
	    }
}
