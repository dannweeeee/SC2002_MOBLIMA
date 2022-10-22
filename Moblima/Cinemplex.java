package Moblima;

import java.util.ArrayList;
import java.util.HashMap;

public class Cinemplex {
	private String location;
	private ArrayList<cinema> listHall;
	private int cinemaNo;
	private  HashMap<String,ArrayList<Show>> movieMap;
	
	
    
    private void generateMovieMap(){
        for (cinema theater :listHall) {
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
    public Cinemplex(ArrayList<cinema> listHall,String location) {
        this.listHall = listHall;
        this.movieMap = new HashMap<>();
        this.location=location;
        generateMovieMap();
    }
    public ArrayList<Show> searchShows(String movieName){
        if (movieMap.containsKey(movieName)){
            return movieMap.get(movieName);
        }
        else
            return null;
    }
	public void setHall(ArrayList<cinema> listHall) {
		this.listHall=listHall;
	}
	public ArrayList<cinema> getHall() {
		return listHall;
	}
	public int getcinemaNo() {
		return cinemaNo;
	}
	public void setcinemaNo(int cinemaNo) {
		this.cinemaNo=cinemaNo;
	}
}