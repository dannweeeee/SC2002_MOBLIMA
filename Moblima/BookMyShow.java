package Moblima;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
}
