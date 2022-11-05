package Moblima;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class is the UI for the admin module.
 * @author Nghia Nguyen
 * @version 1.0
 */
public final class AdminForm extends Form {

    private Admin admin;

    /**
     * Constructor for AdminForm.
     * @param admin the <code>Admin</code> object to be used.
     */
    public AdminForm(Admin admin) {
        super();
        this.admin = admin;
    }

    /**
     * Show the form.
     * @return <code>null</code>.
     */
    public Object show() {
        exitFlag = false;
        BookMyShowInterface BookMyShow = new BookMyShow();
        Scanner sc = new Scanner(System.in);
        while (!exitFlag) {
            System.out.println();
            System.out.println("----------------MOBLIMA ADMIN MENU----------------");
            System.out.println("| 01: Create Movie Listing                       |");
            System.out.println("| 02: Update Movie Listing                       |");
            System.out.println("| 03: Remove Movie Listing                       |");
            System.out.println("| 04: View all Movie Listings                    |");
            System.out.println("| 05: Create Movie Showtime                      |");
            System.out.println("| 06: Update Movie Showtime                      |");
            System.out.println("| 07: Remove Movie Showtime                      |");
            System.out.println("| 08: Configure System Settings                  |");
            System.out.println("| 09: Movie Goer View                            |");
            System.out.println("| 10: Log out                                    |");
            System.out.println("--------------------------------------------------");
            System.out.println();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                try {
                    AdminView adminView = new AdminView();
                    adminView.writeMovieToTextFile("MovieList.txt");
                } catch (IOException e) {

                }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    BookMyShow.showAllMovies();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    admin.manageSettings();
                    break;
                case 9:
                    MovieGoer movieGoer = new MovieGoer();
                    break;
                case 10:
                    this.exit();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
        return null;
    }
}
