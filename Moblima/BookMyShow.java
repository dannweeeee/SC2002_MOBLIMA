package Moblima;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookMyShow implements BookMyShowInterface{
	
	private movieHandler movieHandler;
	private cineplexHandler cineplexHandler;
	private cinemaHandler cinemaHandler;
	private Cinema cinema;
	private Cineplex cineplex;
	private Scanner in;
	private SeatHandler seatHandler;
	private ShowHandler showHandler;
	private UserHandler userhandler;
	SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
	Scanner scanner = new Scanner(System.in);

	private final String STUDENT_PRICE_STANDARD = "ticket_price_student_standard";
	private final String ADULT_PRICE_STANDARD = "ticket_price_adult_standard";

	private final String STUDENT_PRICE_PREMIUM = "ticket_price_student_premium";
	private final String ADULT_PRICE_PREMIUM = "ticket_price_adult_premium";

	private final String STUDENT_PRICE_VIP = "ticket_price_student_vip";
	private final String ADULT_PRICE_VIP = "ticket_price_adult_vip";

	private final String TUESDAY_DISCOUNT = "tuesday_discount_price";
	private final int TUESDAY = 2;
	
	public BookMyShow() {
		this.movieHandler = new movieHandler();
		this.cineplexHandler = new cineplexHandler();
		this.showHandler = new ShowHandler();
		this.seatHandler = new SeatHandler();
		userhandler= new UserHandler();
		in = new Scanner(System.in);
	}
	
	public void initializeExample() {
		Cineplex jurong = cineplexHandler.addCineplex("JurongPoint");
		cinemaHandler JurongPoint = new cinemaHandler("JurongPoint");
		JurongPoint.addCinema("Standard", 35, jurong);
		JurongPoint.addCinema("Platinum", 10, jurong);
		cineplexHandler.getAllCineplex().get(cineplexHandler.getSize()-1).setHall(JurongPoint);
		JurongPoint.addCinema("VIP", 1, jurong);
		
		User ayush = new User("Ayush","ayus@gmail.com",3293131);

		//Movie ironMan = new Movie("Iron Man","showing","Jon Favreaue","AAA", "Example Cast...", movieHandler);
       // Movie avengers = new Movie("Avengers: End Game", "showing","Jon Favreaue","BBB", "Example Cast...", movieHandler);
        String dateInString = "Sunday, Dec 25, 2020 09:00:00 AM";
		try {
			Date date = formatter.parse(dateInString);
			Show show1 = showHandler.addShows(date, movieHandler.getMovie().get(0), cineplexHandler.getAllCineplex().get(0).getHall().get(0), seatHandler);
			Show show2 = showHandler.addShows(date,movieHandler.getMovie().get(1),cineplexHandler.getAllCineplex().get(0).getHall().get(0), seatHandler);
			Show show3 = showHandler.addShows(date,movieHandler.getMovie().get(1),cineplexHandler.getAllCineplex().get(0).getHall().get(1), seatHandler);
			Show show4 = showHandler.addShows(date,movieHandler.getMovie().get(1),cineplexHandler.getAllCineplex().get(0).getHall().get(2), seatHandler);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void showShowTimes(){
		ShowHandler.printAllShows(showHandler.getAllShows());
	}
	
	public void showExample() {
		cineplexHandler.printAllCineplex();
		System.out.println("Show all cinemas:");
		cineplexHandler.getAllCineplex().get(0).printAllCinema();
	}
	
	public void showAllMovies() {
		System.out.println("Showing all movies...");
		int count =1;
		for (Movie temp : movieHandler.getMovie()) {
			System.out.print(count+": ");
			System.out.println(temp);
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
	    		newMovie = new Movie(movieName, movieStatus, movieDirector, movieSynopsis, movieCast);
				movieHandler.addMovie(newMovie);
	    	}
	    	read.close();
	}
	
	public void BookMovie() {
		int booking_option = 0;
		User user1 = BookingInputs.getUserInformation();
		userhandler.getUsers().add(user1);
		ArrayList<Ticket> tickets = new ArrayList<>();
		do{
			ArrayList<Show> shows = null;
			System.out.println("--------------MOBLIMA BOOKING MENU!--------------");
			System.out.println("| 01: List All Shows                            |");
			System.out.println("| 02: Search Shows by Name                      |");
			System.out.println("| 03: View Movie by Location                    |");
			System.out.println("| 04: Go Back                                   |");
			System.out.println("-------------------------------------------------");
			System.out.print("Enter option ('4' to return): ");

			booking_option = BookingInputs.getIntUserInput();

			switch(booking_option){
				case 1:
					shows = showHandler.getAllShows();
					break;
				case 2:
					String searchString = BookingInputs.getSearchString();
					shows = showHandler.searchShows(searchString);
					break;
				case 3:
					cineplexHandler.printAllCineplex();
        			int userInput = BookingInputs.getCineplex();
					shows = showHandler.getAllShowsByLocation(cineplexHandler.getAllCineplex().get(userInput-1));
					break;
				case 4:
					return;
				default:
					System.out.println("Invalid Input");
			}
			if (shows == null){
				System.out.println("No shows found");
			} else {
				ArrayList <Ticket> ticket = bookShow(shows, user1);
				if (ticket != null){
					tickets.addAll(ticket);
				}
			}
		} while(booking_option != 4);
	}

	public ArrayList<Ticket> bookShow (ArrayList<Show> shows, User user1){
		Booking newBooking = new Booking(user1);
		ShowHandler.printAllShows(shows);
		Show selectedShow = BookingInputs.getShow(shows);
		if (selectedShow == null) return null;
		newBooking.setShow(selectedShow);
		seatHandler.printAvailableSeats(selectedShow);
		newBooking.setAdultTicket(BookingInputs.getNumberOfTicket("Adult"));
		newBooking.setStudentTicket(BookingInputs.getNumberOfTicket("Student"));

		if (newBooking.getStudentTicketNum() == 0 && newBooking.getAdultTicketNum() == 0){
			System.out.println("Cancelling booking");
			return null;
		}

		ArrayList<Seats> seats = selectSeats(newBooking);
		if (seats != null){
			newBooking.setSeats(seats);
			ArrayList<Ticket> tickets = bookSeats(newBooking);
			return tickets;
		}
		return null;
	}

	public ArrayList<Seats> selectSeats (Booking newBooking){
        ArrayList<Seats> chosenSeats = new ArrayList<Seats>();
		int totalTickets = newBooking.getAdultTicketNum() + newBooking.getStudentTicketNum();
		if (!seatHandler.checkCapacity(totalTickets, newBooking.getShow())){
			System.out.println("Not enough tickets remaining");
			return null;
		}

        while (true){
			for(int j = 0; j < totalTickets; j++){
				while (true){
					Seats s1 = BookingInputs.getSeatSelection(j);
					if (s1 == null) return null;
					if (seatHandler.checkSeatAvailability(s1, newBooking.getShow()) && !SeatHandler.duplicateSeatInput(s1, chosenSeats)){
						chosenSeats.add(s1);
						break;
					} else{
						System.out.println("Seat is not available, please try again");
						System.out.println("Enter 0 to exit");
					}
					if (chosenSeats.size() == j+1) break;
				}
			}
			break;
		}
        return chosenSeats;
    }

	public ArrayList<Ticket> bookSeats(Booking newBooking){
        ArrayList<Seats> seatlist = newBooking.getSeats();
        int confirmation = 0;
        if (seatlist != null){
            confirmation = bookingConfirmation(newBooking);
        }
        ArrayList<Ticket> ticketList = new ArrayList<>();
        if (confirmation == 1){
            for(Seats s: seatlist) {
				if (newBooking.getAdultTicketNum() > 0){
					ticketList.add(newBooking.getShow().bookTicket(newBooking.getUser(), s, newBooking.getAdultPrice()));
					newBooking.setAdultTicket(newBooking.getAdultTicketNum()-1);
				} else {
					ticketList.add(newBooking.getShow().bookTicket(newBooking.getUser(), s, newBooking.getStudentPrice()));
					newBooking.setStudentTicket(newBooking.getStudentTicketNum()-1);
				}
			}
        }
        return ticketList;
    }

	public boolean isHoliday(Settings settings, Calendar cal){
		String ph_dates = settings.getProperty("public_holiday_dates");
		String[] arrOfStr = ph_dates.split(",");
		for (String s : arrOfStr){
			if (Integer.parseInt(s.split("/", 2)[0]) == cal.get(Calendar.DAY_OF_MONTH) && Integer.parseInt(s.split("/", 2)[1]) == cal.get(Calendar.MONTH) + 1){
				return true;
			}
		}
		return false;
	}

	public void calcPrice(Booking newBooking){
		Settings settings = Settings.getInstance();
		Calendar calendar = Calendar.getInstance();
		Double studentPrice = 0.0;
		Double adultPrice = 0.0;
		String cinemaClass = newBooking.getShow().getCinema().getCinemaClass();
		Date showtime = newBooking.getShow().getShowTime();
		calendar.setTime(showtime);

		try{
			if (cinemaClass== "Standard"){
				studentPrice = Double.parseDouble(settings.getProperty(STUDENT_PRICE_STANDARD));
				adultPrice = Double.parseDouble(settings.getProperty(ADULT_PRICE_STANDARD));
			} else if (cinemaClass == "Platinum"){
				studentPrice = Double.parseDouble(settings.getProperty(STUDENT_PRICE_PREMIUM));
				adultPrice = Double.parseDouble(settings.getProperty(ADULT_PRICE_PREMIUM));
			} else {
				studentPrice = Double.parseDouble(settings.getProperty(STUDENT_PRICE_VIP));
				adultPrice = Double.parseDouble(settings.getProperty(ADULT_PRICE_VIP));
			}
			
			if (calendar.get(Calendar.DAY_OF_WEEK) == TUESDAY){
				studentPrice -= Double.parseDouble(settings.getProperty(TUESDAY_DISCOUNT));
				adultPrice -= Double.parseDouble(settings.getProperty(TUESDAY_DISCOUNT));
			}

			if (isHoliday(settings, calendar)){
				studentPrice += Double.parseDouble(settings.getProperty("public_holiday_price_increase"));
				adultPrice += Double.parseDouble(settings.getProperty("public_holiday_price_increase"));
			}
		} catch (NumberFormatException e){
			System.out.println("Invalid pricing for student or adult, please check approach Admins or Call us");
			return;
		}
		

		newBooking.setAdultPrice(adultPrice);
		newBooking.setStudentPrice(studentPrice);
	}

	public String generateTransactionID(Booking newBooking){
		String transactionID = Integer.toString(newBooking.getShow().getCinema().getCinemaID());;
		final int CINEMACODELENGTH = 4;
		while (transactionID.length() < CINEMACODELENGTH){
			transactionID = "0" + transactionID;
		}
		Date now = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyyMMddHHmm");
		String stringDate= DateFor.format(now);;

		transactionID = transactionID + stringDate;
		return transactionID;
	}

    public int bookingConfirmation(Booking newBooking){
		calcPrice(newBooking);
		if (newBooking.getStudentPrice() == 0.0 || newBooking.getAdultPrice() == 0) return 0;
		double totalPrice = newBooking.getStudentTicketNum() * newBooking.getStudentPrice() + newBooking.getAdultTicketNum() * newBooking.getAdultPrice();
        while(true){
            char confirmation = BookingInputs.getConfirmation(totalPrice);
            if (Character.toUpperCase(confirmation) == 'Y'){
                for (Seats s: newBooking.getSeats()){
                    seatHandler.removeSeats(s, newBooking.getShow());
                }
				System.out.println("Your transaction ID is: " + generateTransactionID(newBooking));
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

    public void showAllMoviesTicket() {
		
		int count =1;
		System.out.println("Sort Movies by: \n1.Ticket sales \n2.Ratings");
		System.out.print("Enter Option: ");
        int sortOption = BookingInputs.getIntUserInput();
        
        if(sortOption==1) {
        	movieHandler.sortByTicketSales();
        	for (Movie temp : movieHandler.getMovie()) {
        		if(count>5) break;
    			System.out.print(count+": ");
    			System.out.print(temp);
    			System.out.println("Ticket Sales: "+temp.getTicketsSize());
    			System.out.println();
    			count++;
    		}
        }
        else {
        	movieHandler.sortByRatings();
	        for (Movie temp : movieHandler.getMovie()) {
	        	if(count>5) break;
	        	System.out.print(count+": ");
				System.out.print(temp);
				System.out.println("Ratings: "+temp.getAverageRatings());
				System.out.println();
				count++;
			}
        }
	}

	// should be in movie handler
	public void searchMovie(String searchString) {
		System.out.println("Showing results for: "+searchString);
		for (Movie temp : movieHandler.getMovie()) {
			if(temp.getName().contentEquals(searchString)) {
				System.out.println(temp);
				System.out.print("Average Movie Rating: ");
				System.out.println(temp.getAverageRatings());
				System.out.println("Most Recent Reviews:");
				System.out.println(temp.getReview());
			}
		}
	}

	// add this to showhandler
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

	public movieHandler getMovieDatabase() {
		return movieHandler;
	}

	public void createMovie(String fileName){
        in = new Scanner(System.in);
        String movieAddName, movieAddStatus, movieAddDirector, movieAddSynopsis, movieAddCasts;
        System.out.print("Enter full name of Movie: ");
        movieAddName = in.nextLine();
        System.out.print("Enter status of Movie (Coming Soon, Now Showing): ");
        movieAddStatus = in.nextLine();
        System.out.print("Enter director of Movie: ");
        movieAddDirector = in.nextLine();
        System.out.print("Enter synopsis of Movie: ");
        movieAddSynopsis = in.nextLine();
        System.out.print("Enter casts of Movie (e.g. Steve Rogers, Borat, Mr Bean): ");
        movieAddCasts = in.nextLine();
        Movie addNewMovie = movieHandler.createMovie(movieAddName, movieAddStatus, movieAddDirector, movieAddSynopsis, movieAddCasts);
    }

	public void updateMovie(String fileName){
		int movieOption = 0, moviePartOption = 0;
		String movieName, movieStatus, movieDirector, movieSynopsis, movieCasts;
		String movieUpdateName, movieUpdateStatus, movieUpdateDirector, movieUpdateSynopsis, movieUpdateCasts;
		showAllMovies();
		System.out.print("Which Movie would you like to update? (e.g. 1): ");
		try{
			movieOption = in.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input. Please re-enter.");
			in.next();
		}
		Movie selectedMovie = movieHandler.getMovie().get(movieOption-1);
		movieName = selectedMovie.getName();
		movieStatus = selectedMovie.getStatus();
		movieDirector = selectedMovie.getDirector();
		movieSynopsis = selectedMovie.getSynopsis();
		movieCasts = selectedMovie.getCast();

		System.out.println("1.Title: " + movieName);
		System.out.println("2.Status: " + movieStatus);
		System.out.println("3.Director: " + movieDirector);
		System.out.println("4.Cast: " + movieCasts);
		System.out.println("5.Synopsis: " + movieSynopsis);

		do{
			System.out.println("Select which to update (Enter '-1' to confirm & exit): ");
			moviePartOption = in.nextInt();
			switch(moviePartOption){
				case 1:
					System.out.print("Update full name of Movie: ");
					in = new Scanner(System.in);
					movieUpdateName = in.nextLine();
					selectedMovie.setName(movieUpdateName);
					break;
				case 2:
					System.out.print("Update status of Movie (Coming Soon, Now Showing): ");
					in = new Scanner(System.in);
					movieUpdateStatus = in.nextLine();
					selectedMovie.setStatus(movieUpdateStatus);
					break;
				case 3:
					System.out.print("Update director of Movie: ");
					in = new Scanner(System.in);
					movieUpdateDirector = in.nextLine();
					selectedMovie.setDirector(movieUpdateDirector);
					break;
				case 4:
					System.out.print("Update casts of Movie (e.g. Steve Rogers, Borat, Mr Bean): ");
					in = new Scanner(System.in);
					movieUpdateCasts = in.nextLine();
					selectedMovie.setCast(movieUpdateCasts);
					break;
				case 5:
					System.out.print("Update synopsis of movie: ");
					in = new Scanner(System.in);
					movieUpdateSynopsis = in.nextLine();
					selectedMovie.setSynopsis(movieUpdateSynopsis);
					break;
				default:
					System.out.println("Invalid Input");
			}
		} while(moviePartOption != -1);
		System.out.println(selectedMovie);
	}

	public void removeMovie(String fileName){
		int movieRemoveOption = 0;
		showAllMovies();
		System.out.print("Which Movie would you like to update? (e.g. 1): ");
		try{
			movieRemoveOption = in.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input. Please re-enter.");
			in.next();
		}
		Movie selectedMovie = movieHandler.getMovie().get(movieRemoveOption-1);
		System.out.println("The following movie has been deleted!");
		System.out.println(selectedMovie);
		movieHandler.removeMovie(movieRemoveOption-1);
	}

	public void showBookingHist() {
		for (Ticket temp : userhandler.getUsers().get(userhandler.getSize()).getTickets()) {
			System.out.println( "Ticket{" +
	                " owner='" +  userhandler.getUsers().get(userhandler.getSize()).getName() +
	                ", bookingTime=" + temp.getBookingTime() +
	                ", Seats booked=" + temp.getSeat().getSeat() +
	                ", bookedShow=" + temp.getBookedShow()+
	                '}');
		}
	}

	public void createRatingReview() {
		int option=0;
		User useri=null;
		System.out.print("Enter your Email: ");
		String email=in.nextLine();
		for(User temp: userhandler.getUsers()) {
			if(temp.getEmail().contentEquals(email)) {
				 useri=temp;}
		}
		if(useri==null){
			System.out.print("User doesn't exist");
			return;
		}
		do {
			
            System.out.println();
            System.out.println("----------------REVIEW/RATING MENU---------------");
        	System.out.println("| 01: Rate a Movie                              |");
        	System.out.println("| 02: Review a Movie                            |");
        	System.out.println("| 03: Exit                                      |");
            System.out.println("-------------------------------------------------");
            System.out.println();
            
			option = BookingInputs.getIntUserInput();
	        
	        switch(option){
			case 1:
				System.out.println("Which Movie would you like to rate?");
				String name=in.nextLine();
				
				for (Movie temp : movieHandler.getMovie()) {
					if(temp.getName().contentEquals(name)) {
						System.out.println("Enter your rating from 1 to 5:");
						double score = scanner.nextInt();
				        scanner.nextLine();
						temp.addRatings(new Rating(score,useri));
						System.out.println("Rating added");
						break;
					}
				}
				break;
			case 2:
				System.out.println("Which Movie would you like to review?");
				String name1=in.nextLine();
				for (Movie temp : movieHandler.getMovie()) {
					if(temp.getName().contentEquals(name1)) {
						System.out.println("Enter your review:");
						String text = in.nextLine();
						temp.addReview(new Review(text,useri));
						System.out.println("Review added");
						break;
					}
				}
				break;
			case 3:
				break;
			default:
				System.out.println("Invalid Input");
	        }
		}while(option!=3);
	}
  
  	public void updateShowTime(){
		Scanner newScanner = new Scanner(System.in);

		while(true){
			ShowHandler.printAllShows(showHandler.getAllShows());
			System.out.println("Enter which show time you want to edit [-1 to exit] ");
			int choice = BookingInputs.getIntUserInput();
			if (choice == -1) break;
			Show selectedShow = ShowHandler.getShowByID(showHandler.getAllShows(), choice);
			System.out.println("You have selected\n" + selectedShow.toString());
			System.out.println("Enter new show time for this movie [Format: Monday, Jun 10, 2022 12:00:00 AM] =>  ");
			String dateInString = newScanner.next();
			try{
				Date date = formatter.parse(dateInString);
				selectedShow.setShowTime(date);
			} catch(ParseException e){
				System.out.println("Invalid date input");
				continue;
			}
		} //end of while	
	}
	
}

