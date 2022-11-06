package Moblima.Handlers;

import java.util.ArrayList;

import Moblima.Entities.Cinema;
import Moblima.Entities.Cineplex;
import Moblima.Entities.Cinema.HallType;

public class CinemaHandler {
	
	private ArrayList<Cinema> allCinema;
	private String cineplexName;
    
	public CinemaHandler(String cineplexName) {
		allCinema = new ArrayList<Cinema>();
		this.cineplexName = cineplexName;
	}
	
	public void addCinema(HallType classtype, int seat_capacity, Cineplex cineplex) {
		Cinema newCinema = new Cinema(classtype,seat_capacity, cineplex);
		allCinema.add(newCinema);
	}
	
	public void printCinema() {
		for (Cinema temp : allCinema) {
			System.out.println(temp);
		}
	}
	
	public ArrayList<Cinema> getAllCinema(){
		return allCinema;
	}
}
