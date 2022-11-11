package Moblima.Handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Moblima.Entities.Cinema;
import Moblima.Entities.Cineplex;
import Moblima.Entities.Show;
import Moblima.Entities.Cinema.HallType;

public class CinemaHandler {
	private Map<Cineplex, ArrayList<Cinema>> allCinemas;
	private static CinemaHandler instance = null;
	private static int cinemaCounter = 0;

	public static CinemaHandler getInstance() {
        if (instance == null) {
            instance = new CinemaHandler();
        }
        return instance;
    }
    
	public CinemaHandler() {
		allCinemas = new HashMap<>();
	}
	
	public ArrayList<Cinema> initializeCinema(Cineplex cineplex){
		ArrayList<Cinema> cinemaList = new ArrayList<>();
		allCinemas.put(cineplex, cinemaList);
		return cinemaList;
	}
	
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

	public void addCinema(HallType classtype, int seat_capacity, Cineplex cineplex) {
		Cinema newCinema = new Cinema(classtype,seat_capacity, cineplex);
		ArrayList <Cinema> cinemaList = getCinemaFromCineplex(cineplex);
		cinemaList.add(newCinema);
		cinemaCounter++;
	}

	public void updateCinema(int cinemaID, int seat_capacity, Cineplex cineplex) {
		ArrayList <Cinema> cinemaList = getCinemaFromCineplex(cineplex);
		for(Cinema temp: cinemaList){
			if(temp.getCinemaID() == cinemaID) temp.setCapacity(seat_capacity);
		}
	}

	public void deleteCinema(Cineplex cineplex, int cinemaID){
		ArrayList <Cinema> cinemaList = getCinemaFromCineplex(cineplex);
		ArrayList <Show> showsList = ShowHandler.getInstance().getAllShows();
		HallType classType = cinemaList.get(cinemaID).getCinemaClass();
		cinemaList.remove(cinemaID);

		Iterator<Show> itr = showsList.iterator(); 
		while (itr.hasNext()) { 
			Show show = itr.next(); 
			if(show.getCinema().getCinemaClass() == classType){
				itr.remove(); 
			} 
		} 
	}
	
	public int getSize() {
		return cinemaCounter;
	}
}
