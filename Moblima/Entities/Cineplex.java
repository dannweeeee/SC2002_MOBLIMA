package Moblima.Entities;

import java.util.ArrayList;

import Moblima.Handlers.CinemaHandler;

/**
 * @author pc
 *
 */
public class Cineplex {
	private int idCounter = 0;
	private int id;
	private String location;
	private CinemaHandler cinemaH;
	private int cineplexNo;
    
    /**
     * @param location
     */
    public Cineplex(String location) {
        idCounter += 1;
        this.id = idCounter;
    	this.location=location;
    }
    
	/**
	 * @return
	 */
	public String getLocation(){
		return this.location;
	}
	
	/**
     * @return
     */
    public int getId(){
        return this.id;
    }

	public void setCineplex(String location) {
        this.location=location;
    }
	
	/**
     * @return
     */
    public void setId(int id) {
    	this.id = id;
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
        return id + ": " + location;
    }
}