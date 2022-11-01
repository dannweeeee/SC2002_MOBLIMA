package Moblima;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class BookMyShowApp_obsolete {
	

    public static void main(String[] args) {
    	
     
    	movieHandler movieDB = new movieHandler();

    	Cineplex GoldenVillageJP= new Cineplex("Jurong Point");

        User ayush = new User("Ayush","ayus@gmail.com",3293131);
        
        Movie ironMan = new Movie("Iron Man","showing","Jon Favreaue", movieDB);

        Movie avengers = new Movie("Avengers: End Game", "showing","Jon Favreaue", movieDB);

        Movie walkToRemember = new Movie("The Walk To Remember", "showing","Jon Favreaue", movieDB);

        Movie housefull = new Movie("HouseFull 2", "showing","Jon Favreaue", movieDB);
        
        readMovieFromTextFile("MovieList.txt");
        
        // Creating Theater --> PVR @ GIP Noida with capacity 30
        Cinema hall1 = new Cinema("standard",30, GoldenVillageJP);

        // Creating Another Theater --> BIG Cinema @ Noida Sector 137 with capacity 40
        Cinema hall2 = new Cinema("platinum",40, GoldenVillageJP );




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
    
    
    public void readMovieFromTextFile(String fileName) throws FileNotFoundException{
    	File movieDatabase = new File(fileName);
    	Scanner read = new Scanner(movieDatabase);
    	read.useDelimiter("\t|\r\n");
    	read.nextLine();
    	String movieName, movieStatus, movieDirector;
    	Movie newMovie;
    	
    	while(read.hasNext()) {
    		read.next();
    		movieName = read.next();
    		movieStatus = read.next();
    		movieDirector = read.next();
    		newMovie = new Movie(movieName, movieStatus, movieDirector, movieDB);
    	}
    	read.close();
    }
}
