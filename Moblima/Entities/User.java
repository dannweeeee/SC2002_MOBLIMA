package Moblima.Entities;

import java.util.ArrayList;
/**
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
     * @param name
     * @param email
     * @param number
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
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * @return
     */
    public String getEmail() {
        return email;
    }
    /**
     * @return
     */
    public int getNumber() {
        return number;
    }
    /**
     * @return
     */
    public ArrayList<Ticket> getTickets(){
    	return bookingHistory;
    }
}
