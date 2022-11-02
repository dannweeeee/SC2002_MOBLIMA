package com.ketchup.moblima.admin;

public class Admin {
    private static Admin instance = null;
    private Settings settings;
    private StaffLoginForm loginUI;
    private AdminForm adminUI;

    private Admin() {
        settings = Settings.getInstance();
    }

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    public void start() {
        settings.load();
        if (!login()) return;
        adminUI = new AdminForm(this);
        adminUI.show();
    }

    private boolean login() {
        loginUI = new StaffLoginForm(new StaffAccount_I(new StaffAccountFileIO_I()));
        return loginUI.show();
    }
}
