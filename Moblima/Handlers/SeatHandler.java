package Moblima.Handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Moblima.Entities.Seats;
import Moblima.Entities.Show;

/**
 * Seat Handler class to Handle  all Seats
 * @author team
 *	@version 1.0
 */
public class SeatHandler {
    private Map<Show, ArrayList<Seats>> allSeats;
	private static SeatHandler instance = null;
	
	/**
	 * Get and create instance for the SeatHandler class
	 * @return SeatHandler
	 */
    public static SeatHandler getInstance() {
        if (instance == null) {
            instance = new SeatHandler();
        }
        return instance;
    }

    /**
     * Default constructor for the SeatHandler class
     */
    public SeatHandler(){
        allSeats = new HashMap<>();
    }

    /**
     * Create a matrix of seats for the specified show
     * @param show Object of show to create the seats
     * @return newSeats A List of seats for the show
     */
    public ArrayList<Seats> initializeSeats(Show show){
        ArrayList<Seats> newSeats = new ArrayList<Seats>();
        int cols = 10;
        int rows = show.getAvailableSeats() / cols;
        int remaining = show.getAvailableSeats() - (rows * cols);

        for (int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                Seats temp = new Seats(String.valueOf((char)(i + 65)), Integer.toString(j));
                newSeats.add(temp);
            }
        }
        for(int x = 0; x < remaining; x++){
            newSeats.add(new Seats(String.valueOf((char)(rows + 65)), Integer.toString(x)));
        }

        this.allSeats.put(show, newSeats);
        return newSeats;
    }

    /**
     * Get the list of Seats for the specified Show
     * @param s A object of show
     * @return seats A list of seats
     */
    public ArrayList<Seats> getSeatList(Show s){
        ArrayList<Seats> seats = null;
        for (Map.Entry<Show, ArrayList<Seats>> entry : this.allSeats.entrySet()) {
            if(s.getID() == entry.getKey().getID()){
                seats = entry.getValue();
            }
        }
        return seats;
    }

    /**
     * Boolean function to check if the specified seats for a show are available
     * @param s1 Object of Seat to query
     * @param s A object of show for the seats 
     * @return boolean
     */
    public boolean checkSeatAvailability(Seats s1, Show s){
        ArrayList<Seats> seats = getSeatList(s);
        boolean available = false;
                        
        for (Seats o : seats){
            if (o.toString().equals(s1.toString())){
                available = true;
                break;
            }
        }
        return available;
    }

    /**
     * Boolean function to check if the amount of seats remaining is enough for the number of seats requested for purchase
     * @param numOfTickets int of number of tickets to check
     * @param s A object of Show to query
     * @return boolean
     */
    public boolean checkCapacity(int numOfTickets, Show s){
        if (numOfTickets > s.getAvailableSeats()){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Remove specified seats from the specified show
     * @param seat Seat to remove
     * @param s Show object to remove the seats from
     */
    public void removeSeats(Seats seat, Show s){
        ArrayList<Seats> allSeats = getSeatList(s);
        for (Seats seats : allSeats){
            if (seats.toString().equals(seat.toString())){
            	s.setAvailableSeats(s.getAvailableSeats()-1);
                seats.setRow("X");
                seats.setCol("X");
                break;
            }
        }

        this.allSeats.put(s, allSeats);
    }

    /**
     * Boolean function to check if the user input the same seat for both ticket user trying to book in the same order
     * @param s1 Object of a seat
     * @param chosenSeats List of the chosen seats
     * @return boolean
     */
    public static boolean duplicateSeatInput(Seats s1, ArrayList<Seats> chosenSeats){
        boolean duplicate = false;
        for (Seats o : chosenSeats){
            if (o.toString().equals(s1.toString())){
                duplicate = true;
                break;
            }
        }
        return duplicate;
    }
}

