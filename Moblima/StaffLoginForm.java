package Moblima;

import java.util.Scanner;

/**
 * This is the UI for the login function of the admin module.
 * @author Nghia Nguyen
 * @version 1.0
 */
public final class StaffLoginForm extends Form {
    private boolean loginSuccess = false;
    private StaffAccount account;
    /**
     * Constructor for StaffLoginForm.
     * @param account   a <code>StaffAccount</code> object to record the login result.
     */
    public StaffLoginForm(StaffAccount account) {
        super();
        this.account = account;
    }
    /**
     * Show the form
     * @return <code>true</code> if the login is successful, <code>false</code> otherwise.
     */
    public Boolean show() {
        exitFlag = false;
        Scanner sc = new Scanner(System.in);
        while (!exitFlag) {
            System.out.println("Staff Login");
            System.out.println("===========");
            System.out.println("Username: ");
            account.setUsername(sc.nextLine());
            System.out.println("Password: ");
            account.setPassword(sc.nextLine());
            if (account.validate()) {
                loginSuccess = true;
                exit();
            } else {
                System.out.println("Invalid username or password.");
                System.out.println("Press 0 to exit, any other key to try again.");
                String choice = sc.nextLine();
                if (choice.equals("0")) {
                    exit();
                }
            }
        }
        return loginSuccess;
    }
}
