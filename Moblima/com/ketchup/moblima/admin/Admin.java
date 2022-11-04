package com.ketchup.moblima.admin;

/**
 * Controller of the admin module. Creates and delegates tasks to other classes.
 * @author Nghia Nguyen
 * @version 1.0
 */
public class Admin {
    private static Admin instance = null;
    private StaffLoginForm loginUI;
    private AdminForm adminUI;
    private SettingsController settingsController;

    /**
     * Constructor for Admin.
     */
    private Admin() {
        loginUI = new StaffLoginForm(new StaffAccount(new StaffAccountFileIO_I()));
        adminUI = new AdminForm(this);
        settingsController = new SettingsController(new SettingsForm());
    }

    /**
     * Get the single instance of the Admin class.
     * @return the instance of the Admin class.
     */
    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    /**
     * Start the admin module.
     */
    public void start() {
        if (!login()) return;
        adminUI.show();
    }

    /**
     * Launch the login function.
     * @return <code>true</code> if the login is successful, <code>false</code> otherwise.
     */
    private boolean login() {
        return loginUI.show();
    }

    /**
     * Log out and restart the admin module.
     */
    public void logout() {
        loginUI = null;
        adminUI = null;
        start();
    }

    /**
     * Launch the settings function.
     */
    public void manageSettings() {
        settingsController.launch();
    }
}
