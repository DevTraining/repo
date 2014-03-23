package com.dev.training;

import java.util.ArrayList;
import java.util.List;

import com.dev.training.bean.User;

public class DBUser {

	private static List<User> users = new ArrayList<User>();

	public DBUser() {
		
	}
	
	
	public static void addUser(User user){
		users.add(user);
	}
	
	public static List<User> getUsers(){
		return users;
	}
	public static User getUser(String username){
		for(User u:users ){
			if(u.getUsername().equalsIgnoreCase(username)){
			return u;
			}
		}
		return new User();
	}
	public static int updateUser(User user){
		for(User u:users ){
			if(u.getUsername().equalsIgnoreCase(user.getUsername())){
				users.remove(u);
				users.add(user);
				return 1;
			}
				
		}
		return 0;
	}
	
	public static int removeUser(User user){
		for(User u:users ){
			if(u.getUsername().equalsIgnoreCase(user.getUsername())){
				users.remove(u);
				return 1;
			}
				
		}
		return 0;
	}
}
