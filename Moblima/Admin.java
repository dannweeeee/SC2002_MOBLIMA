package Moblima;

import java.io.IOException;
import java.util.Scanner;

/**
 * Controller of the admin module. Creates and delegates tasks to other classes.
 * @author Nghia Nguyen
 * @version 1.0
 */
public class Admin {
    private static Admin instance = null;
    private StaffLoginForm loginUI;
    private AdminForm adminUI;
    private SettingsController settingsController;
    private movieHandler movieHandler;

    /**
     * Constructor for Admin.
     */
    private Admin() {
        loginUI = new StaffLoginForm(new StaffAccount(new StaffAccountFileIO_I()));
        adminUI = new AdminForm(this);
        settingsController = new SettingsController(new SettingsForm());
    }

    /**
     * Get the single instance of the Admin class.
     * @return the instance of the Admin class.
     */
    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    /**
     * Start the admin module.
     */
    public void start() {
        if (!login()) return;
        adminUI.show();
    }

    /**
     * Launch the login function.
     * @return <code>true</code> if the login is successful, <code>false</code> otherwise.
     */
    private boolean login() {
        return loginUI.show();
    }

    /**
     * Log out and restart the admin module.
     */
    public void logout() {
        loginUI = null;
        adminUI = null;
        start();
    }

    /**
     * Launch the settings function.
     */
    public void manageSettings() {
        settingsController.launch();
    }

    public void writeMovieToTextFile(String fileName) throws IOException{
        Scanner in = new Scanner(System.in);
        String movieName = "";
        String movieStatus = "";
        String movieDirector = "";
        String movieSynopsis = "";
        String movieCasts = "";
        //ArrayList<String> movieCasts = new ArrayList<>();
        System.out.print("Enter full name of movie: ");
        movieName = in.nextLine();
        System.out.print("Enter status of movie (Coming Soon, Now Showing): ");
        movieStatus = in.nextLine();
        System.out.print("Enter director of movie: ");
        movieDirector = in.nextLine();
        System.out.print("Enter synopsis of movie: ");
        movieSynopsis = in.nextLine();
        System.out.print("Enter casts of movie (e.g. Steve Rogers, Borat, Mr Bean): ");
        movieCasts = in.nextLine();
        /*while(in.nextLine() != ""){
            movieCastsString = in.nextLine();
            movieCasts.add(movieCastsString);
        }*/
        //FileWriter movieDatabase = new FileWriter(fileName);
        //PrintWriter write = new PrintWriter(movieDatabase);
        BookMyShow bookMyShow = new BookMyShow();
        Movie addNewMovie = new Movie(movieName, movieStatus, movieDirector, movieSynopsis, movieCasts, bookMyShow.getMovieDatabase());
        //write.println(movieCasts);
        //write.close();
    }
}
