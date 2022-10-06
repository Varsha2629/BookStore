package com.fdmgroup.bookstore.service.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.User;
import com.fdmgroup.bookstore.service.AuthenticationService;
import com.fdmgroup.bookstore.service.UserNotFoundException;

class AuthenticationServiceTest {
	
	AuthenticationService authenticationservice;
	User user1;
	
	@Mock
	UserRepository userRepoMock;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	@BeforeEach
	void setUp() throws Exception {
		
		userRepoMock = mock(UserRepository.class);
		authenticationservice = new AuthenticationService(userRepoMock);
	}

	@Test
	void testUserAuthentication() throws UserNotFoundException {
		
		String username = "Varsha";
		String password = "abc";
		user1 = new User(1, "varsha", "panchal", "var", "1234", "abc@gmail.com", null);
		
		when(userRepoMock.validate(username, password)).thenReturn(true);
		when(userRepoMock.findByUsername(username)).thenReturn(user1);
		
		assertEquals(user1,authenticationservice.authenticate(username, password));
	}
		
	@Test
	void testUserNotFoundException(){
		
		String username = "Varsha";
		String password = "abc";
		
		when(userRepoMock.validate(username, password)).thenReturn(false);
		
		
		try {
			assertEquals(user1,authenticationservice.authenticate(username, password));
			
		} catch (UserNotFoundException e) {
			
		}
		
	}
	
	
	@Test
	void testFindById() throws UserNotFoundException {
		
		int uId =1;
		user1 = new User(1, "varsha", "panchal", "var", "1234", "abc@gmail.com", null);
		
		when(userRepoMock.findById(uId)).thenReturn(user1);
		
		assertEquals(user1, authenticationservice.findById(uId));
	}
	
	
	
	@Test
	void testFindByIdException() throws UserNotFoundException {
		
		int uId =1;
		user1 = new User(1, "varsha", "panchal", "var", "1234", "abc@gmail.com", null);
		
		when(userRepoMock.findById(uId)).thenReturn(null);
		
		try {
			
			assertEquals(user1, authenticationservice.findById(uId));
		} catch (UserNotFoundException e) {
			
		}
	}
}
