package Moblima.Entities;

import java.util.Date;

/**
 * @author 
 *
 */
public class Show {
    private static int idCounter=0;
    private int id;
    private Date showTime;
    private Movie movie;
    private Cinema theater;
    private int availableSeats;

    /**
     * @param showTime
     * @param movie
     * @param theater
     */
    public Show(Date showTime, Movie movie, Cinema theater) {
        idCounter += 1;
        this.id = idCounter;
        this.showTime = showTime;
        this.movie = movie;
        this.theater = theater;
        this.availableSeats = theater.getCapacity();
    }
    
    /**
     * @return
     */
    public Cinema getCinema(){
        return this.theater;
    }

    /**
     * @return
     */
    public int getID(){
        return this.id;
    }

    /**
     * @return
     */
    public Movie getMovie() {
        return movie;
    }
    /**
     * @param theater
     */
    public void setTheater(Cinema theater) {
        this.theater = theater;
    }
    /**
     * @param availableSeats
     */
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    /**
     * @return
     */
    public int getAvailableSeats() {
        return availableSeats;
    }

    /**
     * @return
     */
    public Date getShowTime() {
        return showTime;
    }

    /**
     * @param date
     */
    public void setShowTime(Date date){
        this.showTime = date;
    }

    /**
     * @param user
     * @param seats
     * @param price
     * @return
     */
    public synchronized Ticket bookTicket(User user, Seats seats, double price){
        Ticket ticket = new Ticket();
        ticket.setOwner(user.getName());
        ticket.setBookedShow(this);
        ticket.setBookingTime(new Date());
        ticket.setSeat(seats);
        ticket.setPrice(price);
        user.addBooking(ticket);
        this.movie.addticket(ticket);
        return ticket;
    }
   
    /**
     *
     */
    @Override
    public String toString() {
        return 	"Show id = " + id +
        		"\nMovie = " + movie.getName() +
                "\nShow Time = " + showTime +
                "\nCinema = " + theater.getCinemaID() +
                "\nAvailable Seats = " + availableSeats + "\n\n"
                ;
    }
}
