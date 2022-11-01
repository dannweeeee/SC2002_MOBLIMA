package Moblima;

import java.util.ArrayList;
import java.util.HashMap;

public class Cineplex {
	private String location;
	private cinemaHandler cinemaHandler;
	private int cineplexNo;
	private  HashMap<String,ArrayList<Show>> movieMap;
	
    
    public Cineplex(String location) {
        this.movieMap = new HashMap<>();
        this.location=location;
        
    }
    
    private void generateMovieMap(){
        for (Cinema theater :cinemaHandler.getAllCinema()) {
            ArrayList<Show> showArray = theater.getShows();
            for(Show show : showArray) {
                if (show != null) {
                    if (movieMap.containsKey(show.getMovie().getName())) {
                        movieMap.get(show.getMovie().getName()).add(show);
                    } else {
                        ArrayList<Show> movieShowList = new ArrayList<>();
                        movieShowList.add(show);
                        movieMap.put(show.getMovie().getName(), movieShowList);
                    }
                }
            }
        }
    }
    
    public ArrayList<Show> searchShows(String movieName){
    	generateMovieMap();
        if (movieMap.containsKey(movieName)){
            return movieMap.get(movieName);
        }
        else
            return null;
    }
    
    public void printMovieMap() {
    	System.out.print(movieMap);
    }
    
	public void setHall(cinemaHandler cinemaHandler) {
		this.cinemaHandler=cinemaHandler;
	}
	public ArrayList<Cinema> getHall() {
		return cinemaHandler.getAllCinema();
	}
	public int getCineplexNo() {
		return cineplexNo;
	}
	public void setCineplexNo(int cineplexNo) {
		this.cineplexNo=cineplexNo;
	}
	public void printCineplex() {
		System.out.println(location);
	}
	public void printAllCinema() {
		int count =0;
		for (Cinema temp : cinemaHandler.getAllCinema()) {
			temp.printCinema();
			count++;
		}
	}
	
}