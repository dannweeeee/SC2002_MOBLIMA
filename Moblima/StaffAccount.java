package Moblima;

/**
 * Represent a staff account.
 * @author Nghia Nguyen
 * @version 1.0
 */
public class StaffAccount {
    private String username;
    private String password;
    private boolean isValid = false;
 
    private StaffAccountFileIO fileIO;

    /**
     * Constructor for StaffAccount.
     * @param fileIO    a <code>StaffAccountFileIO</code> object to read and write to the staff account file.
     */
    public StaffAccount(StaffAccountFileIO fileIO) {
        this.fileIO = fileIO;
    }

    /**
     * Username of the staff account. Note that this is unique.
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Password of the staff account.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Check if the current username and password are valid.
     * @return <code>true</code> if the username and password are valid, <code>false</code> otherwise.
     */
    public boolean validate() {
        String password = fileIO.getPassword(username);
        if ((password != null) && (password.equals(this.password))) {
            isValid = true;
        }
        return isValid;
    }
}
