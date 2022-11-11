package Moblima.Entities;
import java.util.ArrayList;

/**
 * @author 
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
     * @param user
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
     * @return total price
     */
    public double getTotalPrice(){
        return this.getAdultPrice() * this.getAdultTicketNum() + this.getStudentPrice() * this.getStudentTicketNum() + this.getSeniorPrice() * this.getSeniorTicket();
    }

    /**
     * @return adult price
     */
    public double getAdultPrice(){
        return this.adultPrice;
    }

    /**
     * @return senior price
     */
    public double getSeniorPrice(){
        return this.seniorPrice;
    }
    /**
     * @param seniorPrice
     */
    public void setSeniorPrice(Double seniorPrice){
        this.seniorPrice = seniorPrice;
    }

    /**
     * @param price
     */
    public void setAdultPrice(Double price){
        this.adultPrice = price;
    }

    /**
     * @return studentPrice
     */
    public double getStudentPrice(){
        return this.studentPrice;
    }

    /**
     * @param price
     */
    public void setStudentPrice(Double price){
        this.studentPrice = price;
    }

    /**
     * @return user
     */
    public User getUser(){
        return this.user;
    }

    /**
     * @return studentTicket
     */
    public int getStudentTicketNum(){
        return this.studentTicket;
    }

    /**
     * @param num
     */
    public void setStudentTicket(int num){
        this.studentTicket = num;
    } 
    /**
     * @return adult ticket count
     */
    public int getAdultTicketNum(){
        return this.adultTicket;
    }

    /**
     * @param number of ticket
     */
    public void setAdultTicket(int num){
        this.adultTicket = num;
    } 

    /**
     * @param number of ticket
     */
    public void setSeniorTicket(int num){
        this.seniorTicket = num;
    }

    /**
     * @return number senior of ticket
     */
    public int getSeniorTicket(){
        return this.seniorTicket;
    }

    /**
     * @param total number of ticket
     */
    public int getTotalTicketNum(){
        return this.seniorTicket + this.adultTicket + this.studentTicket;
    }
    /**
     * @return seatlist
     */
    public ArrayList<Seats> getSeats() {
        return seatList;
    }

    /**
     * @param seats
     */
    public void setSeats(ArrayList<Seats> seats){
        this.seatList = seats;
    }

    /**
     * @param show
     */
    public void setShow(Show show){
        this.selectedShow = show;
    }

    /**
     * @return selected show
     */
    public Show getShow(){
        return this.selectedShow;
    }

}