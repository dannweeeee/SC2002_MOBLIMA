package Moblima;

import java.util.ArrayList;

public class Cineplex {
	private String location;
	private cinemaHandler cinemaH;
	private int cineplexNo;
    
    public Cineplex(String location) {
        this.location=location;
    }
    
	public String getLocation(){
		return this.location;
	}
	
	public void setHall(cinemaHandler cinemaHandler) {
		this.cinemaH=cinemaHandler;
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