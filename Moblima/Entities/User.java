package Moblima.Entities;

import java.util.ArrayList;
/**
 * User class 
 * @author Ian
 * @version 1.0
 */
public class User {
    private String name;
    private String email;
    private int number;
    private ArrayList<Ticket> bookingHistory;

    /**
     * Constructor for User
     * @param name name of user
     * @param email email of user
     * @param number mobile number of user
     */
    public User(String name,String email, int number) {
        this.name = name;
        this.email=email;
        this.number=number;
        this.bookingHistory= new ArrayList<>();
    }

    /**
     * add booking to user
     * @param t ticket to be added
     */
    public void addBooking(Ticket t){
        this.bookingHistory.add(t);
    }

    /**
     * Get method for name of user
     * @return name 
     */
    public String getName() {
        return name;
    }
    /**
     * Get method for email of user
     * @return email 
     */
    public String getEmail() {
        return email;
    }
    /**
     * Get method for phone number of user
     * @return number 
     */
    public int getNumber() {
        return number;
    }
    /**
     * Get method for Array of tickets purchased by user
     * @return bookingHistory
     */
    public ArrayList<Ticket> getTickets(){
    	return bookingHistory;
    }
}
