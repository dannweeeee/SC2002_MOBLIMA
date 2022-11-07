package Moblima.Admin;

import java.util.InputMismatchException;
import java.util.Scanner;

import Moblima.MovieBookerApp;
import Moblima.MovieBookerInterface;
import Moblima.Entities.Movie;
import Moblima.Utils.SettingsController;
import Moblima.Utils.SettingsForm;
import Moblima.Handlers.MovieHandler;
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
    private Scanner in;

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
        in = new Scanner(System.in);
        String movieAddName, movieAddStatus, movieAddDirector, movieAddSynopsis, movieAddCasts;
        System.out.print("Enter full name of Movie: ");
        movieAddName = in.nextLine();
        System.out.print("Enter status of Movie (Coming Soon, Now Showing): ");
        while (true){
            movieAddStatus = in.nextLine();
            if (movieAddStatus == "Coming Soon"){
                break;
            }
            else if (movieAddStatus == "Now Showing"){
                break;
            }
            else{
                System.out.println("Invalid Input. Please re-enter.");
            }
        }
        System.out.print("Enter director of Movie: ");
        movieAddDirector = in.nextLine();
        System.out.print("Enter synopsis of Movie: ");
        movieAddSynopsis = in.nextLine();
        System.out.print("Enter casts of Movie (e.g. Steve Rogers, Borat, Mr Bean): ");
        movieAddCasts = in.nextLine();
        Movie addNewMovie = movieHandler.createMovie(movieAddName, movieAddStatus, movieAddDirector, movieAddSynopsis, movieAddCasts);
        System.out.print("The following Movie has been added!: ");
        System.out.print(addNewMovie);
    }

    public void updateMovie() {
        in = new Scanner(System.in);
        int movieOption = 0, moviePartOption = 0;
        String movieName, movieStatus, movieDirector, movieSynopsis, movieCasts;
        String movieUpdateName, movieUpdateStatus, movieUpdateDirector, movieUpdateSynopsis, movieUpdateCasts;
        showAllMovies();
        System.out.print("Which Movie would you like to update? (e.g. 1): ");
        while (true){
            try{
                movieOption = in.nextInt();
            }catch(Exception e){
                in.next();
            }
            if (movieOption < movieHandler.sizeMovie()+1 && movieOption > 0){
                break;
            }
            else{
                System.out.println("Invalid Input. Please re-enter.");
            }
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
                    selectedMovie.updateName(movieUpdateName);
                    break;
                case 2:
                    System.out.print("Update status of Movie (Coming Soon, Now Showing): ");
                    in = new Scanner(System.in);
                    movieUpdateStatus = in.nextLine();
                    selectedMovie.updateStatus(movieUpdateStatus);
                    break;
                case 3:
                    System.out.print("Update director of Movie: ");
                    in = new Scanner(System.in);
                    movieUpdateDirector = in.nextLine();
                    selectedMovie.updateDirector(movieUpdateDirector);
                    break;
                case 4:
                    System.out.print("Update casts of Movie (e.g. Steve Rogers, Borat, Mr Bean): ");
                    in = new Scanner(System.in);
                    movieUpdateCasts = in.nextLine();
                    selectedMovie.updateCasts(movieUpdateCasts);
                    break;
                case 5:
                    System.out.print("Update synopsis of movie: ");
                    in = new Scanner(System.in);
                    movieUpdateSynopsis = in.nextLine();
                    selectedMovie.updateSynopsis(movieUpdateSynopsis);
                    break;
                default:
                    System.out.println("Invalid Input. Please re-enter");
            }
        } while(moviePartOption != -1);
        System.out.println(selectedMovie);

    }

    public void removeMovie() {
        int movieRemoveOption = 0;
		showAllMovies();
		System.out.print("Which Movie would you like to delete? (e.g. 1): ");
        while (true){
            try{
                movieRemoveOption = in.nextInt();
            }catch(Exception e){
                in.next();
            }
            if (movieRemoveOption < movieHandler.sizeMovie()+1 && movieRemoveOption > 0){
                break;
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

    public void showAllMovies() {
        movieBooker.showAllMovies();
    }

    public void createShow() {
        // Put implementation of createShow here, or call similar method in another class
    }

    public void updateShow() {
        // Put implementation of updateShow here, or call similar method in another class
    }

    public void removeShow() {
        // Put implementation of removeShow here, or call similar method in another class
    }

    public void manageSettings() {
        settingsController.launch();
    }
}