package Moblima;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BookMyShow implements BookMyShowInterface{
	
	private movieHandler movieHandler;
	private cineplexHandler cineplexHandler;
	private cinemaHandler cinemaHandler;
	private Cinema cinema;
	private Cineplex cineplex;
	private Scanner in;
	SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");

	
	public BookMyShow() {
		
		movieHandler = new movieHandler();
		cineplexHandler = new cineplexHandler();
		in = new Scanner(System.in);
	
	}
	
	public void showMovies() {
		
	}
	
	public void initializeExample() {
		cineplexHandler.addCineplex("JurongPoint");
		cineplexHandler.addCineplex("Yishun");
		cinemaHandler JurongPoint = new cinemaHandler("JurongPoint");
		JurongPoint.addCinema("Standard", 30);
		JurongPoint.addCinema("Platinum", 10);
		cineplexHandler.getAllCineplex().get(cineplexHandler.getSize()-1).setHall(JurongPoint);
		JurongPoint.addCinema("VIP", 1);
		
		User ayush = new User("Ayush","ayus@gmail.com",3293131);
        
        Movie ironMan = new Movie("Iron Man","showing","Jon Favreaue", movieHandler);
        Movie avengers = new Movie("Avengers: End Game", "showing","Jon Favreaue", movieHandler);

        String dateInString = "Friday, Jun 7, 2020 09:00:00 AM";
		try {
			Date date = formatter.parse(dateInString);
//			Show show1 = new Show(date,ironMan,cineplexHandler.getAllCineplex().get(1).getHall().get(0));
//			Show show2 = new Show(date,avengers,cineplexHandler.getAllCineplex().get(1).getHall().get(0));
//			Show show3 = new Show(date,avengers,cineplexHandler.getAllCineplex().get(1).getHall().get(1));
//			Show show4 = new Show(date,avengers,cineplexHandler.getAllCineplex().get(1).getHall().get(2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
	}
	
	public void showExample() {
		cineplexHandler.printAllCineplex();
		System.out.println("Show all cinemas:");
		cineplexHandler.getAllCineplex().get(1).printAllCinema();
	}
	
	public void showAllMovies() {
		System.out.println("Show all Movies");
		int count =0;
		for (Movie temp : movieHandler.getMovie()) {
			System.out.print(count+": ");
			temp.printMovieDetails();
			count++;
		}
	}
	
	public void readMovieFromTextFile(String fileName) throws FileNotFoundException{
	    	FileReader movieDatabase = new FileReader(fileName);
	    	Scanner read = new Scanner(movieDatabase);
	    	read.useDelimiter(",|\\r\\n");
	    	read.nextLine();
	    	String movieName, movieStatus, movieDirector;
	    	Movie newMovie;
	    	while(read.hasNext()) {
	    		//read.next();
	    		movieName = read.next();
	    		movieStatus = read.next();
	    		movieDirector = read.next();
	    		newMovie = new Movie(movieName, movieStatus, movieDirector, movieHandler);
	    	}
	    	read.close();
    }
}
