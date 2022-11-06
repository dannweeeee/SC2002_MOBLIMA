package Moblima;

import java.util.ArrayList;

public class UserHandler {
	private ArrayList<User> users;
	private static int count=0;
	public UserHandler() {
		this.users = new ArrayList<>();
		count++;
	}
	public ArrayList<User> getUsers(){
		return users;
	}
	public int getSize() {
		return count;
	}
}
