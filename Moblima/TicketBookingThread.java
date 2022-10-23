package Moblima;

public class TicketBookingThread{
	 private Show show;
	    private User user;
	    private int numberOfSeats;
	    private Ticket ticket;

	    public TicketBookingThread(Show show, User user, int numberOfSeats) {
	        this.show = show;
	        this.user = user;
	        this.numberOfSeats = numberOfSeats;
	        this.ticket = show.bookTicket(user,numberOfSeats);
	    
	    }

	        

	    public Ticket getTicket() {
	        return ticket;
	    }
}
