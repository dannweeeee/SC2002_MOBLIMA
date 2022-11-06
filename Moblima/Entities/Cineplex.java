package Moblima.Entities;

import java.util.ArrayList;

import Moblima.Handlers.CinemaHandler;

public class Cineplex {
	private String location;
	private CinemaHandler cinemaH;
	private int cineplexNo;
    
    public Cineplex(String location) {
        this.location=location;
    }
    
	public String getLocation(){
		return this.location;
	}
	
	public void setHall(CinemaHandler CinemaHandler) {
		this.cinemaH=CinemaHandler;
	}
	
	public ArrayList<Cinema> getHall() {
		return cinemaH.getAllCinema();
	}
	
	public int getCineplexNo() {
		return cineplexNo;
	}
	
	public void setCineplexNo(int cineplexNo) {
		this.cineplexNo=cineplexNo;
	}

	public void printAllCinema() {
		int count =0;
		for (Cinema temp : cinemaH.getAllCinema()) {
			System.out.println(temp);
			count++;
		}
	}
	
	@Override
    public String toString() {
        return cineplexNo + ": " + location;
    }
}