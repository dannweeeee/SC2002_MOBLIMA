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
     * Create a new show.
     */
    public void createShow();

    /**
     * Update an existing show date & time.
     */
    public void updateShow();

    /**
     * Remove an existing show.
     */
    public void deleteShow();

    /**
     * View all shows.
     */
    public void showAllShows();
    
    /**
     * Create a new cineplex.
     */
    public void addNewCineplex();

    /**
     * Update an existing cineplex.
     */
    public void updateCineplex();

    /**
     * Remove an existing movie cineplex.
     */
    public void removeCineplex();

    /**
     * View all cineplexes.
     */
    public void showAllCineplexes();

    /**
     * Create a new cinema.
     */
    public void addNewCinema();

    /**
     * Update an existing cinema.
     */
    public void updateCinema();

    /**
     * Remove an existing movie cinema.
     */
    public void removeCinema();

    /**
     * View all cinemas.
     */
    public void showAllCinemas();

    /**
     * Launch configure system settings.
     */
    public void manageSettings();
}
