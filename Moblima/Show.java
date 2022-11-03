package Moblima;

import java.util.Date;
import java.util.ArrayList;

public class Show {
    private static int idCounter=0;
    private int id;
    private Date showTime;
    private Movie movie;
    private Cinema theater;
    private int availableSeats;
    private ArrayList<Seats> seats;

    public Show(Date showTime, Movie movie, Cinema theater) {
        idCounter += 1;
        this.id = idCounter;
        this.showTime = showTime;
        this.movie = movie;
        this.theater = theater;
        this.availableSeats = theater.getCapacity();
        this.seats = seatList();
        theater.getShows().add(this);
    }
    
    public ArrayList<Seats> getSeatList(){
        return this.seats;
    }

    public int getID(){
        return this.id;
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

    public ArrayList<Seats> seatList(){
        ArrayList<Seats> allseats = new ArrayList<Seats>();
        int cols = 10;
        int rows = this.availableSeats / cols;
        int remaining = this.availableSeats - (rows * cols);

        for (int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                Seats temp = new Seats(String.valueOf((char)(i + 65)), Integer.toString(j));
                allseats.add(temp);
            }
        }
        for(int x = 0; x < remaining; x++){
            allseats.add(new Seats(String.valueOf((char)(rows + 65)), Integer.toString(x)));
        }

        return allseats;
    }


    public Movie getMovie() {
        return movie;
    }
    public void setTheater(Cinema theater) {
        this.theater = theater;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }

    public Date getShowTime() {
        return showTime;
    }
    public void updateShow(){
    }
    public synchronized Ticket bookTicket(User user, Seats seats){
        Ticket ticket = new Ticket();
        ticket.setOwner(user.getName());
        ticket.setBookedShow(this);
        ticket.setBookingTime(new Date());
        ticket.setSeat(seats);
        System.out.println("Successfully booked");
        user.bookingHistory.add(ticket);
        this.movie.addticket(ticket);
        return ticket;
    }
   
    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", showTime=" + showTime +
                ", movie=" + movie.getName() +
                ", theater=" + theater.getCinemaID() +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
