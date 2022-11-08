package Moblima.Entities;

import java.util.ArrayList;

import Moblima.Handlers.CinemaHandler;

/**
 * @author pc
 *
 */
public class Cineplex {
	private String location;
	private CinemaHandler cinemaH;
	private int cineplexNo;
    
    /**
     * @param location
     */
    public Cineplex(String location) {
        this.location=location;
    }
    
	/**
	 * @return
	 */
	public String getLocation(){
		return this.location;
	}
	
	/**
	 * @param CinemaHandler
	 */
	public void setHall(CinemaHandler CinemaHandler) {
		this.cinemaH=CinemaHandler;
	}
	
	/**
	 * @return
	 */
	public int getCineplexNo() {
		return cineplexNo;
	}
	
	/**
	 * @param cineplexNo
	 */
	public void setCineplexNo(int cineplexNo) {
		this.cineplexNo=cineplexNo;
	}
	
	/**
	 *
	 */
	@Override
    public String toString() {
        return location;
    }
}