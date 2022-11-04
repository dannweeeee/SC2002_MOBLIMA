package Moblima;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminPage {
    private static Scanner in;
    
    public AdminPage() {
        BookMyShowInterface BookMyShow = new BookMyShow();
        int option = 0;
        in = new Scanner(System.in);
        do {
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
            System.out.println("--------------------------------------------------");
            System.out.println();
            while(true) {
                System.out.print("Admin Main Menu - Enter option ('-1' to exit): ");
                try {
                    option = in.nextInt();
                }catch(InputMismatchException e) {
                    System.out.println("Invalid Input. Please re-enter.");
                    in.next();
                    continue;
                }
                
                switch (option) {
                case -1:
                    BookMyShowApp bookMyShowApp = new BookMyShowApp();
                    return;
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
                    break;
                }
            }
        } 
        while(option != -1);
    }
}
