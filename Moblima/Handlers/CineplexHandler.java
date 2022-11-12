package Moblima.Handlers;

import java.util.ArrayList;
import java.util.Iterator;

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
	 * @param CineplexID int of ID of Cineplex to be removed
	 */ 
	public void removeCineplex(int CineplexID){
		ArrayList <Cineplex> cineplexList = CineplexHandler.getInstance().getAllCineplex();
		Iterator<Cineplex> itr = cineplexList.iterator(); 
		while (itr.hasNext()) { 
			Cineplex cineplex = itr.next();
			if(cineplex.getId() == CineplexID){
				itr.remove();
				UtilityOutput.printMessage("DELETED! The Cineplex has been deleted!");
				return;
			}
		}
		UtilityOutput.printMessage("Invalid input. Please try again from Main Menu.");
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