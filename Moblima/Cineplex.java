package Moblima;

import java.util.ArrayList;
import java.util.HashMap;

public class Cineplex {
	private String location;
	private cinemaHandler cinemaH;
	private int cineplexNo;
	private  HashMap<String,ArrayList<Show>> movieMap;
	ArrayList<Show> movieShowList;
	
    
    public Cineplex(String location) {
        this.movieMap = new HashMap<>();
        this.location=location;
        
        
    }
    
    public void generateMovieMap(){
    	ArrayList<Cinema> theaters=this.cinemaH.getAllCinema();
        for (Cinema theater :theaters) {
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
        if (movieMap.containsKey(movieName)){
            return movieMap.get(movieName);
        }
        else
            return null;
    }
    public ArrayList<Show> getAllShows(){
    	return movieShowList;
    }
   
    public void printMovieMap() {
    	System.out.println(movieMap);
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
	public void printCineplex() {
		System.out.println(location);
	}
	public void printAllCinema() {
		int count =0;
		for (Cinema temp : cinemaH.getAllCinema()) {
			temp.printCinema();
			count++;
		}
	}
	
}