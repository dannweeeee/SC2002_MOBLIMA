package Moblima.Admin;

import Moblima.MovieBookerApp;
import Moblima.MovieBookerInterface;
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

    /**
     * Start the admin module.
     */
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

    /**
     * Exit the admin module.
     */
    public void exit() {
        loginUI = null;
        adminUI = null;
        settingsController = null;
        MovieBookerApp.showUserView(movieBooker);
    }

    public void createMovie() {
        // Put implementation of createMovie here, or call similar method in another class
    }

    public void updateMovie() {
        // Put implementation of updateMovie here, or call similar method in another class
    }

    public void removeMovie() {
        // Put implementation of removeMovie here, or call similar method in another class
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

    /**
     * Launch the settings function.
     */
    public void manageSettings() {
        settingsController.launch();
    }
}