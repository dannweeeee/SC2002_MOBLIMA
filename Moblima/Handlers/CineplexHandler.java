package Moblima.Handlers;

import java.util.ArrayList;

import Moblima.Entities.Cineplex;
import Moblima.Utils.Settings;

public class CineplexHandler {
	
	private ArrayList<Cineplex> allCineplex;
	private static int cineplexCounter=0;
	private static CineplexHandler instance = null;
	
    public static CineplexHandler getInstance() {
        if (instance == null) {
            instance = new CineplexHandler();
        }
        return instance;
    }
	public CineplexHandler() {
		allCineplex = new ArrayList<Cineplex>();
	}
	
	public Cineplex addCineplex(String location) {
		Cineplex newCineplex = new Cineplex(location);
		allCineplex.add(newCineplex);
		cineplexCounter++;
		return newCineplex;
	}
	
	public void printAllCineplex() {
		int count =1;
		for (Cineplex temp : allCineplex) {
			temp.setCineplexNo(count);
			System.out.print(temp);
			count++;
		}
	}
	
	public ArrayList<Cineplex> getAllCineplex(){
		return allCineplex;
	}
	
	public int getSize() {
		return cineplexCounter;
	}
	
}