package Moblima.Handlers;

import java.util.ArrayList;

import Moblima.Entities.User;

/**
 * Handler for user objects
 * stores user array
 * @author Ian
 *@see User
 */
public class UserHandler {
	private ArrayList<User> users;
	private static int count=0;
	/**
	 * 
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

