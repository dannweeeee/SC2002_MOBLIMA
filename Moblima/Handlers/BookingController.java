package Moblima.Handlers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Moblima.Entities.Booking;
import Moblima.Entities.Seats;
import Moblima.Entities.Show;
import Moblima.Entities.Ticket;
import Moblima.Entities.User;
import Moblima.Utils.Settings;
import Moblima.Utils.UtilityInputs;

public class BookingController {
	private UserHandler userHandler; 
	private final String STUDENT_PRICE_STANDARD = "ticket_price_student_standard";
	private final String ADULT_PRICE_STANDARD = "ticket_price_adult_standard";

	private final String STUDENT_PRICE_PREMIUM = "ticket_price_student_premium";
	private final String ADULT_PRICE_PREMIUM = "ticket_price_adult_premium";

	private final String STUDENT_PRICE_VIP = "ticket_price_student_vip";
	private final String ADULT_PRICE_VIP = "ticket_price_adult_vip";

	private final String TUESDAY_DISCOUNT = "tuesday_discount_price";
	private final int TUESDAY = 2;
	
	public BookingController(UserHandler userHandler) {
		this.userHandler = userHandler;
	}

	
	public void bookMovie() {
		CineplexHandler cineplexHandler = CineplexHandler.getInstance();
		ShowHandler showHandler = ShowHandler.getInstance();
		int booking_option = 0;
		User user1 = UtilityInputs.getUserInformation();
		userHandler.getUsers().add(user1);
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

			booking_option = UtilityInputs.getIntUserInput();

			switch(booking_option){
				case 1:
					shows = showHandler.getAllShows();
					break;
				case 2:
					String searchString = UtilityInputs.getSearchString();
					shows = showHandler.searchShows(searchString);
					break;
				case 3:
					cineplexHandler.printAllCineplex();
        			int userInput = UtilityInputs.getCineplex();
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
		SeatHandler seatHandler = SeatHandler.getInstance();
		Booking newBooking = new Booking(user1);
		ShowHandler.printAllShows(shows);
		Show selectedShow = UtilityInputs.getShow(shows);
		if (selectedShow == null) return null;
		newBooking.setShow(selectedShow);
		seatHandler.printAvailableSeats(selectedShow);
		newBooking.setAdultTicket(UtilityInputs.getNumberOfTicket("Adult"));
		newBooking.setStudentTicket(UtilityInputs.getNumberOfTicket("Student"));

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
		SeatHandler seatHandler = SeatHandler.getInstance();
        ArrayList<Seats> chosenSeats = new ArrayList<Seats>();
		int totalTickets = newBooking.getAdultTicketNum() + newBooking.getStudentTicketNum();
		if (!seatHandler.checkCapacity(totalTickets, newBooking.getShow())){
			System.out.println("Not enough tickets remaining");
			return null;
		}

        while (true){
			for(int j = 0; j < totalTickets; j++){
				while (true){
					Seats s1 = UtilityInputs.getSeatSelection(j);
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
		if (ph_dates == null) return false;
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
    	SeatHandler seatHandler = SeatHandler.getInstance();
		calcPrice(newBooking);
		if (newBooking.getStudentPrice() == 0.0 || newBooking.getAdultPrice() == 0) return 0;
		double totalPrice = newBooking.getStudentTicketNum() * newBooking.getStudentPrice() + newBooking.getAdultTicketNum() * newBooking.getAdultPrice();
        while(true){
            char confirmation = UtilityInputs.getConfirmation(totalPrice);
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

}
