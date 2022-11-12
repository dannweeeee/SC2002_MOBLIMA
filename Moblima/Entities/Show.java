package Moblima.Entities;

import java.util.Date;

import Moblima.Utils.UtilityOutput;

/**
 * Show class
 * @author Team
 * @version 1.0
 */
public class Show {
    private static int idCounter=0;
    private int id;
    private Date showTime;
    private Movie movie;
    private Cinema theater;
    private int availableSeats;

    /**
     * Constructor for Show
     * @param showTime showtime of show
     * @param movie movie object for creating show
     * @param theater Cinema it is showing
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
     * Get Method for Cinema
     * @return cinema object
     */
    public Cinema getCinema(){
        return this.theater;
    }

    /**
     * Get method for show ID
     * @return id for show
     */
    public int getID(){
        return this.id;
    }

    /**
     * Get method for Movie
     * @return movie object of show
     */
    public Movie getMovie() {
        return movie;
    }
    /**
     * Set method for cinema
     * @param theater updated object of Cinema
     */
    public void setTheater(Cinema theater) {
        this.theater = theater;
    }
    /**
     * Set method for available seats for shows
     * @param availableSeats updated value of seats
     */
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    /**
     * Get method for avaiable seats
     * @return number of available seats
     */
    public int getAvailableSeats() {
        return availableSeats;
    }

    /**
     * Get method for show time
     * @return date of showtime
     */
    public Date getShowTime() {
        return showTime;
    }

    /**
     * Set method for show time
     * @param date date of show time
     */
    public void setShowTime(Date date){
        this.showTime = date;
    }

    /**
     * Booking of ticket
     * @param user user that owns the tickets
     * @param seats seats booked by the user
     * @param price price paid by the user
     * @return ticket 
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
     * To String method for Show
     * @return details of show in string
     */
    @Override
    public String toString() {
        return 	id + ". " + movie.getName() +
        		"\nShow time: " + showTime +
        		"\nHall Type: " + theater.getCinemaClass() +
        		"\nAvailable Seats: " + availableSeats +
        		"\nLocation: " + theater.getCineplex().getLocation() + "\n"
        		;
          }
}
