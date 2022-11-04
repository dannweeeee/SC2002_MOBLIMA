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
	        return seat_capacity;
	    }

	    public int getCinemaID() {
	    	return id;
	    }
	    
	    public void printCinema() {
	    	System.out.print(id+": ");
			System.out.print(classtype+", ");
			System.out.print(seat_capacity+", ");
	    }
}
