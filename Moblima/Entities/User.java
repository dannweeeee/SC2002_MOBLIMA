package Moblima.Entities;

import java.util.ArrayList;
/**
 * User class 
 * @author Ian
 *
 */
public class User {
	private static int idCounter=0;
    private int id;
    private String name;
    private String email;
    private int number;
    public ArrayList<Ticket> bookingHistory;

    /**
     * @param name of user
     * @param email of user
     * @param number of user
     */
    public User(String name,String email, int number) {
        idCounter += 1;
        this.id = idCounter;
        this.name = name;
        this.email=email;
        this.number=number;
        this.bookingHistory= new ArrayList<>();
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
