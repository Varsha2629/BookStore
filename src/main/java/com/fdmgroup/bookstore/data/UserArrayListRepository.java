package com.fdmgroup.bookstore.data;


import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.bookstore.model.User;

public class UserArrayListRepository implements UserRepository{
	
	
	List<User> users = new ArrayList<User>();	
	public static int id=1;
	
		
	public UserArrayListRepository(int id) {
		super();
		this.id = id;
	}

	public UserArrayListRepository(List<User> users) {
		super();
		this.users = users;
	}
	
	public int generateId() {
		return id++; 
	}	
		
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	@Override
	public User findById(int id) {
		for (User user : users) {
			
			if(user.getUserId() == id) {
				return user;
			}
		}
		return null;
	}
	
	@Override
	public List<User> findAll() {
		
//		System.out.println(users);
		return users;
	}

	
	@Override
	public boolean validate(String username, String password) {
		for (User user : users) {
			
			if(user.getUserName().equals(username) && user.getPassword().equals(password)){
				return true;
			} 
		}
		return false;
	
	}
	@Override
	public User findByUsername(String username) {
		for (User user : users) {
			
			if(user.getUserName().equals(username)){
				return user;
			} 
		}
		return null;
	
	}
	
	@Override
	public User save(User user) {
			
			int uId = generateId();
			User saveUser = findByUsername(user.getUserName()); 
			if(saveUser != null && saveUser.equals(user)) {
				user.setUserId(uId);
				
			}else {
				user.setUserId(uId);
				users.add(user);
			}
			
			return user;
		}	


	@Override
	public User delete(User user) {
		
		if(findByUsername(user.getUserName()).equals(user)) {
			users.remove(user);
		}
		return user;
	}

	
	

}
