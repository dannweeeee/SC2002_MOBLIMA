package Moblima.Admin;

import java.util.HashMap;

/**
 * This class contains the staff accounts.
 * @author Dann Wee
 * @version 1.0
 */
public class IDandPasswords {
    HashMap<String,String> logininfo = new HashMap<String,String>();

    /**
     * Constructor of the class.
     * <p>This constructor will load predefined accounts into IDandPasswords class.</p>
     */
    IDandPasswords(){
        logininfo.put("dexter","ahgui");
        logininfo.put("ian","dimsum");
        logininfo.put("marcus","khoolkid");
        logininfo.put("dann","weeeee");
        logininfo.put("ngiah","nguyen");
    }

    /**
     * Get all the accounts.
     * @return a <code>HashMap</code> containing all accounts.
     */
    protected HashMap getLoginInfo(){
        return logininfo;
    }
}
