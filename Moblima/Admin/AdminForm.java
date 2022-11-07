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
            System.out.println("| 01: View all Movie Listings                    |");
            System.out.println("| 02: Create Movie Listing                       |");
            System.out.println("| 03: Update Movie Listing                       |");
            System.out.println("| 04: Remove Movie Listing                       |");
            System.out.println("| 05: Create Movie Showtime                      |");
            System.out.println("| 06: Update Movie Showtime                      |");
            System.out.println("| 07: Remove Movie Showtime                      |");
            System.out.println("| 08: Create Cineplex                            |");
            System.out.println("| 09: Update Cineplex                            |");
            System.out.println("| 10: Remove Cineplex                            |");
            System.out.println("| 11: Create Cinema                              |");
            System.out.println("| 12: Update Cinema                              |");
            System.out.println("| 13: Remove Cinema                              |");
            System.out.println("| 14: Configure System Settings                  |");
            System.out.println("| 15: ADMIN LOGOUT                               |");
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
                admin.createShow();
                break;
            case 6:
                admin.updateShow();
                break;
            case 7:
                admin.deleteShow();
                break;
            case 8:
                admin.addNewCineplex();
                break;
            case 9:
                admin.updateCineplex();
                break;
            case 10:
                admin.removeCineplex();
                break;
            case 11:
                admin.addNewCinema();
                break;
            case 12:
                admin.updateCinema();
                break;
            case 13:
                admin.removeCinema();
                break;
            case 14:
                admin.manageSettings();
                break;
            case 15:
                admin.exit();
                this.exit();
                break;
            default:
                System.out.println("Invalid Input. Please re-enter.");
                continue;
            }
        } while(!exitFlag);
        return null;
    }
}
