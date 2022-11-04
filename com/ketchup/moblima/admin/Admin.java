package com.ketchup.moblima.admin;

public class Admin {
    private static Admin instance = null;
    private StaffLoginForm loginUI;
    private AdminForm adminUI;
    private SettingsController settingsController;

    private Admin() {
        loginUI = new StaffLoginForm(new StaffAccount(new StaffAccountFileIO_I()));
        adminUI = new AdminForm(this);
        settingsController = new SettingsController(new SettingsForm(settingsController));
    }

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    public void start() {
        if (!login()) return;
        adminUI.show();
    }

    private boolean login() {
        return loginUI.show();
    }

    public void logout() {
        loginUI = null;
        adminUI = null;
        start();
    }

    public void manageSettings() {
        settingsController.launch();
    }
}
