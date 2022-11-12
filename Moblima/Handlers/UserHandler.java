package Moblima.Handlers;

import java.util.ArrayList;

import Moblima.Entities.User;

/**
 * Handler for user objects
 * stores user array
 * @author Ian
 * @see User
 */
public class UserHandler {
	private ArrayList<User> users;
	private static int count=0;
	private static UserHandler instance = null;
	
    /**
     * Get method for instance of UserHandler
     * @return instance
     */
    public static UserHandler getInstance() {
        if (instance == null) {
            instance = new UserHandler();
        }
        return instance;
    }
	
	/**
	 * Default constructor for UserHandler Class
	 */
	public UserHandler() {
		this.users = new ArrayList<>();
		count++;
	}
	/**
	 * Get method for array of users
	 * @return users
	 */
	public ArrayList<User> getUsers(){
		return users;
	}
	
}

