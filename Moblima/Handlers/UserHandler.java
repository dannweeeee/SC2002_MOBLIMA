package Moblima.Handlers;

import java.util.ArrayList;

import Moblima.Entities.User;

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
		return users.size()-1;
	}
}

