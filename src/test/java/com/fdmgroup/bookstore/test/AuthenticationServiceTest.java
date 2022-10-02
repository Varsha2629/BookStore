package com.fdmgroup.bookstore.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;
import com.fdmgroup.bookstore.service.AuthenticationService;
import com.fdmgroup.bookstore.service.UserNotFoundException;

class AuthenticationServiceTest {

	UserRepository userRepository;
	AuthenticationService authenticationService;
	 
	 
	@BeforeEach
	public void setup() {
		
		userRepository = mock(UserRepository.class);
		authenticationService =new  AuthenticationService(userRepository);
		
	}


	@Test
	void testAuthenticate() throws UserNotFoundException {
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(1,new Book(1,7.5,"Jolly","LLB",BookGenre.ACTION),1,LocalDateTime.now()));
		User expectedUser=new User(1,"keshav","ganesh","keshavg","xyz","keshavg@gmail.com",orders);
		
		when(userRepository.validate("keshavg","xyz")).thenReturn(true);
		when(userRepository.findByUsername("keshavg")).thenReturn(new User(1,"keshav","ganesh","keshavg","xyz","keshavg@gmail.com",orders));
		
		User actualUser=authenticationService.authenticate("keshavg", "xyz");
		
		assertEquals(expectedUser,actualUser);
	}
	
	@Test
	void testAuthenticate_userNotFoundException() throws UserNotFoundException {
		Exception exception = assertThrows(UserNotFoundException.class, () -> {
			User actualUser=authenticationService.authenticate("keshavg", "xyz");
	    }); 
	
	    String expectedMessage = "User not Found";
	    String actualMessage = exception.getMessage();
	
	    assertTrue(actualMessage.contains(expectedMessage));
}

	@Test
	void testFindById() throws UserNotFoundException {
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(1,new Book(1,7.5,"Jolly","LLB",BookGenre.ACTION),1,LocalDateTime.now()));
		User expectedUser=new User(1,"keshav","ganesh","keshavg","xyz","keshavg@gmail.com",orders);
		when(userRepository.findById(1)).thenReturn(expectedUser);
		assertEquals(expectedUser,authenticationService.findById(1));		
	}
	
	@Test
	void testFindById_UserNotFoundException() throws UserNotFoundException {
		Exception exception = assertThrows(UserNotFoundException.class, () -> {
			User actualUser=authenticationService.findById(1);
	    }); 
	
	    String expectedMessage = "User not Found";
	    String actualMessage = exception.getMessage();
	
	    assertTrue(actualMessage.contains(expectedMessage));
				
	}

}
