package Moblima.Handlers;

import java.util.Date;

import Moblima.Entities.Cinema;
import Moblima.Entities.Cineplex;
import Moblima.Entities.Movie;
import Moblima.Entities.Show;
import Moblima.Utils.UtilityOutput;

import java.util.ArrayList;

public class ShowHandler {
    private ArrayList<Show> allShows;
	private static ShowHandler instance = null;
	
    public static ShowHandler getInstance() {
        if (instance == null) {
            instance = new ShowHandler();
        }
        return instance;
    }

    public ShowHandler(){
        this.allShows = new ArrayList<>(); 
    }

    public Show addShows(Date showTime, Movie movie, Cinema theater, SeatHandler seathandler){
        Show newShow = new Show(showTime, movie, theater);
        this.allShows.add(newShow);
        seathandler.initializeSeats(newShow);
        return newShow;
    }

    public static Show getShowByID(ArrayList<Show> shows, int showID){
        Show result = null;
        for(Show s : shows){
            if(s.getID() == showID){
                result = s;
                break;
            }
        }
        return result;
    }

    public ArrayList<Show> getAllShows(){
        return this.allShows;
    }

    public ArrayList<Show> getAllShowsShowing(){
        ArrayList <Show> result_list = new ArrayList<>();
        for (Show s : this.allShows){
            if (s.getShowTime().compareTo(new Date()) > 0){
                result_list.add(s);
            }
        }

        return result_list;
    }

    public static void printAllShows(ArrayList<Show> shows){
        Show temp;
        for (int i = 0; i < shows.size(); i++){
            temp = shows.get(i);
            UtilityOutput.printMessage(temp.getID() + ". " + temp.getMovie().getName());
            UtilityOutput.printMessage("Show time: " + temp.getShowTime());
            UtilityOutput.printMessage("Hall Type: " + temp.getCinema().getCinemaClass());
            UtilityOutput.printMessage("Location: " + temp.getCinema().getCineplex().getLocation());
            UtilityOutput.printMessage("");
        }
    }

    public ArrayList<Show> getAllShowsByLocation(Cineplex c){
        ArrayList<Show> results = new ArrayList<>();
        for (Show s : this.allShows){
            if (c.getLocation() == s.getCinema().getCineplex().getLocation() && s.getShowTime().compareTo(new Date()) > 0){
                results.add(s);
            }
        }
        return results;
    }

    public ArrayList<Show> searchShows(String searchString){
        ArrayList<Show> results = new ArrayList<>();
        for (Show show : this.allShows){
            if (show.getMovie().getName().toLowerCase().contains(searchString) && show.getShowTime().compareTo(new Date()) > 0){
                results.add(show); 
            }
        }
        return results;
    }

    public void removeShow(Show show){
        this.allShows.remove(show);
        show = null;
    }
   
}
