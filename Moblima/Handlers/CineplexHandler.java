package Moblima.Handlers;

import java.util.ArrayList;

import Moblima.Entities.Cineplex;
import Moblima.Utils.UtilityOutput;

public class CineplexHandler {
	
	private ArrayList<Cineplex> allCineplex;
	private static int cineplexCounter=0;
	private static CineplexHandler instance = null;
	
    public static CineplexHandler getInstance() {
        if (instance == null) {
            instance = new CineplexHandler();
        }
        return instance;
    }
	
	public CineplexHandler() {
		allCineplex = new ArrayList<Cineplex>();
	}
	
	/**
     * Add a Cineplex.
     */	 	
	public Cineplex addCineplex(String location, CinemaHandler cinemaHandler) {
		Cineplex newCineplex = new Cineplex(location);
		allCineplex.add(newCineplex);
		cineplexCounter++;
		cinemaHandler.initializeCinema(newCineplex);
		return newCineplex;
	   }

	/**
     * Remove a Cineplex.
     */	 	   
	public void removeCineplex(Cineplex c){
		this.allCineplex.remove(c);
		c = null;
	   }
	
	/**
     * Print all Cineplexes.
     */	   
	public void printAllCineplex() {
		UtilityOutput.printObjectList(allCineplex);
    }
	
	/**
     * Get array list of all Cineplexes.
     */
	public ArrayList<Cineplex> getAllCineplex(){
		return allCineplex;
	}
	
	/**
     * Get number of Cineplexes.
     */
	public int getSize() {
		return cineplexCounter;
	}
	
}