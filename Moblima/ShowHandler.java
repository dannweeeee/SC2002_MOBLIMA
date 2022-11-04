package Moblima;

import java.util.Date;
import java.util.ArrayList;

public class ShowHandler {
    private ArrayList<Show> allShows;

    public ShowHandler(){
        this.allShows = new ArrayList<>(); 
    }

    public Show addShows(Date showTime, Movie movie, Cinema theater, SeatHandler seathandler){
        Show newShow = new Show(showTime, movie, theater);
        this.allShows.add(newShow);
        seathandler.initializeSeats(newShow);
        return newShow;
    }

    public ArrayList<Show> getAllShows(){
        return this.allShows;
    }

    public static void printAllShows(ArrayList<Show> shows){
        Show temp;
        for (int i = 0; i < shows.size(); i++){
            temp = shows.get(i);
            System.out.println(temp.getID() + ". " + temp.getMovie().getName());
            System.out.println("Show time: " + temp.getShowTime());
            System.out.println("Location: " + temp.getCinema().getCineplex().getLocation());
        }
    }

    public ArrayList<Show> searchShows(String searchString){
        ArrayList<Show> results = new ArrayList<>();
        for (Show show : this.allShows){
            if (show.getMovie().getName().toLowerCase().contains(searchString)){
                results.add(show); 
            }
        }
        return results;
    }
}
