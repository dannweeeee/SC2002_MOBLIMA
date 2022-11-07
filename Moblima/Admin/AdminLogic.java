package Moblima.Admin;

/**
 * This is the interface containing admin side business logics that are used by the admin UI.
 * @author Our team
 * @version 1.0
 */
public interface AdminLogic {
    /**
     * Start the admin module.
     */
    public void start();

    /**
     * Exit the admin module.
     */
    public void exit();

    /**
     * Create a new movie listing.
     */
    public void createMovie();

    /**
     * Update an existing movie listing.
     */
    public void updateMovie();

    /**
     * Remove an existing movie listing.
     */
    public void removeMovie();

    /**
     * View all movie listings.
     */
    public void showAllMovies();

    /**
     * Create a new movie showtime.
     */
    public void createShow();

    /**
     * Update an existing movie showtime.
     */
    public void updateShow();

    /**
     * Remove an existing movie showtime.
     */
    public void removeShow();

    /**
     * Launch configure system settings.
     */
    public void manageSettings();
}
