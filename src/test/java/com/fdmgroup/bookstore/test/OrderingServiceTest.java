package com.fdmgroup.bookstore.service.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.data.OrderRepository;
import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;
import com.fdmgroup.bookstore.service.ItemNotFoundException;
import com.fdmgroup.bookstore.service.OrderingService;
import com.fdmgroup.bookstore.service.UserNotFoundException;

class OrderingServiceTest {

	OrderingService orderingservice;
	
	static int num_orderId;
	@Mock
	OrderRepository orderRepository;
	
	@Mock
	BookRepository bookRepoMock;
	
	@Mock
	UserRepository userRepoMock;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	@BeforeEach
	void setUp() throws Exception {
		orderRepository = mock(OrderRepository.class);
		bookRepoMock = mock(BookRepository.class);
		userRepoMock = mock(UserRepository.class);
		orderingservice = new OrderingService(orderRepository,bookRepoMock,userRepoMock);
	}
	
	@Test
	void testPlaceOrder() throws UserNotFoundException {

		Book book = new Book(1,10.0,"newBook","me", BookGenre.CRIME);
		
		User user = new User(1,"varsha","panchal","username","password","@gmail.com",null);
		
		OrderingService.setNum_orderId(1);
		Order order = new Order(1, book, user.getUserId(), LocalDateTime.now());
		

		when(bookRepoMock.findById(book.getItemld())).thenReturn(book);
		when(userRepoMock.findById(user.getUserId())).thenReturn(user);
		
		assertEquals(order.getOrderId(), orderingservice.placeOrder(book, user).getOrderId());
	}
	
	
	@Test
	void testPlaceOrders() throws ItemNotFoundException, UserNotFoundException {

		Book book1 = new Book(1,10.0,"newBook","me", BookGenre.CRIME);
		Book book2 = new Book(1,10.0,"newBook","me", BookGenre.CRIME);
		
		ArrayList<Book> books = new ArrayList<Book>();
		
		books.add(book1);
		books.add(book2);
		
		User user = new User(1,"varsha","panchal","username","password","@gmail.com",null);
		
		when(bookRepoMock.findById(book1.getItemld())).thenReturn(book1);
		when(bookRepoMock.findById(book2.getItemld())).thenReturn(book2);
		when(userRepoMock.findById(user.getUserId())).thenReturn(user);
		
		assertEquals(2, orderingservice.placeOrders(books, user).size());
		
	}

	@Test
	void testgetOrdersForUser() {
		
//		Book book = new Book(1,10.0,"newBook","me", BookGenre.CRIME);
		ArrayList<Order> orders = new ArrayList<Order>();
		Order order = new Order(0, null, 0, null);
		orders.add(order);
		User user = new User(1,"varsha","panchal","username","password","@gmail.com", orders);
		
		
		when(orderRepository.findById(user.getUserId())).thenReturn(order);
		assertEquals(List.of(order),orderingservice.getOrdersForUser(user));
		
	}	
	@Test
	void testgetOrdersForBook() {
		
		Book book = new Book(1,10.0,"newBook","me", BookGenre.CRIME);
		Order order = new Order(0, book, 0, null);
		
		ArrayList<Order> orders = new ArrayList<Order>();
		
		orders.add(order);
		User user = new User(1,"varsha","panchal","username","password","@gmail.com", orders);
		List<Object> ordersAsObject = (List)orders;
		
		when(orderRepository.findAll()).thenReturn(ordersAsObject);
		assertEquals(List.of(order),orderingservice.getOrdersForBook(book));
		
	}

}
