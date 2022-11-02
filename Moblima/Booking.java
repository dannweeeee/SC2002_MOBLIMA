package Moblima;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Booking {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Cinema> AllCinemas;

    public Booking(){}

    public void printAllCinemas(){
        for (int i = 0; i < AllCinemas.size(); i++){
            System.out.println(Integer.toString(i+1) + ". " + Integer.toString(AllCinemas.get(i).getCinemaID()));
        }
    }

    public int getIntUserInput(){
        int choice = scanner.nextInt() - 1;
        scanner.nextLine();
        return choice;
    }

    public Cinema SelectCinema(Cineplex test){
        Cinema cinema = null;

        try {
            System.out.println("Select Cinema: ");
            AllCinemas = test.getHall();
            
            printAllCinemas();
            int choice = getIntUserInput();

            if (choice > AllCinemas.size() || choice <= -1) {
                throw new CinemaDoesNotExistException("Cinema does not exist");
            } else {
                cinema = AllCinemas.get(choice);
            }
        } catch (CinemaDoesNotExistException e) {
            cinema = SelectCinema(test);
        }
        return cinema;
    }

    public void printAllShows(ArrayList<Show> showlist){
        Show temp;
        if (showlist.size() >= 1){
            for (int i = 0; i < showlist.size(); i++){
                temp = showlist.get(i);
                System.out.println(temp.getMovie().getName() + " " + temp.getShowTime());
            }
        } else{
            System.out.println("There is no shows available yet");
        }
    }


    public Show selectShows(Cinema cinemaSelected){
        Show choice = null;
        ArrayList<Show> showlist = cinemaSelected.getShows();

        System.out.println("Please select a show: ");
        
        int i = scanner.nextInt() - 1;
        scanner.nextLine();

        if(i > showlist.size() || i <= -1){
            //invalid
        } else{
            choice = showlist.get(i);
        }
        return choice;
    }

    public Date selectShowTime(Show selectedShow){
        return selectedShow.getShowTime();
    }

    public void printAvailableSeats(Cinema cinemaSelected){
        ArrayList<Seats> remainingSeats = cinemaSelected.getSeatList(); //available seats
        
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

    public ArrayList<Seats> selectSeats(Cinema cinemaSelected){
        ArrayList<Seats> remainingSeats = cinemaSelected.getSeatList(); //available seats
        printAvailableSeats(cinemaSelected);

        ArrayList<Seats> chosenSeats = new ArrayList<Seats>();
        while (true){
            System.out.println();
            System.out.println("How many tickets would you like to book? (Enter 0 to go back)");
            int seatNum = scanner.nextInt();
            scanner.nextLine();

            if (seatNum > remainingSeats.size()){
                System.out.println("Not enough tickets remaining");
            }else if (seatNum == 0) {
                return null;
            }else{
                for(int j = 0; j < seatNum; j++){
                    while (true){
                        System.out.print("Seat number for Ticket " + Integer.toString(j+1) + ": ");
                        String seats = scanner.nextLine();
                        if (seats == "XX"){
                            System.out.println("Invalid seat");
                            continue;
                        }
                        if (seats == "0") break;

                        String row = String.valueOf(seats.charAt(0));
                        String col = String.valueOf(seats.charAt(1));
                        Seats s1 = new Seats(row, col);
                        
                        boolean available = false;
                        
                        for (Seats o : remainingSeats){
                            if (o.getSeat().equals(s1.getSeat())){
                                available = true;
                                break;
                            }
                        }

                        if (available){
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

    public int seatConfirmation(ArrayList<Seats> seatList, Cinema cinemaSelected){
        while(true){
            System.out.print("Confirm ticket booking (Y/N): ");
            char confirmation = scanner.next().charAt(0);
            if (Character.toUpperCase(confirmation) == 'Y'){
                for (Seats s: seatList){
                    cinemaSelected.removeSeats(s);
                }
                printAvailableSeats(cinemaSelected);
                return 1;
            } else if (Character.toUpperCase(confirmation) == 'N'){
                System.out.println("Returning back to main menu");
                return 0;
            } else {
                System.out.println("Enter only Y or N");
            }
        }
    }
    
    public ArrayList<Ticket> menu(Cineplex test){
        User user1 = new User("Name", "email@test.com", 12345678);
        System.out.println("Which show would you like to book");
        test.generateMovieMap();
        test.printMovieMap();
        Cinema cinemaSelected = SelectCinema(test);
        Show selectedShow = selectShows(cinemaSelected);
        ArrayList<Seats> seatlist = selectSeats(cinemaSelected);
        int confirmation = seatConfirmation(seatlist, cinemaSelected);

        ArrayList<Ticket> ticketList = new ArrayList<>();
        if (confirmation == 1){
            //return seat list for ticket
            for(Seats s: seatlist) ticketList.add(selectedShow.bookTicket(user1, s));
        }
        return ticketList;
    }
}