package Moblima.Handlers;

import java.util.ArrayList;

import Moblima.Entities.User;

/**
 * @author Ian
 *
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
	 * @return
	 */
	public ArrayList<User> getUsers(){
		return users;
	}
	/**
	 * @return
	 */
	public int getSize() {
		return users.size()-1;
	}
}

