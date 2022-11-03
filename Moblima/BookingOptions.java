package Moblima;
import java.util.ArrayList;

public class BookingOptions extends Booking {

    public ArrayList<Ticket> searchMoviebyName(ArrayList<Cineplex> cineplexes, User user1){
        System.out.print("Enter movie name to search: ");
        String searchString = scanner.nextLine();
        searchString = searchString.substring(0,1).toUpperCase() + searchString.substring(1).toLowerCase();
        ArrayList<Show> allShows = searchShows(cineplexes, searchString);
        if (allShows.size() >= 1){
            printAllShows(allShows);
            Show showSelected = selectShow(allShows);
            if (showSelected != null) return bookSeats(showSelected, user1);
        }
        return null;
    }

    public ArrayList<Ticket> searchMovieByLocation(ArrayList<Cineplex> cineplexes, User user1){
        int choice = getCineplex(cineplexes);
        if (choice > cineplexes.size()){
            System.out.println("Invalid Input");
            return null;
        } else if (choice <= -1){
            return null;
        }
        Cineplex chosenCineplex = cineplexes.get(choice);
        ArrayList<Show> showsInCineplex = new ArrayList<>();
        if (chosenCineplex.getHall() != null){
            for (Cinema theater : chosenCineplex.getHall()){
                printAllShows(theater.getShows());
                showsInCineplex.addAll(theater.getShows());
            }
            Show showSelected = selectShow(showsInCineplex);
            if (showSelected != null) return bookSeats(showSelected, user1);
        }
        return null;
    }

    public ArrayList<Ticket> listAllMovies(ArrayList<Cineplex> cineplexes, User user1){
        System.out.println("Which show would you like to book");
        ArrayList<Show> allShows = listAllShows(cineplexes);
        if (allShows.size() >= 1){
            printAllShows(allShows);
            Show showSelected = selectShow(allShows);
            if (showSelected != null) return bookSeats(showSelected, user1);
        }
        return null;
    }
}
