package Moblima;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
<<<<<<< Updated upstream
import java.util.HashMap;
public class BookMyShow {
	

    public static void main(String[] args) {
        /* --------Data generation code ----START ----------------- */


        // Creating Registered User --> Ayush
        User ayush = new User("Ayush","ayus@gmail.com",3293131);


        // Creating Movie object --> Iron Man
        Movie ironMan = new Movie("Iron Man","showing","Jon Favreaue");

        // Creating Movie object --> Avengers: End Game
        Movie avengers = new Movie("Avengers: End Game", "showing","Jon Favreaue");

        // Creating Movie object --> The Walk To Remember
        Movie walkToRemember = new Movie("The Walk To Remember", "showing","Jon Favreaue");

        // Creating Movie object --> HouseFull2
        Movie housefull = new Movie("HouseFull 2", "showing","Jon Favreaue");

        // Creating Theater --> PVR @ GIP Noida with capacity 30
        cinema hall1 = new cinema("standard",30);

        // Creating Another Theater --> BIG Cinema @ Noida Sector 137 with capacity 40
        cinema hall2 = new cinema("platinum",40);




        // Creating four shows for movies
        Show show1=null, show2=null, show3=null, show4=null;
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");

        try {
            // Creating Show for Movie Iron Man on 7 Jun 2020 @ 9:00 AM in hall1
            String dateInString = "Friday, Jun 7, 2020 09:00:00 AM";
            Date date = formatter.parse(dateInString);
            show1 = new Show(date,ironMan,hall1);

            // Creating Show for Movie HOUSEFULL on 7 Jun 2020 @ 12:00 PM in hall1
            dateInString = "Friday, Jun 7, 2020 12:00:00 PM";
            date = formatter.parse(dateInString);
            show2 = new Show(date,housefull,hall1);

            // Creating Show for Movie WALK TO REMEMBER on 7 Jun 2020 @ 09:00 AM in hall2
            dateInString = "Friday, Jun 7, 2020 09:00:00 AM";
            date = formatter.parse(dateInString);
            show3 = new Show(date,walkToRemember,hall2);

            // Creating Show for Movie WALK TO REMEMBER on 7 Jun 2020 @ 12:00 PM in hall2
            dateInString = "Friday, Jun 7, 2020 12:00:00 PM";
            date = formatter.parse(dateInString);
            show4 = new Show(date,walkToRemember,hall2);

            } catch (ParseException e) {
                    e.printStackTrace();
        }

        /* --------Data generation code ---- END ----------------- */

        // Now We have two theaters with their shows, lets add these theaters to our Book My Show app
        ArrayList<cinema> theaterArrayList= new ArrayList<>();
        theaterArrayList.add(hall1);
        theaterArrayList.add(hall2);
        Cinemplex GoldenVillageJP= new Cinemplex(theaterArrayList,"Jurong Point");
        

        // Searching Book My Show for all the shows of movie WALK TO REMEMBER
        ArrayList<Show> searchedShow = GoldenVillageJP.searchShows("The Walk To Remember");
        // Above code returns two shows of WALK TO REMEMBER
        /* 
        searchedShow --> [Show{id=3, showTime=Sun Jun 07 09:00:00 IST 2020, 
        movie=The Walk To Remember, theater=Big Cinema, availableSeats=40}, 
        Show{id=4, showTime=Sun Jun 07 12:00:00 IST 2020, movie=The Walk To Remember, 
        theater=Big Cinema, availableSeats=40}]
        */
        // Now suppose AYUSH and SAURABH both wants to book 10 tickets each for first show
        // Then Book My show will create two Ticket Booking threads for their requests

        Show bookingShow = searchedShow.get(0);

        // Ticket Booking Thread for the request made by AYUSH for 10 Seats
        TicketBookingThread t1 = new TicketBookingThread(bookingShow,ayush,10);

       
     
        System.out.println(t1.getTicket());
        

  
 
        
        

    }
=======
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookMyShow implements BookMyShowInterface{
	
	private movieHandler movieHandler;
	private cineplexHandler cineplexHandler;
	private cinemaHandler cinemaHandler;
	private Cinema cinema;
	private Cineplex cineplex;
	private UserHandler userhandler;
	private Scanner in;
	SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
	Scanner scanner = new Scanner(System.in);
	
	public BookMyShow() {
		movieHandler = new movieHandler();
		cineplexHandler = new cineplexHandler();
		userhandler= new UserHandler();
		in = new Scanner(System.in);
	}
	
	public void showMovies() {}
	
	public void initializeExample() {
		cineplexHandler.addCineplex("JurongPoint");
		//cineplexHandler.addCineplex("Yishun");
		cinemaHandler JurongPoint = new cinemaHandler("JurongPoint");
		JurongPoint.addCinema("Standard", 35);
		JurongPoint.addCinema("Platinum", 10);
		cineplexHandler.getAllCineplex().get(cineplexHandler.getSize()-1).setHall(JurongPoint);
		JurongPoint.addCinema("VIP", 1);
		
		User ayush = new User("Ayush","ayus@gmail.com",3293131);
        
        //Movie ironMan = new Movie("Iron Man","showing","Jon Favreaue", movieHandler);
        //Movie avengers = new Movie("Avengers: End Game", "showing","Jon Favreaue", movieHandler);

		Movie ironMan = new Movie("Iron Man","showing","Jon Favreaue","AAA", "Example Cast...", movieHandler);
        Movie avengers = new Movie("Avengers: End Game", "showing","Jon Favreaue","BBB", "Example Cast...", movieHandler);
        String dateInString = "Friday, Jun 7, 2020 09:00:00 AM";
		try {
			Date date = formatter.parse(dateInString);
			Show show1 = new Show(date,ironMan,cineplexHandler.getAllCineplex().get(0).getHall().get(0));
			Show show2 = new Show(date,avengers,cineplexHandler.getAllCineplex().get(0).getHall().get(0));
			Show show3 = new Show(date,avengers,cineplexHandler.getAllCineplex().get(0).getHall().get(1));
			Show show4 = new Show(date,avengers,cineplexHandler.getAllCineplex().get(0).getHall().get(2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
	}
	
	public void showExample() {
		cineplexHandler.printAllCineplex();
		System.out.println("Show all cinemas:");
		cineplexHandler.getAllCineplex().get(0).printAllCinema();
	}
	
	public void showAllMovies() {
		System.out.println("Show all Movies");
		int count =1;
		for (Movie temp : movieHandler.getMovie()) {
			System.out.print(count+": ");
			temp.printMovieDetails();
			count++;
		}
	}
	
	public void readMovieFromTextFile(String fileName) throws FileNotFoundException{
	    	FileReader movieDatabase = new FileReader(fileName);
	    	Scanner read = new Scanner(movieDatabase);
	    	read.useDelimiter("\\||\\r\\n");
	    	read.nextLine();
	    	String movieName, movieStatus, movieDirector, movieSynopsis, movieCast;
	    	Movie newMovie;
	    	while(read.hasNext()) {
	    		//read.next();
	    		movieName = read.next();
	    		movieStatus = read.next();
	    		movieDirector = read.next();
	    		movieSynopsis = read.next();
	    		movieCast = read.next();
	    		newMovie = new Movie(movieName, movieStatus, movieDirector, movieSynopsis, movieCast, movieHandler);
	    	}
	    	read.close();
    }

	public User getUserInformation(){
        System.out.print("Please enter your name: ");
        String name = in.nextLine();

        System.out.print("Please enter your email: ");
        String email = in.nextLine();

        System.out.print("Please enter your number: ");
        double number = in.nextDouble();

        return new User(name, email, number);
    }
	
	public void BookMovie() {
		int booking_option = 0;
		Booking newBooking = new Booking();
		ArrayList<Show> allShows = null;
		ArrayList<Ticket> tickets = null;
		User user1 = getUserInformation();
		userhandler.getUsers().add(user1);
		do{
			System.out.println("--------------MOBLIMA BOOKING MENU!--------------");
			System.out.println("| 01: List All Shows                            |");
			System.out.println("| 02: Search Shows by Name                      |");
			System.out.println("| 03: View Movie by Location                    |");
			System.out.println("| 04: Go Back                                   |");
			System.out.println("-------------------------------------------------");
			System.out.print("Enter option ('4' to return):");

			booking_option = in.nextInt();
			switch(booking_option){
				case 1:
					allShows = cineplexHandler.getAllShows();
					tickets = book(allShows, newBooking, user1);
					break;
				case 2:
					System.out.print("Enter movie name to search [0 to exit] => ");
					Scanner newScanner = new Scanner(System.in);
					String searchString = newScanner.nextLine();
					searchString = searchString.substring(0,1).toUpperCase() + searchString.substring(1).toLowerCase();
					allShows = searchShows(searchString);
					tickets = book(allShows, newBooking, user1);
					break;
				case 3:
					printAllLocation();
        			System.out.print("Enter Cineplex to book [0 to exit] => ");
					in.nextLine();
        			String userInput = in.nextLine();
					try{
						int choice = Integer.parseInt(userInput);
						allShows = cineplexHandler.getAllCineplex().get(choice-1).getAllShows();
						tickets = book(allShows, newBooking, user1);
					} catch (NumberFormatException e){
						System.out.println("Invalid input: " + userInput + " is not a number");
            			System.out.println("");
						break;
					}
					break;
				case 4:
					break;
				default:
					System.out.println("Invalid Input");
			}
		} while(booking_option != 4);
	}

	public ArrayList<Ticket> book(ArrayList<Show> allShows, Booking newBooking, User user1){
		if (allShows != null){
			printAllShows(allShows);
			Show showSelected = newBooking.selectShow(allShows);
			if (showSelected != null) return newBooking.bookSeats(showSelected, user1);
		}
		return null;
	} 

	public void showAllMoviesTicket() {
		System.out.println("Sort Movies by: 1.Ticket sales, 2.Ratings");
		int sort = scanner.nextInt();
        scanner.nextLine();
        if(sort==1) {
        	movieHandler.SortbyTicketSales();
        }
        else {movieHandler.SortbyRatings();}
		int count =1;
		for (Movie temp : movieHandler.getMovie()) {
			System.out.print(count+": ");
			temp.printMovieDetails();
			count++;
		}
	}

	public void printAllLocation(){
        System.out.println("All Locations: ");
        for (int i = 0; i < cineplexHandler.getSize(); i++){
            Cineplex temp = cineplexHandler.getAllCineplex().get(i);
            System.out.println(Integer.toString(i+1) + ". " + temp.getLocation());
        }
    }

	public void printAllShows(ArrayList<Show> showlist){
		 for (Cineplex c : cineplexHandler.getAllCineplex()){
			 			System.out.println("Shows at Location: " + c.getLocation());
						c.printMovieMap();
                 
             }
         }
		

        /*Show temp;
        for (int i = 0; i < showlist.size(); i++){
            temp = showlist.get(i);
            System.out.println(temp.getID() + ". " + temp.getMovie().getName());
            System.out.println("Show time: " + temp.getShowTime());
            
            for (Cineplex c : cineplexHandler.getAllCineplex()){
                for (Cinema cinema : c.getHall()){
                    if (cinema.getCinemaID() == temp.getID()){
                        System.out.println("Location: " + c.getLocation());
						System.out.println();
						break;
                    }
                }
            }
        }*/
    
	
	public ArrayList<Show> searchShows(String searchString){
        ArrayList <Show> allShows = new ArrayList<>();
        for (Cineplex c : cineplexHandler.getAllCineplex()){
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

	public void searchMovie(String searchString) {
		System.out.println("Showing results for: "+searchString);
		for (Movie temp : movieHandler.getMovie()) {
			if(temp.getName().contentEquals(searchString)) {
				temp.printFullMovieDetails();
				System.out.print("Average Movie Rating: ");
				System.out.println(temp.getAverageRatings());
				System.out.println("Most Recent Reviews:");
				System.out.println(temp.getReview());
			}
		}
	}

	public void createShow() {
		int movieOption = -1, cineplexOption =-1, cinemaOption =-1;
		String dateInString;
		Date showtime = null;
		
		showAllMovies();
		System.out.print("Select Movie to create show:");
		try{
			movieOption = in.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input. Please re-enter.");
			in.next();
		}
		Movie selectedMovie = movieHandler.getMovie().get(movieOption-1);
		
		System.out.print("Enter date and showtime (E.g. Friday, Jun 7, 2020 09:00:00 AM): ");
		in.nextLine();
		dateInString = in.nextLine();
		try {
			showtime = formatter.parse(dateInString);
			System.out.println(showtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		cineplexHandler.printAllCineplex();
		System.out.print("Select Cineplex: ");
		try{
			cineplexOption = in.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input. Please re-enter.");
			in.next();
		}
		
		cineplexHandler.getAllCineplex().get(cineplexOption-1).printAllCinema();
		System.out.print("Select Cinema: ");
		try{
			cinemaOption = in.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input. Please re-enter.");
			in.next();
		}
		
		Show show1 = new Show(showtime,selectedMovie,cineplexHandler.getAllCineplex().get(cineplexOption-1).getHall().get(cinemaOption-1));
		System.out.println(show1);
	}
	public void showBookingHist() {
		System.out.println("Please enter your name to check your Booking history");
		for (Ticket temp : userhandler.getUsers().get(0).showHist()) {
			System.out.println( "Ticket{" +
	                "owner='" +  userhandler.getUsers().get(0).getName() +
	                ", bookingTime=" + temp.getBookingTime() +
	                ", Seats booked=" + temp.getSeat().getSeat() +
	                ", bookedShow=" + temp.getBookedShow()+
	                '}');
		}
	}
>>>>>>> Stashed changes
}
