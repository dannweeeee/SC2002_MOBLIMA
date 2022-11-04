package com.ketchup.moblima.admin;

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
        Scanner sc = new Scanner(System.in);
        while (!exitFlag) {
            System.out.println("MOBLIMA - Admin");
            System.out.println("===============");
            System.out.println("1. Add movie to catalog");
            System.out.println("2. Edit movie details");
            System.out.println("3. Remove movie from catalog");
            System.out.println("4. Add showtime");
            System.out.println("5. Edit showtime");
            System.out.println("6. Remove showtime");
            System.out.println("7. Settings");
            System.out.println("8. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 7:
                    admin.manageSettings();
                    break;
                case 8:
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
        return null;
    }
}
