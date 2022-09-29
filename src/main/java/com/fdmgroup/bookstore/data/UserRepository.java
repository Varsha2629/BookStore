package com.fdmgroup.bookstore.data;

import com.fdmgroup.bookstore.model.User;

public interface UserRepository extends Searchable{
	
	 public boolean validate(String username, String password);
	 public User findByUsername(String username);	  
	
	 
}
