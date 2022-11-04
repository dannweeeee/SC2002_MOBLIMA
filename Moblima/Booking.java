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
        scanner = new Scanner(System.in);
        input = scanner.nextLine();
        try {
            intInput = Integer.parseInt(input);
        }catch (NumberFormatException e) {
            System.out.println("Invalid input: " + input + " is not a number");
            System.out.println("");
        }
        return intInput;
    }
    
    public int getNumberOfTicket(){
        int numTickets = -1;
        while (true){
            System.out.println();
            System.out.print("How many tickets would you like to book? [0 to go back] => ");
            numTickets = getIntUserInput();
            if (numTickets >= 0) break;
        }
        return numTickets;
    }

    private boolean duplicateInput(Seats s1, ArrayList<Seats> chosenSeats){
        boolean duplicate = false;
                        
        for (Seats o : chosenSeats){
            if (o.getSeat().equals(s1.getSeat())){
                duplicate = true;
                break;
            }
        }
        return duplicate;
    }

    public ArrayList<Seats> selectSeats(Show showSelected){
        showSelected.printAvailableSeats();

        ArrayList<Seats> chosenSeats = new ArrayList<Seats>();
        while (true){
            int seatNum = getNumberOfTicket();
            if (seatNum == 0) return null;

            if (showSelected.checkCapacity(seatNum)){
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

                        if (showSelected.checkSeatAvailability(s1) && !duplicateInput(s1, chosenSeats)){
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

    public Show selectShow(ArrayList<Show> allShows){
        while (true){
            System.out.print("Please enter the show number to watch (1 - " + allShows.size() + ") [0 to exit] => ");
            int choice = getIntUserInput();
            
            if (choice == 0){
                break;
            } else if (choice > allShows.size() || choice < 0){
                System.out.println("Input out of range");
            } else {
                for(Show s: allShows){
                    if(s.getID() == choice){
                        return s;
                    }
                }
                System.out.println("Invalid input, please try again");
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
    
}