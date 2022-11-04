package Moblima;

import java.util.Date;

public class Show {
    private static int idCounter=0;
    private int id;
    private Date showTime;
    private Movie movie;
    private cinema theater;
    private int availableSeats;

    public Show(Date showTime, Movie movie, cinema theater) {
        idCounter += 1;
        this.id = idCounter;
        this.showTime = showTime;
        this.movie = movie;
        this.theater = theater;
        this.availableSeats = theater.getCapacity();
        theater.getShows().add(this);
    }
<<<<<<< Updated upstream
=======
    
    public ArrayList<Seats> getSeatList(){
        return this.seats;
    }

    public int getID(){
        return this.id;
    }

    public void printAvailableSeats(){
        ArrayList<Seats> remainingSeats = this.seats; //available seats
        
        System.out.println("Seats still available: ");
        int i = 0;

        for(Seats seat : remainingSeats){
            System.out.print(seat.getSeat() + " ");
            if((i+1) % 10 == 0 && i != 0){
                System.out.println("");
            }
            i++;
        }
    }

    public boolean checkSeatAvailability(Seats s1){
        boolean available = false;
                        
        for (Seats o : this.seats){
            if (o.getSeat().equals(s1.getSeat())){
                available = true;
                break;
            }
        }
        return available;
    }

    public boolean checkCapacity(int numOfTickets){
        if (numOfTickets > this.availableSeats){
            System.out.println("Not enough tickets remaining");
            return false;
        }else{
            return true;
        }
    }
    
    public void removeSeats(Seats seat){
        System.out.println("removing seat: " + seat.getSeat());
        for (Seats s : this.seats){
            if (s.getSeat().equals(seat.getSeat())){
                s.setRow("X");
                s.setCol("X");
                availableSeats--;
                break;
            }
        }
    }

    public ArrayList<Seats> seatList(){
        ArrayList<Seats> allseats = new ArrayList<Seats>();
        int cols = 10;
        int rows = this.availableSeats / cols;
        int remaining = this.availableSeats - (rows * cols);

        for (int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                Seats temp = new Seats(String.valueOf((char)(i + 65)), Integer.toString(j));
                allseats.add(temp);
            }
        }
        for(int x = 0; x < remaining; x++){
            allseats.add(new Seats(String.valueOf((char)(rows + 65)), Integer.toString(x)));
        }

        return allseats;
    }

>>>>>>> Stashed changes

    public Movie getMovie() {
        return movie;
    }
    public void setTheater(cinema theater) {
        this.theater = theater;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public void updateShow(){
    }
<<<<<<< Updated upstream
    public synchronized Ticket bookTicket(User user, int seats){
        if(availableSeats >= seats && seats >0){
            Ticket ticket = new Ticket();
            availableSeats -= seats;
            ticket.setOwner(user.getName());
            ticket.setBookedShow(this);
            ticket.setBookingTime(new Date());
            ticket.setNumberOfSeats(seats);
            System.out.println("Successfully booked");
            user.bookingHistory.add(ticket);
            return ticket;
        }
        else{
            System.out.println("Seats not Available");
            return null;
        }
=======
    public synchronized Ticket bookTicket(User user, Seats seats){
        Ticket ticket = new Ticket();
        ticket.setOwner(user.getName());
        ticket.setBookedShow(this);
        ticket.setBookingTime(new Date());
        ticket.setSeat(seats);
        System.out.println("Successfully booked");
	System.out.println("Successfully booked"+ movie.getName()+" time: "+ this.getShowTime());
        user.bookingHistory.add(ticket);
        this.movie.addticket(ticket);
        return ticket;
>>>>>>> Stashed changes
    }
   
    @Override
    public String toString() {
        return "Show{" +
                "Show " + id +
                ", showTime=" + showTime +
                ", movie=" + movie.getName() +
                ", theater=" + theater.getcinemaid() +
                ", availableSeats=" + availableSeats +
                '}';
    }
}