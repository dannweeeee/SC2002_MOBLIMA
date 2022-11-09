package Moblima.Handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Moblima.Entities.Cinema;
import Moblima.Entities.Cineplex;
import Moblima.Entities.Cinema.HallType;

public class CinemaHandler {
	private Map<Cineplex, ArrayList<Cinema>> allCinemas;
	private static CinemaHandler instance = null;

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
		return null;
	}

	public void addCinema(HallType classtype, int seat_capacity, Cineplex cineplex) {
		Cinema newCinema = new Cinema(classtype,seat_capacity, cineplex);
		ArrayList <Cinema> cinemaList = getCinemaFromCineplex(cineplex);
		cinemaList.add(newCinema);
	}


	public void removeCinema(Cineplex c, Cinema cinema){
		ArrayList<Cinema> cinemaList = getCinemaFromCineplex(c);
		cinemaList.remove(cinema);
		cinema = null;
	}
}
