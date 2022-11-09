package Moblima.Admin;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is the UI for the admin module.
 * @author Our team
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
        Scanner sc = new Scanner(System.in);
        int option;
        do {
            System.out.println();
            System.out.println("-------------MOBLIMA ADMIN MAIN MENU--------------");
            System.out.println("| 01: VIEW ALL MOVIE LISTINGS                    |");
            System.out.println("| 02: Create Movie Listing                       |");
            System.out.println("| 03: Update Movie Listing                       |");
            System.out.println("| 04: Remove Movie Listing                       |");
            System.out.println("--------------------------------------------------");
            System.out.println("| 05: VIEW ALL SHOWS                             |");
            System.out.println("| 06: Create Movie Showtime                      |");
            System.out.println("| 07: Update Movie Showtime                      |");
            System.out.println("| 08: Remove Movie Showtime                      |");
            System.out.println("--------------------------------------------------");
            System.out.println("| 09: VIEW ALL CINEPLEXES                        |");
            System.out.println("| 10: Create Cineplex                            |");
            System.out.println("| 11: Update Cineplex                            |");
            System.out.println("| 12: Remove Cineplex                            |");
            System.out.println("--------------------------------------------------");
            System.out.println("| 13: VIEW ALL CINEMAS                           |");
            System.out.println("| 14: Create Cinema                              |");
            System.out.println("| 15: Update Cinema                              |");
            System.out.println("| 16: Remove Cinema                              |");
            System.out.println("--------------------------------------------------");
            System.out.println("| 17: Configure System Settings                  |");
            System.out.println("| 18: ADMIN LOGOUT                               |");
            System.out.println("--------------------------------------------------");
            System.out.println();
            System.out.print("Admin Main Menu - Enter option ('-1' to exit app): ");
            try {
                option = sc.nextInt();
            }catch(InputMismatchException e) {
                System.out.println("Invalid Input. Please re-enter.");
                sc.next();
                continue;
            }
            switch (option) {
            case -1:
                System.out.println("Goodbye!");
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
                //viewallcinemas
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
                System.out.println("Invalid Input. Please re-enter: ");
                continue;
            }
        } while(!exitFlag);
        return null;
    }
}
