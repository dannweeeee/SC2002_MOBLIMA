package com.ketchup.moblima.admin;

public class StaffAccount {
    private String username;
    private String password;
    private boolean isValid = false;
 
    private StaffAccountFileIO fileIO;

    public StaffAccount(StaffAccountFileIO fileIO) {
        this.fileIO = fileIO;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validate() {
        String password = fileIO.getPassword(username);
        if ((password != null) && (password.equals(this.password))) {
            isValid = true;
        }
        return isValid;
    }
}
