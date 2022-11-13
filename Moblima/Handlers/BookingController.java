package Moblima.Handlers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Moblima.Entities.Booking;
import Moblima.Entities.Seats;
import Moblima.Entities.Show;
import Moblima.Entities.Ticket;
import Moblima.Entities.User;
import Moblima.Utils.Settings;
import Moblima.Utils.UtilityInputs;
import Moblima.Utils.UtilityOutput;
import Moblima.Entities.Cinema.HallType;
import Moblima.Entities.Movie.MovieType;
import Moblima.Exceptions.InvalidInputException;
import Moblima.Exceptions.SeatsNotAvailableException;

/**
 * Booking Controller class to faciltate booking
 * @author Marcus Kho
 * @version 1.0
 */
public class BookingController {
	/**
	 * Enum of AllPrices and their respective property names from Settings.ini
	 */
	public enum AllPrices{
		/**
		 * Standard Hall price for students, aduilts and senior citizens
		 */
		STANDARD_PRICES (HallType.STANDARD, "ticket_price_student_standard", "ticket_price_adult_standard", "ticket_price_senior_standard"), 
		/**
		 * Premium Hall price for students, aduilts and senior citizens
		 */
		PREMIUM_PRICES(HallType.PREMIUM, "ticket_price_student_premium", "ticket_price_adult_premium", "ticket_price_senior_premium"),
		/**
		 * VIP Hall price for students, aduilts and senior citizens
		 */
		VIP_PRICES(HallType.VIP, "ticket_price_student_vip", "ticket_price_adult_vip", "ticket_price_senior_vip"),
		
		/**
		 * IMAX 3D HALL PRICE FOR Students, adults and senior citizens
		 */
		IMAX_3D(HallType.IMAX_3D, "ticket_price_student_3d", "ticket_price_adult_3d", "ticket_price_senior_3d");

		private final Map<HallType, String[]> allPrices;
		/**
		 * Constructor for AllPrices
		 * 
		 * @param h halltype
		 * @param student student property
		 * @param adult adult property
		 * @param senior senior citizen property
		 */
		AllPrices(HallType h, String student, String adult, String senior){
			String[] prices = {student, adult, senior};
			allPrices = new HashMap<>();
			allPrices.put(h, prices);
		}
		/**
		 * Gets the respective HallType property for pricing
		 * 
		 * @param choice - can be 0-2, 0:STANDARD, 1:PREMIUM, 2:VIP
		 * @return property String
		 */
		public String getProperty(int choice){
			return this.allPrices.values().iterator().next()[choice];
		}
	}

	private final String PUBLIC_HOLIDAYS = "public_holiday_dates";
	private final String DISCOUNT_DAyS = "weekly_discount_days";
	private final String DISCOUNT_DAYS_RATE = "weekly_discount_rates";
	private final String BLOCKBUSTER = "ticket_price_increase_blockbuster";
	private final String DIGITAL3D = "ticket_price_increase_digital3D";
	/**
	 * 
	 * Default constructor for Booking Controller
	 */
	public BookingController() {}

	/**
	 * Printing of booking menu
	 * 
	 */
	public static void bookMenu(){
		String[] menu = {"--------------MOBLIMA BOOKING MENU!--------------",
		"| 01: List All Shows                            |",
		"| 02: Search Shows by Name                      |",
		"| 03: View Movie by Location                    |",
		"| 04: Go Back                                   |",
		"-------------------------------------------------"};
		UtilityOutput.printMenu(menu);
		UtilityOutput.printInputMessage("Enter option ('4' to return): ");
	}

	
	/** 
	 * Get choice for booking menu
	 * @return int
	 */
	public int getMenuChoice(){
		return UtilityInputs.getIntUserInput();
	}

	
	/** 
	 * Generates a list of shows based on user's input
	 * 
	 * @param choice Gets a input parameter from user menu
	 * @return ArrayList of Show based on how user search for shows
	 */
	public ArrayList<Show> getShowList(int choice){
		ShowHandler showHandler = ShowHandler.getInstance();
		CineplexHandler cineplexHandler = CineplexHandler.getInstance();
		
		ArrayList<Show> shows = null;
		try{
			switch(choice){
				case 1:
					shows = showHandler.getAllShowsShowing();
					break;
				case 2:
					while (true) {
						UtilityOutput.printInputMessage("Enter movie name to search [0 to exit] => ");
						String searchString = UtilityInputs.getStringUserInput().toLowerCase();
						if (searchString.equals("0")) return null;
						shows = showHandler.searchShows(searchString);
						if (shows.isEmpty()) {
							UtilityOutput.printMessage("No Results found for: "+searchString);
							UtilityOutput.printMessage("Please re-enter...");
						}else break;
					}
					break;
				case 3:
					cineplexHandler.printAllCineplex();
					try{
						UtilityOutput.printInputMessage("Enter Cineplex to book [0 to exit] => ");
						int userInput = UtilityInputs.getIntUserInput();
						if (userInput == 0) return null;
						if (userInput > cineplexHandler.getSize()) throw new InvalidInputException("Cineplex does not exist");
						shows = showHandler.getAllShowsByLocation(cineplexHandler.getAllCineplex().get(userInput-1));
					} catch(InvalidInputException e ){
						UtilityOutput.printMessage(e.getMessage());
					} 
					break;
				case 4:
					return null;
				default:
					throw new InvalidInputException("Invalid Input, please enter only 1 - 4");
			}
		}catch(InvalidInputException e){
			UtilityOutput.printMessage(e.getMessage());
		}
		if (shows == null){
			UtilityOutput.printMessage("No shows found");
		}
		return shows;
	}

	
	/** 
	 * Faciliate booking of shows
	 * 
	 * @param shows list of shows for user to select from
	 * @param user1 user object to keep track of booking if sucessful
	 * @return ArrayList of Ticket that are sucessfully booked by the user
	 */
	public ArrayList<Ticket> bookShow (ArrayList<Show> shows, User user1){
		SeatHandler seatHandler = SeatHandler.getInstance();
		Booking newBooking = new Booking(user1);
		ShowHandler.printAllShows(shows);
		Show selectedShow = UtilityInputs.getShow(shows);
		if (selectedShow == null) return null;
		newBooking.setShow(selectedShow);
		ArrayList<Seats> seatList = seatHandler.getSeatList(selectedShow);
        UtilityOutput.printSeatingForBooking(seatList);

		newBooking.setAdultTicket(UtilityInputs.getNumberOfTicket("Adult"));
		newBooking.setStudentTicket(UtilityInputs.getNumberOfTicket("Student"));
		newBooking.setSeniorTicket(UtilityInputs.getNumberOfTicket("Senior Citizen"));

		if (newBooking.getStudentTicketNum() == 0 && newBooking.getAdultTicketNum() == 0 && newBooking.getSeniorTicket() == 0){
			UtilityOutput.printMessage("Cancelling booking");
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

	
	/** 
	 * Allow users to choose seats for their selected show
	 * 
	 * @param newBooking booking object to retrieve user's data easily
	 * @return ArrayList of seats user had chose
	 */
	public ArrayList<Seats> selectSeats (Booking newBooking){
		SeatHandler seatHandler = SeatHandler.getInstance();
        ArrayList<Seats> chosenSeats = new ArrayList<Seats>();
		int totalTickets = newBooking.getTotalTicketNum();

		if (!seatHandler.checkCapacity(totalTickets, newBooking.getShow())){
			UtilityOutput.printMessage("Not enough tickets remaining");
			return null;
		}

        while (true){
			for(int j = 0; j < totalTickets; j++){
				while (true){
					try{
						Seats s1 = UtilityInputs.getSeatSelection(j);
						if (s1 == null) return null;
						if (seatHandler.checkSeatAvailability(s1, newBooking.getShow()) && !SeatHandler.duplicateSeatInput(s1, chosenSeats)){
							chosenSeats.add(s1);
							break;
						}else{
							throw new SeatsNotAvailableException("Seat not available.\nEnter 0 to exit");
						}
					} catch (SeatsNotAvailableException e){
						UtilityOutput.printMessage(e.getMessage());
						continue;
					}
				}
			}
			break;
		}
        return chosenSeats;
    }

	
	/** 
	 * Seek booking confirmation before generating the tickets
	 * 
	 * @param newBooking booking object to retrieve user's data easily
	 * @return ArrayList of Ticket booked by the user
	 */
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
				} else if (newBooking.getStudentTicketNum() > 0) {
					ticketList.add(newBooking.getShow().bookTicket(newBooking.getUser(), s, newBooking.getStudentPrice()));
					newBooking.setStudentTicket(newBooking.getStudentTicketNum()-1);
				} else {
					ticketList.add(newBooking.getShow().bookTicket(newBooking.getUser(), s, newBooking.getSeniorPrice()));
					newBooking.setSeniorTicket(newBooking.getSeniorTicket() - 1);
				}
			}
        }
        return ticketList;
    }

	
	/** 
	 * Checks if show time date falls under any public holidays
	 * 
	 * @param settings settings instance to get public holidays dates
	 * @param cal date of showtime to check if it falls under public holiday
	 * @return boolean true if holiday, false if not
	 */
	public boolean isHoliday(Settings settings, Calendar cal){
		try{
			String ph_dates = settings.getProperty(PUBLIC_HOLIDAYS);
			String[] arrOfStr = ph_dates.split(",");
			for (String s : arrOfStr){
				if (Integer.parseInt(s.split("/", 2)[0]) == cal.get(Calendar.DAY_OF_MONTH) && Integer.parseInt(s.split("/", 2)[1]) == cal.get(Calendar.MONTH) + 1){
					return true;
				}
			}
		} catch (NullPointerException e){
			return false;
		}
		
		return false;
	}

	
	/** 
	 * Checks if the show time falls on any days of the week with discounted price
	 * 
	 * @param settings settings instance to get weekly discount days
	 * @param cal show time date for checking
	 * @return int returns discount (if any), else 0
	 */
	public int weeklyDiscounts(Settings settings, Calendar cal){
		String discount_days = settings.getProperty(DISCOUNT_DAyS);
		try{
			String[] days = discount_days.split(",");
		for (String s : days){
			if((Integer.parseInt(s)+1) == cal.get(Calendar.DAY_OF_WEEK)){
				String discount_rate = settings.getProperty(DISCOUNT_DAYS_RATE);
				return Integer.parseInt(discount_rate);
			}
		}
		} catch (NullPointerException e){
			return 0;
		}
		return 0;		
	}

	
	/** 
	 * Calculate the price based on user's selection of hall, ticket type(Student, Adult, Senior Citizen)
	 * 
	 * @param newBooking booking object for easy retrival of data
	 */
	public void calcPrice(Booking newBooking){
		Settings settings = Settings.getInstance();
		Calendar calendar = Calendar.getInstance();
		int discounts = 0;

		// student , adult , senior
		Double[] prices = {0.0, 0.0, 0.0};
		HallType cinemaClass = newBooking.getShow().getCinema().getCinemaClass();
		Date showtime = newBooking.getShow().getShowTime();
		calendar.setTime(showtime);
		AllPrices allprices = null;
		String property = null;
		try{
			switch(cinemaClass){
				case STANDARD:
					allprices = AllPrices.STANDARD_PRICES;
					break;
				case PREMIUM:
					allprices = AllPrices.PREMIUM_PRICES;
					break;
				case VIP:
					allprices = AllPrices.VIP_PRICES;
					break;
				case IMAX_3D:
					allprices = AllPrices.IMAX_3D;
					break;
			}
			for (int i = 0; i < prices.length; i++){
				prices[i] = Double.parseDouble(settings.getProperty(allprices.getProperty(i)));
			}
			
			MovieType type = newBooking.getShow().getMovie().getType();
			switch(type) {
				case STANDARD_DIGITAL: 
					break;
				case BLOCKBUSTER:
					property = BLOCKBUSTER;
					break;
				case DIGITAL_3D:
					property = DIGITAL3D;
					break;
			}
			if (property != null) {
				for (int i = 0 ; i < prices.length; i++) {
					prices[i] += Double.parseDouble(settings.getProperty(property));
				}
			}
		} catch (NumberFormatException e){
			UtilityOutput.printMessage("Invalid pricing for student or adult, please check approach Admins or Call us");
			return;
		}

		discounts = weeklyDiscounts(settings, calendar);
		for (int i = 0; i < prices.length; i++){
			prices[i] -= discounts;
		}
		
		try{
			if (isHoliday(settings, calendar)){
				for(int i = 0; i < prices.length; i++){
					prices[i] += Double.parseDouble(settings.getProperty("public_holiday_price_increase"));
				}
			}
		}catch (NumberFormatException e) {}

		newBooking.setAdultPrice(prices[1]);
		newBooking.setStudentPrice(prices[0]);
		newBooking.setSeniorPrice(prices[2]);
	}

	
	/** 
	 * Function to generate Transactional ID with the format - (XXXXyyyyMMddHHmm) where XXXX is Cinema ID and yyyyMMddHHmm is the date of booking
	 * 
	 * @param newBooking for easy retrival of user's selection to generate Transaction ID
	 * @return String - Transactional ID
	 */
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

    
	/** 
	 * Confirms booking with user
	 * 
	 * @param newBooking booking object for easy retrival of user's selection
	 * @return int 1 for confirmation, 0 for cancel
	 */
	public int bookingConfirmation(Booking newBooking){
    	SeatHandler seatHandler = SeatHandler.getInstance();
		calcPrice(newBooking);
		if (newBooking.getStudentPrice() == 0.0 || newBooking.getAdultPrice() == 0) return 0;
		double totalPrice = newBooking.getTotalPrice();
        while(true){
			try{
				char confirmation = UtilityInputs.getConfirmation(totalPrice);
				if (Character.toUpperCase(confirmation) == 'Y'){
					for (Seats s: newBooking.getSeats()){
						seatHandler.removeSeats(s, newBooking.getShow());
					}
					UtilityOutput.printMessage("Your transaction ID is: " + generateTransactionID(newBooking));
					return 1;
				} else if (Character.toUpperCase(confirmation) == 'N'){
					UtilityOutput.printMessage("Booking unsuccessful");
					UtilityOutput.printMessage("Returning back to main menu");
					return 0;
				} else {
					throw new InvalidInputException("Enter only Y or N");
				}
			}catch (InvalidInputException e){
				UtilityOutput.printMessage(e.getMessage());
			}
        }
    }

}
