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

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

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

    public void showAllMovies(){
        movieBooker.showAllMovies();
    }

    public void createMovie() {
        String movieAddName, movieAddStatus, movieAddDirector, movieAddSynopsis, movieAddCasts;
        System.out.print("Enter full name of Movie: ");
        movieAddName = UtilityInputs.getStringUserInput();
        System.out.print("Enter status of Movie (Coming Soon, Now Showing, Preview, End of Showing) => ");
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
        System.out.print("Enter director of Movie => ");
        movieAddDirector = UtilityInputs.getStringUserInput();
        System.out.print("Enter casts of Movie (e.g. Steve Rogers, Borat, Mr Bean) => ");
        movieAddCasts = UtilityInputs.getStringUserInput();
        System.out.print("Enter synopsis of Movie => ");
        movieAddSynopsis = UtilityInputs.getStringUserInput();
        Movie addNewMovie = movieHandler.createMovie(movieAddName, movieAddStatus, movieAddDirector, movieAddSynopsis, movieAddCasts);
        System.out.println("SUCCESS! The following Movie has been added!");
        System.out.println(addNewMovie);
    }

    public void updateMovie() {
        int movieOption = 0, moviePartOption = 0;
        String movieUpdateValue;
        movieBooker.showAllMovies();
        while (true){
            System.out.print("Which Movie would you like to update? (e.g. 1) [Enter '0' to exit] => ");
            movieOption = UtilityInputs.getIntUserInput();
            if (movieOption == 0) return;
            else if (movieOption == -1){
                System.out.println("Please re-enter.");
                continue;
            }
            else if (movieOption < -1 || movieOption > movieHandler.sizeMovie()){
                System.out.println("Invalid input. Please re-enter.");
                continue;
            }
            else{
                break;
            }
        }
        Movie selectedMovie = movieHandler.getMovie().get(movieOption-1);
        System.out.println("1.Title: " + selectedMovie.getName());
        System.out.println("2.Status: " + selectedMovie.getStatus());
        System.out.println("3.Director: " + selectedMovie.getDirector());
        System.out.println("4.Cast: " + selectedMovie.getCast());
        System.out.println("5.Synopsis: " + selectedMovie.getSynopsis());

        while(true){
            System.out.print("Select which to update [Enter '0' to confirm & exit] => ");
            moviePartOption = UtilityInputs.getIntUserInput();
            if (moviePartOption == 0){
                break;
            }
            else if (moviePartOption == -1){
                System.out.println("Please re-enter.");
                continue;
            }
            else if (moviePartOption < -1 || moviePartOption > 5){
                System.out.println("Invalid input. Please re-enter.");
                continue;
            }
            else {
                System.out.print("Enter updated value => ");
                movieUpdateValue = UtilityInputs.getStringUserInput();
                switch(moviePartOption){
                case 1:
                    selectedMovie.setName(movieUpdateValue);
                    break;
                case 2:
                    if (movieUpdateValue.equals("Coming Soon")){
                        selectedMovie.setStatus(movieUpdateValue);
                       break;
                    }
                    else if (movieUpdateValue.equals("Now Showing")){
                        selectedMovie.setStatus(movieUpdateValue);
                        break;
                    }
                    else if (movieUpdateValue.equals("Preview")){
                        selectedMovie.setStatus(movieUpdateValue);
                        break;
                    }
                    else if (movieUpdateValue.equals("End of Showing")){
                        selectedMovie.setStatus(movieUpdateValue);
                        break;
                    }
                    else{
                        System.out.println("Invalid Input. Please re-enter.");
                        break;
                    }
                case 3:
                    selectedMovie.setDirector(movieUpdateValue);
                    break;
                case 4:
                    selectedMovie.setCast(movieUpdateValue);
                    break;
                case 5:
                    selectedMovie.setSynopsis(movieUpdateValue);
                    break;
                }
            }
        } 
        System.out.println("UPDATED! The following movie has been updated!");
        System.out.println(selectedMovie);
    }

    public void removeMovie() {
        int movieRemoveOption = 0;
		movieBooker.showAllMovies();
        while (true){
            System.out.print("Which Movie would you like to delete? (e.g. 1) [Enter '0' to exit] => ");
            movieRemoveOption = UtilityInputs.getIntUserInput();
            if (movieRemoveOption == 0){
                break;
            }
            else if (movieRemoveOption == -1){
                System.out.println("Please re-enter.");
                continue;
            }
            else if (movieRemoveOption < -1 || movieRemoveOption > movieHandler.sizeMovie()){
                System.out.println("Invalid Input. Please re-enter.");
                continue;
            }
            else{
                Movie selectedMovie = movieHandler.getMovie().get(movieRemoveOption-1);
                System.out.println("DELETED! The following movie has been deleted!");
                System.out.println(selectedMovie);
                movieHandler.removeMovie(movieRemoveOption-1);
                break;
            }
        }
    }

    public void showAllShows(){
        while(true){
            if (showHandler.getAllShows().size() != 0 ){
			    ShowHandler.printAllShows(showHandler.getAllShows());
                break;
            }else{
                System.out.println("No Shows shown.");
                break;
            }
        }
    }

    public void createShow() {
        CineplexHandler cineplexHandler = CineplexHandler.getInstance();
		MovieHandler movieHandler = MovieHandler.getInstance();
		ShowHandler showHandler = ShowHandler.getInstance();
		SeatHandler seatHandler = SeatHandler.getInstance();
		int movieOption = -1, cineplexOption =-1, cinemaOption =-1;
		String dateInString;
		Date showtime = null;
		
		movieBooker.showAllMovies();
        while (true){
		    System.out.print("Select Movie to create show [Enter '0' to exit] => ");
		    movieOption = UtilityInputs.getIntUserInput();
            if (movieOption == 0) return;
            else if (movieOption == -1){
                System.out.println("Please re-enter.");
                continue;
            }
            else if (movieOption < -1 || movieOption > movieHandler.sizeMovie()){
                System.out.println("Invalid input. Please re-enter.");
                continue;
            }
            else{
                break;
            }
        }
        Movie selectedMovie = movieHandler.getMovie().get(movieOption-1);
		while(true){
            System.out.print("Enter date and showtime (Format: 31/07/2022 21:00:00) => ");
		    dateInString = UtilityInputs.getStringUserInput();
		    try {
		    	showtime = formatter.parse(dateInString);
		    	System.out.println(showtime);
                break;
		    } catch (ParseException e) {
		    	System.out.println("Wrong date format. Please re-enter.");
		    }
        }
        if (cineplexHandler.getAllCineplex().size() != 0 ){
            cineplexHandler.printAllCineplex();
            while (true){
                System.out.print("Select Cineplex [Enter '0' to exit] => ");
                cineplexOption = UtilityInputs.getIntUserInput();
                if (cineplexOption == 0) return;
                else if (cineplexOption == -1){
                    System.out.println("Please re-enter.");
                    continue;
                }
                else if (cineplexOption < -1 || cineplexOption > cineplexHandler.getAllCineplex().size()){
                    System.out.println("Invalid input. Please re-enter.");
                    continue;
                }
                else{
                    break;
                }
            }
            Cineplex cineplex = cineplexHandler.getAllCineplex().get(cineplexOption-1);
            if (CinemaHandler.getInstance().getCinemaFromCineplex(cineplex).size() != 0){
                for (Cinema c : CinemaHandler.getInstance().getCinemaFromCineplex(cineplex)){
                    System.out.println(c);
                }
                while (true){
                    System.out.print("Select Cinema => ");
                    cinemaOption = UtilityInputs.getIntUserInput();
                    if (cinemaOption == 0) return;
                    else if (cinemaOption == -1){
                        System.out.println("Please re-enter.");
                        continue;
                    }
                    else if (cinemaOption < -1 || cinemaOption > CinemaHandler.getInstance().getCinemaFromCineplex(cineplex).size()){
                        System.out.println("Invalid input. Please re-enter.");
                        continue;
                    }
                    else{
                        break;
                    }
                }
		        Show show1 = showHandler.addShows(showtime,selectedMovie,CinemaHandler.getInstance().getCinemaFromCineplex(cineplex).get(cinemaOption-1), seatHandler);
                System.out.println("SUCCESS! The following show has been created!");
		        System.out.println(show1);
            }
            else{
                System.out.println("No Cinema shown. Please head back to the Main Menu to Initalise Example.");
            }
        }
        else{
            System.out.println("No Cineplex shown. Please head back to the Main Menu to Initalise Example.");
        }
    }

    public void updateShow() {
        ShowHandler showHandler = ShowHandler.getInstance();
		while(true){
            if (showHandler.getAllShows().size() != 0 ){
			    ShowHandler.printAllShows(showHandler.getAllShows());
                int choice = -1;
			    while (true){
                    System.out.print("Enter which show time you want to edit [Enter '0' to exit] => ");
                    choice = UtilityInputs.getIntUserInput();
                    if (choice == 0) return;
                    else if (choice == -1){
                        System.out.println("Please re-enter.");
                        continue;
                    }
                    else if (choice < -1 || choice > showHandler.getAllShows().size()){
                        System.out.println("Invalid input. Please re-enter.");
                        continue;
                    }
                    else{
                        break;
                    }
                }
			    Show selectedShow = ShowHandler.getShowByID(showHandler.getAllShows(), choice);
			    System.out.println("You have selected:\n" + selectedShow.toString());
                while(true){
                    System.out.print("Enter new show time for this movie [Format: 31/07/2022 21:00:00] => ");
			        String dateInString = UtilityInputs.getStringUserInput();
			        try{
				        Date date = formatter.parse(dateInString);
				        selectedShow.setShowTime(date);
                        break;
			        }catch(ParseException e){
				        System.out.println("Wrong date format. Please re-enter.");
			        }
                }
                System.out.println("UPDATED! The following show has been updated!");
                System.out.println(selectedShow);
            }else{
                System.out.println("No Shows shown. Please head back to the Main Menu to Initalise Example.");
                break;
            }
		}	
    }

    public void deleteShow(){
        if (showHandler.getAllShows().size() != 0 ){
            ArrayList<Show> allShows = ShowHandler.getInstance().getAllShows();
            ShowHandler.printAllShows(allShows);
            Show selectedShow = UtilityInputs.getShow(allShows);
            ShowHandler.getInstance().removeShow(selectedShow);
            System.out.println("DELETED! The Show has been deleted!");
            System.out.println(selectedShow);
        }else{
            System.out.println("No Shows shown. Please head back to the Main Menu to Initalise Example");
        }
    }

    public void showAllCineplexes(){
        CineplexHandler cineplexHandler = CineplexHandler.getInstance();
        if (cineplexHandler.getAllCineplex().size() != 0 ){
            cineplexHandler.printAllCineplex();
        }
        else{
            System.out.println("No Cineplex shown.");
        }
    }

    public void addNewCineplex(){
        CineplexHandler cineplexHandler = CineplexHandler.getInstance();
        System.out.print("Enter Cineplex location [Enter '0' to exit] => ");
        String location = UtilityInputs.getStringUserInput();
        if (location.equals("0")) {
            return;
        }
        cineplexHandler.addCineplex(location, CinemaHandler.getInstance());
        System.out.println("SUCCESS! The Cineplex has been added!");
        cineplexHandler.printAllCineplex();
    }

    public void updateCineplex(){
        CineplexHandler cineplexHandler = CineplexHandler.getInstance();
        int cineplexOption = -1;
        cineplexHandler.printAllCineplex();
        while(true){
            System.out.print("Select which cineplex to update [Enter '0' to confirm & exit] => ");
            cineplexOption = UtilityInputs.getIntUserInput();
            if (cineplexOption == 0) return;
            else if (cineplexOption == -1){
                System.out.println("Please re-enter.");
                continue;
            }
            else if (cineplexOption < -1 || cineplexOption > cineplexHandler.getSize()){
                System.out.println("Invalid input. Please re-enter.");
                continue;
            }
            else{
                Cineplex c = CineplexHandler.getInstance().getAllCineplex().get(cineplexOption-1);
                System.out.print("Enter updated value => ");
                String cineplexUpdateString = UtilityInputs.getStringUserInput();
                c.setCineplex(cineplexUpdateString);
                System.out.println("UPDATED! The following cineplex has been updated!");
                System.out.println(c);
                break;
            }
        }
    }

    public void removeCineplex(){
        CineplexHandler cineplexHandler = CineplexHandler.getInstance();
        cineplexHandler.printAllCineplex();
        while (true){
            System.out.print("Select which cineplex to remove [Enter '0' to confirm & exit] => ");
            int choice = UtilityInputs.getIntUserInput();
            if (choice == 0) return;
            else if (choice == -1){
                System.out.println("Please re-enter.");
                continue;
            }
            else if (choice < -1 || choice > cineplexHandler.getSize()){
                System.out.println("Invalid input. Please re-enter.");
                continue;
            }
            else{
                Cineplex c = CineplexHandler.getInstance().getAllCineplex().get(choice - 1);
                CineplexHandler.getInstance().removeCineplex(c);
                System.out.println("Removed Successfully");
                break;
            }
        }
    }

    public void showAllCinemas(){

    }

    public void addNewCinema(){
        CineplexHandler cineplexHandler = CineplexHandler.getInstance();
        cineplexHandler.printAllCineplex();
        int userChoice = -1;
        while (true){
            System.out.print("Which cineplex would you like to add Cinema [0 to exit]=> ");
            userChoice = UtilityInputs.getIntUserInput();
            if (userChoice == 0) return;
            else if (userChoice == -1){
                System.out.println("Please re-enter.");
                continue;
            }
            else if (userChoice < -1 || userChoice > cineplexHandler.getSize()){
                System.out.println("Invalid input. Please re-enter.");
                continue;
            }
            else{
                break;
            }
        }
        Cineplex c = CineplexHandler.getInstance().getAllCineplex().get(userChoice - 1);
        int x = 1;
        for (HallType hall : HallType.values()){
            System.out.println(Integer.toString(x) + ". " + hall);
            x++;
        }
        HallType type = null;
        while (type == null){
            System.out.print("Enter hall type [0 to exit] => ");
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
                    System.out.println("Please re-enter.");
                }
            }
            int cap = 0;
            while (true){
                System.out.print("Enter seat capacity: ");
                cap = UtilityInputs.getIntUserInput();
                if (cap > 0) break;
            }
            CinemaHandler.getInstance().addCinema(type, cap, c);
            System.out.println("SUCCESS! The Cinema has been created!");
    }

    public void updateCinema(){
        CineplexHandler.getInstance().printAllCineplex();
        while (true){
            System.out.print("Which cineplex would you like to add Cinema [0 to exit]=> ");
            int userChoice = UtilityInputs.getIntUserInput();
            if (userChoice == 0){
            System.out.println("Returning...");
            break;
        }
        CineplexHandler.getInstance().getAllCineplex().get(userChoice - 1);
        if (userChoice > CineplexHandler.getInstance().getAllCineplex().size() || userChoice < 0){
            System.out.println("Invalid Input");
            continue;
        }
        int x = 1;
        for (HallType hall : HallType.values()){
            System.out.println(Integer.toString(x) + " ." + hall);
            x++;
        }
        System.out.print("Enter hall type [0 to exit] ");
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
                System.out.print("Enter updated seat capacity: ");
                cap = UtilityInputs.getIntUserInput();
                if (cap > 0) break;
            }

        }
    }

    public void removeCinema(){

    }

    public void manageSettings() {
        settingsController.launch();
    }
}