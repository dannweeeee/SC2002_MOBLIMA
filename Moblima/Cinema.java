package Moblima;

import java.util.ArrayList;

public class Cinema {
	private static int idCounter=0;
	    private int id;
	    private int seat_capacity;
	    private String classtype;
	    private ArrayList<Show> shows;
	    private Cineplex cinemplex;
	    private cineplexHandler cineplexHandler;
	    private ArrayList<Seats> seats;

	    public Cinema(String classtype,int seat_capacity) {
	        idCounter += 1;
	        this.id = idCounter;
	        this.classtype= classtype;
	        this.seat_capacity = seat_capacity;
	        this.shows = new ArrayList<>();
	        this.seats = seatList();
	    }

	    public ArrayList<Show> getShows(){
	        return shows;
	    }

	    public int getCapacity() {
	        return seat_capacity;
	    }
	    public ArrayList<Seats> seatList(){
			ArrayList<Seats> allseats = new ArrayList<Seats>();
			int cols = 10;
			int rows = this.seat_capacity / cols;

			for (int i = 0; i < rows; i++){
				for(int j = 0; j < cols; j++){
					Seats temp = new Seats(String.valueOf((char)(i + 65)), Integer.toString(j));
					allseats.add(temp);
				}
			}
			return allseats;
		}
	    public void removeSeats(Seats seat){
			System.out.println("removing seat: " + seat.getSeat());
			for (Seats s : this.seats){
				if (s.getSeat().equals(seat.getSeat())){
					s.setRow("X");
					s.setCol("X");
					break;
				}
			}
		}
	    public ArrayList<Seats> getSeatList(){
			return this.seats;
		}
	    public int getCinemaID() {
	    	return id;
	    }
	    
	    public void printCinema() {
	    	System.out.print(id+": ");
			System.out.print(classtype+", ");
			System.out.print(seat_capacity+", ");
			System.out.println(getShows());
	    }
}
