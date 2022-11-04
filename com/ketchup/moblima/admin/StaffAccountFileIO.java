package com.ketchup.moblima.admin;

/**
 * This interface handles the reading and writing to the staff account file.
 * @author Nghia Nguyen
 * @version 1.0
 */
public interface StaffAccountFileIO {
    /**
     * Get the password of a staff account with corresponding <code>username</code>.
     * @param username  the username of the staff account.
     * @return the password of the staff account.
     */
    public abstract String getPassword(String username);
}
