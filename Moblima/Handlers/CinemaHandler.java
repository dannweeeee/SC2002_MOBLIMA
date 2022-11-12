package Moblima.Handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Moblima.Entities.Cinema;
import Moblima.Entities.Cineplex;
import Moblima.Entities.Show;
import Moblima.Entities.Cinema.HallType;
import Moblima.Utils.UtilityOutput;

/**
 * Cinema Handler class to handle Cinema Entities
 * @author Team
 * @version 1.0
 */
public class CinemaHandler {
	private Map<Cineplex, ArrayList<Cinema>> allCinemas;
	private static CinemaHandler instance = null;
	private static int cinemaCounter = 0;
	
	/**
	 * Get and set Instance of CinemaHandler.
	 * @return CinemaHandler
	 */
	public static CinemaHandler getInstance() {
        if (instance == null) {
            instance = new CinemaHandler();
        }
        return instance;
    }
    
	/**
	 * Default constructor for Cinema Handler.
	 */
	public CinemaHandler() {
		allCinemas = new HashMap<>();
	}
	
	/**
	 * Create and map a list of Cinema into a Cineplex.
	 * @param cineplex Gets Cineplex object
	 * @return cinemaList
	 */
	public ArrayList<Cinema> initializeCinema(Cineplex cineplex){
		ArrayList<Cinema> cinemaList = new ArrayList<>();
		allCinemas.put(cineplex, cinemaList);
		return cinemaList;
	}
	
	/**
	 * Get a list of Cinemas from Cineplex.
	 * @param c Gets Cineplex object
	 * @return cinemaList or null if there is no Cinema assigned to Cineplex
	 */
	public ArrayList<Cinema> getCinemaFromCineplex(Cineplex c){
		ArrayList<Cinema> cinemaList = new ArrayList<>();
		for (Map.Entry<Cineplex, ArrayList<Cinema>> entry : this.allCinemas.entrySet()) {
            if(c.getLocation() == entry.getKey().getLocation()){
                cinemaList = entry.getValue();
				return cinemaList;
            }
        }
		cinemaCounter = cinemaList.size();
		return null;
	}

	/**
	 * Add a Cinema into a list of Cinemas mapped to a Cineplex
	 * @param classtype enum of Cinema types 
	 * @param seat_capacity capacity of Cinema
	 * @param cineplex gets desired Cineplex that want to add cinema to
	 */
	public void addCinema(HallType classtype, int seat_capacity, Cineplex cineplex) {
		Cinema newCinema = new Cinema(classtype,seat_capacity, cineplex);
		ArrayList <Cinema> cinemaList = getCinemaFromCineplex(cineplex);
		cinemaList.add(newCinema);
		cinemaCounter++;
	}

	/**
	 * Update the seat capacity of a Cinema
	 * @param cinemaID Get Cinema ID
	 * @param seat_capacity Get new seat capacity 
	 * @param cineplex Get Cineplex that desired Cinema is mapped to
	 */
	public void updateCinema(int cinemaID, int seat_capacity, Cineplex cineplex) {
		ArrayList <Cinema> cinemaList = getCinemaFromCineplex(cineplex);
		for(Cinema temp: cinemaList){
			if(temp.getCinemaID() == cinemaID) temp.setCapacity(seat_capacity);
		}
	}

	/**
	 * Delete a Cinema from list of Cinemas mapped to a Cineplex
	 * @param cineplex Cineplex object that you want to delete Cinema from
	 * @param cinemaID Cinema ID that is to be deleted
	 */
	public void deleteCinema(Cineplex cineplex, int cinemaID){
		ArrayList <Cinema> cinemaList = getCinemaFromCineplex(cineplex);
		ArrayList <Show> showsList = ShowHandler.getInstance().getAllShows();

		Iterator<Cinema> cinemaitr = cinemaList.iterator(); 
		while (cinemaitr.hasNext()) { 
			Cinema cinema = cinemaitr.next(); 
			if(cinema.getCinemaID() == cinemaID){
				cinemaitr.remove(); 
				Iterator<Show> showitr = showsList.iterator(); 
				while (showitr.hasNext()) { 
					Show show = showitr.next(); 
					if(show.getCinema().getCinemaID() == cinemaID){
					showitr.remove(); 
					} 
				}
				UtilityOutput.printMessage("DELETED! The Cinema has been deleted!");
				return;
			} 
		}
		UtilityOutput.printMessage("Invalid input. Please try again from Main Menu.");
	}
	
	/**
	 * Return size of the list of Cinema
	 * @return int returns the size of the list
	 */
	public int getSize() {
		return cinemaCounter;
	}
}
