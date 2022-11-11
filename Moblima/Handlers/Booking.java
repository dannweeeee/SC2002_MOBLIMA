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

    public double getTotalPrice(){
        return this.getAdultPrice() * this.getAdultTicketNum() + this.getStudentPrice() * this.getStudentTicketNum() + this.getSeniorPrice() * this.getSeniorTicket();
    }

    /**
     * @return
     */
    public double getAdultPrice(){
        return this.adultPrice;
    }

    public double getSeniorPrice(){
        return this.seniorPrice;
    }

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
     * @return
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
     * @return
     */
    public User getUser(){
        return this.user;
    }

    /**
     * @return
     */
    public int getStudentTicketNum(){
        return this.studentTicket;
    }

    /**
     * @param num
     */
    /**
     * @param num
     */
    public void setStudentTicket(int num){
        this.studentTicket = num;
    } 

    /**
     * @return
     */
    /**
     * @return
     */
    public int getAdultTicketNum(){
        return this.adultTicket;
    }

    /**
     * @param num
     */
    /**
     * @param num
     */
    public void setAdultTicket(int num){
        this.adultTicket = num;
    } 

    public void setSeniorTicket(int num){
        this.seniorTicket = num;
    }

    public int getSeniorTicket(){
        return this.seniorTicket;
    }

    public int getTotalTicketNum(){
        return this.seniorTicket + this.adultTicket + this.studentTicket;
    }

    /**
     * @return
     */
    /**
     * @return
     */
    public ArrayList<Seats> getSeats() {
        return seatList;
    }

    /**
     * @param seats
     */
    /**
     * @param seats
     */
    public void setSeats(ArrayList<Seats> seats){
        this.seatList = seats;
    }

    /**
     * @param show
     */
    /**
     * @param show
     */
    public void setShow(Show show){
        this.selectedShow = show;
    }

    /**
     * @return
     */
    /**
     * @return
     */
    public Show getShow(){
        return this.selectedShow;
    }

}