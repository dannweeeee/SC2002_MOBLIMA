package Moblima.Admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Moblima.MovieBookerApp;
import Moblima.MovieBookerInterface;
import Moblima.Entities.Cinema;
import Moblima.Entities.Cineplex;
import Moblima.Entities.Movie;
import Moblima.Entities.Show;
import Moblima.Entities.Cinema.HallType;
import Moblima.Utils.SettingsController;
import Moblima.Utils.SettingsForm;
import Moblima.Utils.UtilityInputs;
import Moblima.Handlers.CinemaHandler;
import Moblima.Handlers.CineplexHandler;
import Moblima.Handlers.MovieHandler;
import Moblima.Handlers.SeatHandler;
import Moblima.Handlers.ShowHandler;

/**
 * Controller of the admin module. Creates and delegates tasks to other classes.
 * @author Our team
 * @version 1.0
 */
public class Admin implements AdminLogic, LoginObserver {

    private LoginPage loginUI;
    private AdminForm adminUI;
    private SettingsController settingsController;

    private MovieBookerInterface movieBooker;
    private MovieHandler movieHandler;
    private ShowHandler showHandler;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");

    /**
     * Constructor for Admin.
     */
    public Admin(MovieBookerInterface movieBooker) {
        adminUI = new AdminForm(this);
        settingsController = new SettingsController(new SettingsForm());
        this.movieBooker = movieBooker;
        movieHandler = MovieHandler.getInstance();
        showHandler = ShowHandler.getInstance();
    }

    public void start() {
        //adminUI.show();
        login();
    }

    /**
     * Launch the login function.
     * @return <code>true</code> if the login is successful, <code>false</code> otherwise.
     */
    private void login() {
        IDandPasswords idandPasswords = new IDandPasswords();
		loginUI = new LoginPage(idandPasswords.getLoginInfo());
        loginUI.addLoginObserver(this);
    }

    /**
     * Launch the admin UI when the login is successful.
     */
    public void loginSuccess() {
        adminUI.show();
    }

    public void exit() {
        loginUI = null;
        adminUI = null;
        settingsController = null;
        MovieBookerApp.showUserView(movieBooker);
    }

    public void createMovie() {
        String movieAddName, movieAddStatus, movieAddDirector, movieAddSynopsis, movieAddCasts;
        System.out.print("Enter full name of Movie: ");
        movieAddName = UtilityInputs.getStringUserInput();
        System.out.print("Enter status of Movie (Coming Soon, Now Showing, Preview, End of Showing): ");
        while (true){
            movieAddStatus = UtilityInputs.getStringUserInput();
            if (movieAddStatus.equals("Coming Soon")){
                break;
            }
            else if (movieAddStatus.equals("Now Showing")){
                break;
            }
            else if (movieAddStatus.equals("Preview")){
                break;
            }
            else if (movieAddStatus.equals("End of Showing")){
                break;
            }
            else{
                System.out.println("Invalid Input. Please re-enter.");
            }
        }
        System.out.print("Enter director of Movie: ");
        movieAddDirector = UtilityInputs.getStringUserInput();
        System.out.print("Enter synopsis of Movie: ");
        movieAddSynopsis = UtilityInputs.getStringUserInput();
        System.out.print("Enter casts of Movie (e.g. Steve Rogers, Borat, Mr Bean): ");
        movieAddCasts = UtilityInputs.getStringUserInput();
        Movie addNewMovie = movieHandler.createMovie(movieAddName, movieAddStatus, movieAddDirector, movieAddSynopsis, movieAddCasts);
        System.out.print("The following Movie has been added!: ");
        System.out.print(addNewMovie);
    }

    public void updateMovie() {
        int movieOption = 0, moviePartOption = 0;
        String movieUpdateValue;
        movieBooker.showAllMovies();
        System.out.print("Which Movie would you like to update? (e.g. 1): ");
        while (true){
            movieOption = UtilityInputs.getIntUserInput();
            if (movieOption == -1) continue;
            if (movieOption < movieHandler.sizeMovie()+1 && movieOption > 0){
                break;
            }
            else{
                System.out.println("Invalid Input. Please re-enter.");
            }
        }
        Movie selectedMovie = movieHandler.getMovie().get(movieOption-1);
        System.out.println("1.Title: " + selectedMovie.getName());
        System.out.println("2.Status: " + selectedMovie.getStatus());
        System.out.println("3.Director: " + selectedMovie.getDirector());
        System.out.println("4.Cast: " + selectedMovie.getCast());
        System.out.println("5.Synopsis: " + selectedMovie.getSynopsis());

        while(true){
            System.out.println("Select which to update (Enter '0' to confirm & exit): ");
            moviePartOption = UtilityInputs.getIntUserInput();
            if (moviePartOption == 0){
                break;
            }
            if (moviePartOption < 0 && moviePartOption > 5){
                System.out.println("Invalid Input. Please re-enter");
                continue;
            }
            System.out.println("Enter updated value");
            movieUpdateValue = UtilityInputs.getStringUserInput();
            switch(moviePartOption){
                case 1:
                    selectedMovie.updateName(movieUpdateValue);
                    break;
                case 2:
                    selectedMovie.updateStatus(movieUpdateValue);
                    break;
                case 3:
                    selectedMovie.updateDirector(movieUpdateValue);
                    break;
                case 4:
                    selectedMovie.updateCasts(movieUpdateValue);
                    break;
                case 5:
                    selectedMovie.updateSynopsis(movieUpdateValue);
                    break;
            }
        } 
        System.out.println(selectedMovie);
    }

    public void removeMovie() {
        int movieRemoveOption = 0;
		movieBooker.showAllMovies();
		System.out.print("Which Movie would you like to delete? (e.g. 1): ");
        while (true){
            movieRemoveOption = UtilityInputs.getIntUserInput();
            if (movieRemoveOption < movieHandler.sizeMovie()+1 && movieRemoveOption > 0){
                break;
            }
            else if (movieRemoveOption == -1){
                continue;
            }
            else{
                System.out.println("Invalid Input. Please re-enter.");
            }
        }
		Movie selectedMovie = movieHandler.getMovie().get(movieRemoveOption-1);
		System.out.println("The following movie has been deleted!");
		System.out.println(selectedMovie);
		movieHandler.removeMovie(movieRemoveOption-1);
    }

    public void createShow() {
        CineplexHandler cineplexHandler = CineplexHandler.getInstance();
		MovieHandler movieHandler = MovieHandler.getInstance();
		ShowHandler showHandler = ShowHandler.getInstance();
		SeatHandler seatHandler = SeatHandler.getInstance();
        int count = 1;
		int movieOption = -1, cineplexOption =-1, cinemaOption =-1;
		String dateInString;
		Date showtime = null;
		
		movieBooker.showAllMovies();
		System.out.print("Select Movie to create show:");
		movieOption = UtilityInputs.getIntUserInput();
		Movie selectedMovie = movieHandler.getMovie().get(movieOption-1);
		while(true){
            System.out.print("Enter date and showtime (E.g. 31/07/2022 09:00:00 AM): ");
		    dateInString = UtilityInputs.getStringUserInput();
		    try {
		    	showtime = formatter.parse(dateInString);
		    	System.out.println(showtime);
                break;
		    } catch (ParseException e) {
		    	System.out.println("Wrong format. Please re-enter.");
		    }
        }
        if (cineplexHandler.getAllCineplex().size() != 0 ){
            cineplexHandler.printAllCineplex();
            System.out.print("Select Cineplex: ");
		    cineplexOption = UtilityInputs.getIntUserInput();
            Cineplex cineplex = cineplexHandler.getAllCineplex().get(cineplexOption-1);
            if (CinemaHandler.getInstance().getCinemaFromCineplex(cineplex).size() != 0){
                for (Cinema c : CinemaHandler.getInstance().getCinemaFromCineplex(cineplex)){
                    count++;
                    System.out.println(count + ". " + c);
                }
		        System.out.print("Select Cinema: ");
		        cinemaOption = UtilityInputs.getIntUserInput();
		
		        Show show1 = showHandler.addShows(showtime,selectedMovie,CinemaHandler.getInstance().getCinemaFromCineplex(cineplex).get(cinemaOption-1), seatHandler);
		        System.out.println(show1);
            }
            else{
                System.out.print("No Cinema");
            }
        }
        else{
            System.out.print("No Cineplex");
        }
    }

    public void updateShow() {
        ShowHandler showHandler = ShowHandler.getInstance();
		while(true){
            if (showHandler.getAllShows().size() != 0 ){
			    ShowHandler.printAllShows(showHandler.getAllShows());
			    System.out.println("Enter which show time you want to edit [-1 to exit] ");
			    int choice = UtilityInputs.getIntUserInput();
			    if (choice == -1) break;
			    Show selectedShow = ShowHandler.getShowByID(showHandler.getAllShows(), choice);
			    System.out.println("You have selected\n" + selectedShow.toString());
			    System.out.println("Enter new show time for this movie [Format: 31/07/2022 12:00:00 AM] =>  ");
			    String dateInString = UtilityInputs.getStringUserInput();
			    try{
				    Date date = formatter.parse(dateInString);
				    selectedShow.setShowTime(date);
			    } catch(ParseException e){
				    System.out.println("Invalid date input");
				    continue;
			    }
            }else{
                System.out.print("No Shows");
                break;
            }
		} //end of while	
    }

    public void deleteShow(){
        if (showHandler.getAllShows().size() != 0 ){
            ArrayList<Show> allShows = ShowHandler.getInstance().getAllShows();
            ShowHandler.printAllShows(allShows);
            Show selectedShow = UtilityInputs.getShow(allShows);
            ShowHandler.getInstance().removeShow(selectedShow);
            System.out.println("Show has been removed");
        }else{
            System.out.print("No Shows");
        }
    }

    public void manageSettings() {
        settingsController.launch();
    }

    public void showAllMovies(){
        movieBooker.showAllMovies();
    }

    public void addNewCineplex(){
        CineplexHandler cineplexHandler = CineplexHandler.getInstance();
        System.out.print("Enter Cineplex location [0 to exit] =>");
        String location = UtilityInputs.getStringUserInput();
        if (location.equals("0")) {
         System.out.println("Returning...");
         return;
        }
        cineplexHandler.addCineplex(location, CinemaHandler.getInstance());
        System.out.println("Cineplex added!");
    }

    public void updateCineplex(){

    }

    public void removeCineplex(){
        CineplexHandler.getInstance().printAllCineplex();
        while (true){
            System.out.println("Choice [0 to exit] =>");
            int choice = UtilityInputs.getIntUserInput();
            if (choice == 0) break;
            if (choice < 0 || choice > CineplexHandler.getInstance().getAllCineplex().size()){
                System.out.println("invlaid");
            }
            Cineplex c = CineplexHandler.getInstance().getAllCineplex().get(choice - 1);
            CineplexHandler.getInstance().removeCineplex(c);
            System.out.println("Removed Successfully");
            break;
        }
    }

    public void addNewCinema(){
        CineplexHandler.getInstance().printAllCineplex();
        while (true){
            System.out.print("Which cineplex would you like to add Cinema [0 to exit]=>");
            int userChoice = UtilityInputs.getIntUserInput();
            if (userChoice == 0){
            System.out.println("Returning...");
            break;
        }
        Cineplex c = CineplexHandler.getInstance().getAllCineplex().get(userChoice - 1);
        if (userChoice > CinemaHandler.getInstance().getCinemaFromCineplex(c).size() || userChoice < 0){
            System.out.println("Invalid Input");
            continue;
        }
        int x = 1;
        System.out.print("Enter hall type [0 to exit");
        for (HallType hall : HallType.values()){
            System.out.println(Integer.toString(x) + " ." + hall);
        }
        HallType type = null;
        while (type == null){
            int choice = UtilityInputs.getIntUserInput();
            switch(choice){
                case 0:
                    return;
                case 1:
                    type = HallType.STANDARD;
                    break;
                case 2:
                    type = HallType.PREMIUM;
                    break;
                case 3: 
                    type = HallType.VIP;
                default:
                    System.out.println("Invalid input");
                }
            }
            int cap = 0;
            while (true){
                System.out.print("Enter seat capacity: ");
                cap = UtilityInputs.getIntUserInput();
                if (cap > 0) break;
            }
            CinemaHandler.getInstance().addCinema(type, cap, c);
        }
    }

    public void updateCinema(){

    }

    public void removeCinema(){

    }
}