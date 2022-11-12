package Moblima.Handlers;

import java.util.Date;

import Moblima.Entities.Cinema;
import Moblima.Entities.Cineplex;
import Moblima.Entities.Movie;
import Moblima.Entities.Show;
import Moblima.Entities.Movie.MovieStatus;
import Moblima.Utils.UtilityOutput;

import java.util.ArrayList;

/**
 * Show Handler class to handle Show Entities
 * @author team
 * @version 1.0
 */
public class ShowHandler {
    private ArrayList<Show> allShows;
	private static ShowHandler instance = null;
	
	/**
	 * Get and set instance of ShowHandler
	 * @return ShowHandler
	 */
    public static ShowHandler getInstance() {
        if (instance == null) {
            instance = new ShowHandler();
        }
        return instance;
    }

    /**
     * Default constructor for the ShowHandler class
     */
    public ShowHandler(){
        this.allShows = new ArrayList<>(); 
    }

    /**
     * Create a new Show and add it to the list of Shows
     * @param showTime Date of show (DD/MM/YY HH:MM:ss)
     * @param movie Object of movie that want to create Show of
     * @param theater Object of Cinema to create the show in
     * @param seathandler Seats Handler for the show
     * @return newShow An object of Show created.
     */
    public Show addShows(Date showTime, Movie movie, Cinema theater, SeatHandler seathandler){
        Show newShow = new Show(showTime, movie, theater);
        this.allShows.add(newShow);
        seathandler.initializeSeats(newShow);
        return newShow;
    }

    /**
     * Get Show from list of Show by specified ID
     * @param shows List of Shows
     * @param showID int for the Show Identifier
     * @return result An object of Show with the matching ID
     */
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

    /**
     * Get all shows in the list of Shows
     * @return allShows A List of Shows
     */
    public ArrayList<Show> getAllShows(){
        return this.allShows;
    }

    /**
     * Get all shows that have not ended (based on the Show Time and current Date)
     * @return result_list A List of all the Shows that have not ended
     */
    public ArrayList<Show> getAllShowsShowing(){
        ArrayList <Show> result_list = new ArrayList<>();
        for (Show s : this.allShows){
            if (s.getShowTime().compareTo(new Date()) > 0){
                if (s.getMovie().getStatus() == MovieStatus.PREVIEW || s.getMovie().getStatus() == MovieStatus.NOW_SHOWING){
                    result_list.add(s);
                }
            }
        }

        return result_list;
    }

    /**
     * Print all Shows
     * @param shows List of all shows
     */
    public static void printAllShows(ArrayList<Show> shows){
        for (Show temp : shows)
            UtilityOutput.printMessage(temp.toString());
    }

    /**
     * Get All shows in the List of Shows by the location(Cineplex)
     * @param c Object of Cineplex
     * @return results List of Shows that match the location
     */
    public ArrayList<Show> getAllShowsByLocation(Cineplex c){
        ArrayList<Show> results = new ArrayList<>();
        for (Show s : this.allShows){
            if (c.getLocation() == s.getCinema().getCineplex().getLocation() && s.getShowTime().compareTo(new Date()) > 0){
                results.add(s);
            }
        }
        return results;
    }

    /**
     * Search shows in the List of Shows that have Names that match the searchString
     * @param searchString String input by the user
     * @return results List of Shows that fulfils the search
     */
    public ArrayList<Show> searchShows(String searchString){
        ArrayList<Show> results = new ArrayList<>();
        for (Show show : this.allShows){
            if (show.getMovie().getName().toLowerCase().contains(searchString) && show.getShowTime().compareTo(new Date()) > 0){
                results.add(show); 
            }
        }
        return results;
    }

    /**
     * Remove a show from the list of shows.
     * @param show Object of Show that is to be removed
     */
    public void removeShow(Show show){
        this.allShows.remove(show);
        show = null;
    }
   
}
