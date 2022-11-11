package Moblima.Entities;
import java.util.ArrayList;

/**
 * Class to facilitate and store information for booking
 * @author Marcus Kho
 *
 */
public class Booking {
    private int studentTicket;
    private int adultTicket;
    private int seniorTicket;
    private Show selectedShow;
    private ArrayList<Seats> seatList;
    private User user;
    private double adultPrice;
    private double studentPrice;
    private double seniorPrice;

    /**
     * Constructor
     * @param user user object to keep track of which user book what shows
     */
    public Booking(User user){
        seatList = new ArrayList<>();
        studentTicket = 0;
        adultTicket = 0;
        studentPrice = 0.0;
        adultPrice = 0.0;
        selectedShow = null;
        this.user = user;
    }

    /**
     * Retrieve Total Price
     * @return total price
     */
    public double getTotalPrice(){
        return this.getAdultPrice() * this.getAdultTicketNum() + this.getStudentPrice() * this.getStudentTicketNum() + this.getSeniorPrice() * this.getSeniorTicket();
    }

    /**
     * Retrieve Adult Price
     * @return adult price
     */
    public double getAdultPrice(){
        return this.adultPrice;
    }

    /**
     * Retrieve Senior Citizen Price 
     * @return senior price
     */
    public double getSeniorPrice(){
        return this.seniorPrice;
    }
    /**
     * Set Senior Citizen Price 
     * @param seniorPrice update seniorPrice
     */
    public void setSeniorPrice(Double seniorPrice){
        this.seniorPrice = seniorPrice;
    }

    /**
     * Set adult price
     * @param price update adultPrice
     */
    public void setAdultPrice(Double price){
        this.adultPrice = price;
    }

    /**
     * Retrieve Student price
     * @return studentPrice
     */
    public double getStudentPrice(){
        return this.studentPrice;
    }

    /**
     * Set Student Price
     * @param price update student Price
     */
    public void setStudentPrice(Double price){
        this.studentPrice = price;
    }

    /**
     * Get User object
     * @return user
     */
    public User getUser(){
        return this.user;
    }

    /**
     * Retrieve number of Student Tickets
     * @return studentTicket
     */
    public int getStudentTicketNum(){
        return this.studentTicket;
    }

    /**
     * Sets number of Student Tickets
     * @param num update number of student tickets
     */
    public void setStudentTicket(int num){
        this.studentTicket = num;
    } 
    /**
     * Retrieve number of adult ticket
     * @return adult ticket count
     */
    public int getAdultTicketNum(){
        return this.adultTicket;
    }

    /**
     * Set number of Adult Ticket
     * @param num number of adult tickets
     */
    public void setAdultTicket(int num){
        this.adultTicket = num;
    } 

    /**
     * Set number of Senior Ticket
     * @param num number of senior citizen tickets
     */
    public void setSeniorTicket(int num){
        this.seniorTicket = num;
    }

    /**
     * Get number of Senior Citizen Ticket
     * @return number senior of ticket
     */
    public int getSeniorTicket(){
        return this.seniorTicket;
    }

    /**
     * Get total Number of tickets (Sum of Adult + Student + Senior)
     * @return total number of ticket
     */
    public int getTotalTicketNum(){
        return this.seniorTicket + this.adultTicket + this.studentTicket;
    }
    /**
     * Get the seats chosen for booking
     * @return seatlist
     */
    public ArrayList<Seats> getSeats() {
        return seatList;
    }

    /**
     * Set the seats chosen for booking
     * @param seats update seats
     */
    public void setSeats(ArrayList<Seats> seats){
        this.seatList = seats;
    }

    /**
     * Set show selected for booking
     * @param show update shows
     */
    public void setShow(Show show){
        this.selectedShow = show;
    }

    /**
     * Get selected show
     * @return selected show
     */
    public Show getShow(){
        return this.selectedShow;
    }

}