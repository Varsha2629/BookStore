package com.fdmgroup.bookstore.service;

import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.User;

public class AuthenticationService {

	private UserRepository userRepository;
	
	
	public AuthenticationService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
		
	public User authenticate(String username, String password) throws UserNotFoundException{
		
		if(userRepository.validate(username, password)) {		
			return userRepository.findByUsername(username);			
		}else {
			throw new UserNotFoundException("Message");
		
		}
	}	
	public User findById(int uId) throws UserNotFoundException {
		
		if(userRepository.findById(uId) != null) {
			return (User) userRepository.findById(uId);
		}else {
			throw new UserNotFoundException("Message");	
		
		}				
	}	
}
