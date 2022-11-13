package Moblima.Admin;

import java.util.InputMismatchException;

import Moblima.Utils.UtilityInputs;
import Moblima.Utils.UtilityOutput;

/**
 * This class is the UI for the admin module.
 * @author Dann Wee
 * @version 1.0
 */
public final class AdminForm extends Form {

    private AdminLogic admin;

    /**
     * Constructor for AdminForm.
     * @param admin the <code>Admin</code> object to be used.
     */
    public AdminForm(AdminLogic admin) {
        super();
        this.admin = admin;
    }

    /**
     * Show the form.
     * @return <code>null</code>.
     */
    public Object show() {
        exitFlag = false;
        //BookMyShowInterface BookMyShow = new BookMyShow();
        String[] menu = {"",
        "-------------MOBLIMA ADMIN MAIN MENU--------------", 
        "| 01: VIEW ALL MOVIE LISTINGS                    |",
        "| 02: Create Movie Listing                       |",
        "| 03: Update Movie Listing                       |",
        "| 04: Remove Movie Listing                       |",
        "--------------------------------------------------",
        "| 05: VIEW ALL SHOWS                             |",
        "| 06: Create Show                                |",
        "| 07: Update Show                                |",
        "| 08: Remove Show                                |",
        "--------------------------------------------------",
        "| 09: VIEW ALL CINEPLEXES                        |",
        "| 10: Create Cineplex                            |",
        "| 11: Update Cineplex                            |",
        "| 12: Remove Cineplex                            |",
        "--------------------------------------------------",
        "| 13: VIEW ALL CINEMAS                           |",
        "| 14: Create Cinema                              |",
        "| 15: Update Cinema                              |",
        "| 16: Remove Cinema                              |",
        "--------------------------------------------------",
        "| 17: Configure System Settings                  |",
        "| 18: ADMIN LOGOUT                               |",
        "--------------------------------------------------"};
        int option;
        do {
            UtilityOutput.printMenu(menu);
            UtilityOutput.printInputMessage("Admin Main Menu - Enter option ('0' to exit app): ");
            try {
                option = UtilityInputs.getIntUserInput();
            }catch(InputMismatchException e) {
                UtilityOutput.printMessage("Invalid Input. Please re-enter.");
                continue;
            }
            switch (option) {
            case 0:
                UtilityOutput.printMessage("Goodbye!");
                System.exit(0);
                return null;
            case 1:
                admin.showAllMovies();
                break;
            case 2:
                admin.createMovie();
                break;
            case 3:
                admin.updateMovie();
                break;
            case 4:
                admin.removeMovie();
                break;
            case 5:
                admin.showAllShows();
                break;
            case 6:
                admin.createShow();
                break;
            case 7:
                admin.updateShow();
                break;
            case 8:
                admin.deleteShow();
                break;
            case 9:
                admin.showAllCineplexes();
                break;
            case 10:
                admin.addNewCineplex();
                break;
            case 11:
                admin.updateCineplex();
                break;
            case 12:
                admin.removeCineplex();
                break;
            case 13:
                admin.showAllCinemas();
                break;
            case 14:
                admin.addNewCinema();
                break;
            case 15:
                admin.updateCinema();
                break;
            case 16:
                admin.removeCinema();
                break;
            case 17:
                admin.manageSettings();
                break;
            case 18:
                admin.exit();
                this.exit();
                break;
            default:
                UtilityOutput.printMessage("Invalid Input. Please re-enter: ");
                continue;
            }
        } while(!exitFlag);
        return null;
    }
}
