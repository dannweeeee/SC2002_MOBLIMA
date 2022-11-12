package Moblima.Handlers;

import java.util.ArrayList;

import Moblima.Entities.Cineplex;
import Moblima.Utils.UtilityOutput;

/**
 * Cineplex Handler class to handle all Cineplexes
 * @author team
 * @version 1.0
 */
public class CineplexHandler {
	
	private ArrayList<Cineplex> allCineplex;
	private static int cineplexCounter=0;
	private static CineplexHandler instance = null;
	
	/**
	 * Get and set Instance of CineplexHandler.
	 * @return CineplexHandler
	 */
    public static CineplexHandler getInstance() {
        if (instance == null) {
            instance = new CineplexHandler();
        }
        return instance;
    }
	/**
	 * Default constructor for Cineplex Handler
	 */
	public CineplexHandler() {
		allCineplex = new ArrayList<Cineplex>();
	}
		 
	/**
	 * Create and add a Cineplex.
	 * @param location Location/Identifier of the new Cineplex
	 * @param cinemaHandler List of Cinemas to map to the new Cineplex
	 * @return newCineplex 
	 */
	public Cineplex addCineplex(String location, CinemaHandler cinemaHandler) {
		Cineplex newCineplex = new Cineplex(location);
		allCineplex.add(newCineplex);
		cineplexCounter++;
		cinemaHandler.initializeCinema(newCineplex);
		return newCineplex;
	   }

	/**
	 * Remove Cineplex from list of Cineplexes
	 * @param c object of Cineplex to be removed
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
	 * @return allCineplex
	 */
	public ArrayList<Cineplex> getAllCineplex(){
		return allCineplex;
	}
	
	/**
     * Get number of Cineplexes.
     * @return int cineplexCounter
     */
	public int getSize() {
		return cineplexCounter;
	}
	
}