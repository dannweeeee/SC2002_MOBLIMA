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
	
	public int getCineplexNo() {
		return cineplexNo;
	}
	
	public void setCineplexNo(int cineplexNo) {
		this.cineplexNo=cineplexNo;
	}
	
	@Override
    public String toString() {
        return cineplexNo + ": " + location;
    }
}