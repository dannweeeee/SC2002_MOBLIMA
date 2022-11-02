package Moblima;

import java.util.Date;

public class Show {
    private static int idCounter=0;
    private int id;
    private Date showTime;
    private Movie movie;
    private Cinema theater;
    private int availableSeats;

    public Show(Date showTime, Movie movie, Cinema theater) {
        idCounter += 1;
        this.id = idCounter;
        this.showTime = showTime;
        this.movie = movie;
        this.theater = theater;
        this.availableSeats = theater.getCapacity();
        theater.getShows().add(this);
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