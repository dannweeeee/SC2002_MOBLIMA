package Moblima.Entities;

import java.util.Date;

/**
 * Ticket class
 * @author Ian
 * @version 1.0
 */
public class Ticket {
	 private static int idCounter=0;
	    private int id;
	    private String owner;
	    private Date bookingTime;
	    private Seats seat_no;
	    private Show bookedShow;
		private Cinema cinemaSelected;
		private Double price;

	    /**
	     * Default Constructor of ticket
	     */
	    public Ticket() {
	        idCounter += 1;
	        this.id = idCounter;
	    }

		/**
		 * Set method for price of ticket
		 * @param price updated value of price
		 * */
		public void setPrice(Double price){
			this.price = price;
		}

		/**
		 * Get method for price of ticket
		 * @return price of ticket 
		 */
		public Double getPrice(){
			return this.price;
		}
		

		/**
		 * Set method for cinemaSelected for ticket
		 * @param cinemaSelected updated object for cinema
		 */
		public void setCinema(Cinema cinemaSelected){
			this.cinemaSelected = cinemaSelected;
		}

		/**
		 * Get method for Cinema of ticket
		 * @return cinemaSelected
		 */
		public Cinema getCinema(){
			return cinemaSelected;
		}


	    /**
	     * Get method for name of owner of ticket
	     * @return owner 
	     */
	    public String getOwner() {
	        return owner;
	    }

	    /**
	     * Set method for name of owner of ticket
	     * @param owner update ownder of ticket
	     */
	    public void setOwner(String owner) {
	        this.owner = owner;
	    }

	    /**
	     * Get method for ticket id
	     * @return id
	     */
	    public int getId() {
	        return id;
	    }

	    /**
	     * Set method for ticket id
	     * @param id updated id of ticket
	     */
	    public void setId(int id) {
	        this.id = id;
	    }

	    /**
	     * Get method for bookingTime of ticket
	     * @return bookingTime
	     */
	    public Date getBookingTime() {
	        return bookingTime;
	    }

	    /**
	     * Set method for bookingTime of ticket
	     * @param bookingTime updated booking time of the ticket
	     */
	    public void setBookingTime(Date bookingTime) {
	        this.bookingTime = bookingTime;
	    }

	    /**
	     * Get method for seat number of ticket
	     * @return seat_no
	     */
	    public Seats getSeat() {
	        return seat_no;
	    }

	    /**
	     * Set method for seat number of ticket
	     * @param seat updated object of seat
	     */
	    public void setSeat(Seats seat) {
	        this.seat_no = seat;
	    }

	    /**
	     *String format for ticket
	     */
	    @Override
	    public String toString() {
	        return "Ticket{" +
	                "owner='" + owner  +
	                ", bookingTime=" + bookingTime +
	                ", Seats booked=" + seat_no +
	                ", bookedShow=" + bookedShow +
	                '}';
	    }

	    /**
	     * Get method for bookedShow
	     * @return bookedShow
	     */
	    public Show getBookedShow() {
	        return bookedShow;
	    }

	    /**
	     * Set method for bookedShow 
	     * @param bookedShow updated object of show
	     */
	    public void setBookedShow(Show bookedShow) {
	        this.bookedShow = bookedShow;
	    }
}
