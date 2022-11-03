package Moblima;
import java.util.ArrayList;
import java.util.Scanner;

public class Booking {
    Scanner scanner = new Scanner(System.in);

    public Booking(){}

    // returns -1 if invalid input
    private int getIntUserInput(){
        int intInput = -1;
        String input = "";
        input = scanner.nextLine();
        try {
            intInput = Integer.parseInt(input);
        }catch (NumberFormatException e) {
            System.out.println("Invalid input: " + input + " is not a number");
            System.out.println("");
        }
        return intInput-1;
    }


    public void printAllShows(ArrayList<Show> showlist){
        Show temp;
        for (int i = 0; i < showlist.size(); i++){
            temp = showlist.get(i);
            System.out.println(temp.getID() + ". " + temp.getMovie().getName());
            System.out.println("Show time: " + temp.getShowTime());
            System.out.println("Location: " + temp.getTheater());
            System.out.println("");
        }
    }

    public void printAvailableSeats(Show showSelected){
        ArrayList<Seats> remainingSeats = showSelected.getSeatList(); //available seats
        
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
    
    public int getNumberOfTicket(){
        int numTickets = -2;
        while (true){
            System.out.println();
            System.out.print("How many tickets would you like to book? [0 to go back] => ");
            numTickets = getIntUserInput() + 1;
            if (numTickets >= 0) break;
        }
        return numTickets;
    }

    private boolean checkCapacity(ArrayList<Seats> capacityRemaining, int numOfTickets){
        if (numOfTickets > capacityRemaining.size()){
            System.out.println("Not enough tickets remaining");
            return false;
        }else{
            return true;
        }
    }

    private boolean checkSeatAvailability(Seats s1, ArrayList<Seats> seatList){
        boolean available = false;
                        
        for (Seats o : seatList){
            if (o.getSeat().equals(s1.getSeat())){
                available = true;
                break;
            }
        }
        return available;
    }

    public ArrayList<Seats> selectSeats(Show showSelected){
        ArrayList<Seats> remainingSeats = showSelected.getSeatList(); //available seats
        printAvailableSeats(showSelected);

        ArrayList<Seats> chosenSeats = new ArrayList<Seats>();
        while (true){
            int seatNum = getNumberOfTicket();
            if (seatNum == 0) return null;

            if (checkCapacity(remainingSeats, seatNum)){
                for(int j = 0; j < seatNum; j++){
                    while (true){
                        System.out.print("Seat number for Ticket " + Integer.toString(j+1) + ": ");
                        String seats = scanner.nextLine();

                        if (seats == "XX" || seats.length() != 2){
                            System.out.println("Invalid seat");
                            continue;
                        } else if (seats == "0") return null;

                        String row = String.valueOf(seats.charAt(0)).toUpperCase();
                        String col = String.valueOf(seats.charAt(1));
                        Seats s1 = new Seats(row, col);

                        if (checkSeatAvailability(s1, remainingSeats) && !checkSeatAvailability(s1, chosenSeats)){
                            chosenSeats.add(s1);
                            break;
                        } else{
                            System.out.println("Seat is not available, please try again");
                            System.out.println("Enter 0 to exit");
                        }
                        
                        if (chosenSeats.size() == seatNum) break;
                    }
                }
                break;
            }
        }
        return chosenSeats;
    }

    public int seatConfirmation(ArrayList<Seats> seatList, Show showSelected){
        while(true){
            System.out.print("Confirm ticket booking (Y/N): ");
            char confirmation = scanner.next().charAt(0);
            if (Character.toUpperCase(confirmation) == 'Y'){
                for (Seats s: seatList){
                    showSelected.removeSeats(s);
                }
                return 1;
            } else if (Character.toUpperCase(confirmation) == 'N'){
                System.out.println("Booking unsuccessful");
                System.out.println("Returning back to main menu");
                return 0;
            } else {
                System.out.println("Enter only Y or N");
            }
        }
    }

    public ArrayList<Show> listAllShows(ArrayList<Cineplex> cineplexes){
        ArrayList <Show> allShows = new ArrayList<>();
        for (Cineplex c : cineplexes){
            for (Cinema theater : c.getHall()){
                allShows.addAll(theater.getShows());
            }
        }
        return allShows;
    }

    public Show selectShow(ArrayList<Show> allShows){
        while (true){
            System.out.print("Please enter the show number to watch (1 - " + allShows.size() + ") [0 to exit] => ");
            int choice = getIntUserInput();
            
            if (choice == -1){
                break;
            } else if (choice >= allShows.size()){
                System.out.println("Input out of range");
            } else if(choice <= -1){
                //pass
            } else {
                return allShows.get(choice);
            }
        }
        return null;
    }

    public ArrayList<Ticket> bookSeats(Show showSelected, User user1){
        ArrayList<Seats> seatlist = selectSeats(showSelected);
        int confirmation = 0;
        if (seatlist != null){
            confirmation = seatConfirmation(seatlist, showSelected);
        }
        ArrayList<Ticket> ticketList = new ArrayList<>();
        if (confirmation == 1){
            for(Seats s: seatlist) ticketList.add(showSelected.bookTicket(user1, s));
        }
        return ticketList;
    }

    public ArrayList<Show> searchShows(ArrayList<Cineplex> cineplexes, String searchString){
        ArrayList <Show> allShows = new ArrayList<>();
        for (Cineplex c : cineplexes){
            for (Cinema theater : c.getHall()){
                for (Show show : theater.getShows()){
                    if (show.getMovie().getName().contains(searchString)){
                        allShows.add(show);
                    }
                }
            }
        }
        return allShows;
    }

    public void printAllLocation(ArrayList<Cineplex> cineplexes){
        System.out.println("All Locations: ");
        for (int i = 0; i < cineplexes.size(); i++){
            Cineplex temp = cineplexes.get(i);
            System.out.println(Integer.toString(i+1) + ". " + temp.getLocation());
        }
    }

    public int getCineplex(ArrayList<Cineplex> cineplexes){
        printAllLocation(cineplexes);
        System.out.print("Enter Cineplex to book [0 to exit] => ");
        int choice = getIntUserInput();

        return choice;
    }
}

