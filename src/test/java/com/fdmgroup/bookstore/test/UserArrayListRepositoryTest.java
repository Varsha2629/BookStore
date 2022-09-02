package com.fdmgroup.bookstore.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.bookstore.data.UserArrayListRepository;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;

class UserArrayListRepositoryTest {

	UserArrayListRepository userlistrepo;
	User user1;
	User user2;
	User user3;
	List<User> users_list;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		List<Order> orders = new ArrayList<Order>();
		users_list = new ArrayList<>();
		
		user1 = new User(1, "varsha", "panchal", "var", "1234", "abc@gmail.com", orders);
		user2= new User(2, "sachin", "panchal", "sac", "12345", "abc1@gmail.com",orders);
		user3= new User(3, "hriday", "panchali", "AH", "12345", "abc2@gmail.com",orders);
		
		
		users_list.add(user1);
		users_list.add(user2);
		
		userlistrepo = new UserArrayListRepository(users_list);
	
	}

	@Test
	void getAllUsersTest(){
			
			List<User> users_list1 = userlistrepo.getUsers();
			assertEquals(2,users_list1.size());
		}
	

	@Test
	void uservalidateTest(){
			
			boolean result = userlistrepo.validate("var","1234");
			assertEquals(true, result);
	}
	
	@Test
	void findUsernameTest(){
			
			User result = userlistrepo.findByUsername("var");
			assertEquals(user1, result);
	}
	
	@Test
	void saveUserTest(){
		
		User result = userlistrepo.save(user3);
		assertEquals(user3, result);
	}
	
	@Test
	void updateUserTest(){
			
			User result = userlistrepo.save(user1);
			assertEquals(user1, result);
	}
	
	@Test
	void deleteUserTest(){
		
		User result = userlistrepo.delete(user2);
		assertEquals(user2, result);
	}
	
	@Test
	void generateIdTest(){
		
		int result = userlistrepo.generateId();
		assertEquals(3, result);
	}
	
		
	@Test
	void findByIdTest(){
		
		User result = userlistrepo.findById(1);
		assertNotEquals(2, result);
		assertEquals(user1, result);
	}
	
	
	@Test
	void findAllUsersTest(){
		
		List<User> result = userlistrepo.findAll();
		assertEquals(users_list, result);
	}
	
}
	

