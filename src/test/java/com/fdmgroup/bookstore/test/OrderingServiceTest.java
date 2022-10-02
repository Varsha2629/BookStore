package com.fdmgroup.bookstore.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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


	UserRepository userRepository;
	OrderRepository orderRepository;
	BookRepository bookRepository;
	
	OrderingService orderingService;
	 
	@BeforeEach
	public void setup() {
		userRepository = mock(UserRepository.class);
		orderRepository =mock(OrderRepository.class);
		bookRepository =mock(BookRepository.class);
		orderingService =new  OrderingService(orderRepository,bookRepository,userRepository);
		
	}
	@Test
	void testPlaceOrder() throws UserNotFoundException, ItemNotFoundException {
		Book book= new Book(1,7,"Rockstar","Keshav",BookGenre.ACTION);
		List<Order> orders = new ArrayList<Order>();
		Order order=new Order(1,book,1,LocalDateTime.now());
		orders.add(order);
		User user=new User(1,"keshav","ganesh","keshavg","xyz","keshavg@gmail.com",orders);
		
		when(bookRepository.findById(book.getItemId())).thenReturn(book);
		when(userRepository.findById(user.getUserId())).thenReturn(user);
		Order returnedorder=orderingService.placeOrder(book, user);
		verify(bookRepository, times(1)).findById(book.getItemId());
		verify(userRepository, times(1)).findById(user.getUserId());

		
	}
 
	@Test
	void testPlaceOrder_UserNotFound() throws UserNotFoundException, ItemNotFoundException {
		
		
		Exception exception = assertThrows(UserNotFoundException.class, () -> {
			Book book= new Book(1,7,"Rockstar","Keshav",BookGenre.ACTION);
			List<Order> orders = new ArrayList<Order>();
			Order order=new Order(1,book,1,LocalDateTime.now());
			orders.add(order);
			User user=new User(1,"keshav","ganesh","keshavg","xyz","keshavg@gmail.com",orders);
			when(bookRepository.findById(book.getItemId())).thenReturn(book);
			when(userRepository.findById(user.getUserId())).thenReturn(null);
			Order returnedorder=orderingService.placeOrder(book, user);
		}); 
	 
	    String expectedMessage = "User not found";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));

		
	}
	@Test
	void testPlaceOrder_ItemNotFound() throws UserNotFoundException, ItemNotFoundException {
		
		
		Exception exception = assertThrows(ItemNotFoundException.class, () -> {
			Book book= new Book(1,7,"Rockstar","Keshav",BookGenre.ACTION);
			List<Order> orders = new ArrayList<Order>();
			Order order=new Order(1,book,1,LocalDateTime.now());
			orders.add(order);
			User user=new User(1,"keshav","ganesh","keshavg","xyz","keshavg@gmail.com",orders);
			when(bookRepository.findById(book.getItemId())).thenReturn(null);
			Order returnedorder=orderingService.placeOrder(book, user);
		}); 
	 
	    String expectedMessage = "Item not found";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));

		
	}
	@Test
	void testPlaceOrders() throws ItemNotFoundException, UserNotFoundException {	
		
	Book book1= new Book(1,7,"Rockstar","Keshav",BookGenre.ACTION);
	Book book2= new Book(2,7,"Rockstar","Keshav",BookGenre.ACTION);
	List<Book> books = new ArrayList<Book>();
	books.add(book1);
	books.add(book2);
	
	
	
	List<Order> orders = new ArrayList<Order>();
	Order order=new Order(1,book1,1,LocalDateTime.now());
	orders.add(order);
	User user=new User(1,"keshav","ganesh","keshavg","xyz","keshavg@gmail.com",orders);
	when(bookRepository.findById(book1.getItemId())).thenReturn(book1);
	when(bookRepository.findById(book2.getItemId())).thenReturn(book2);
	when(userRepository.findById(user.getUserId())).thenReturn(user);
	
	List<Order> returnedorder=orderingService.placeOrders(books, user);
	verify(bookRepository, times(1)).findById(book1.getItemId());
	verify(bookRepository, times(1)).findById(book2.getItemId());
	verify(userRepository, times(1)).findById(user.getUserId());

	
	}


	@Test
	void testPlaceOrders_UserNotFound() throws UserNotFoundException, ItemNotFoundException {
		
		
		Exception exception = assertThrows(UserNotFoundException.class, () -> {
			Book book1= new Book(1,7,"Rockstar","Keshav",BookGenre.ACTION);
			Book book2= new Book(2,7,"Rockstar","Keshav",BookGenre.ACTION);
			List<Book> books = new ArrayList<Book>();
			books.add(book1);
			books.add(book2);
			
			List<Order> orders = new ArrayList<Order>();
			Order order=new Order(1,book1,1,LocalDateTime.now());
			orders.add(order);
			User user=new User(1,"keshav","ganesh","keshavg","xyz","keshavg@gmail.com",orders);
			when(userRepository.findById(user.getUserId())).thenReturn(null);
			List<Order> returnedorders=orderingService.placeOrders(books, user);
		}); 
	 
	    String expectedMessage = "User not found";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));

		
	}
	@Test
	void testPlaceOrders_ItemNotFound() throws UserNotFoundException, ItemNotFoundException {
		
		
		Exception exception = assertThrows(ItemNotFoundException.class, () -> {
			Book book1= new Book(1,7,"Rockstar","Keshav",BookGenre.ACTION);
			Book book2= new Book(2,7,"Rockstar","Keshav",BookGenre.ACTION);
			List<Book> books = new ArrayList<Book>();
			books.add(book1);
			books.add(book2);
			List<Order> orders = new ArrayList<Order>();
			Order order=new Order(1,book1,1,LocalDateTime.now());
			orders.add(order);
			User user=new User(1,"keshav","ganesh","keshavg","xyz","keshavg@gmail.com",orders);
			when(bookRepository.findById(book1.getItemId())).thenReturn(null);
			when(bookRepository.findById(book2.getItemId())).thenReturn(null);
			when(userRepository.findById(user.getUserId())).thenReturn(user);
			List<Order> returnedorders=orderingService.placeOrders(books, user);
		}); 
	 
	    String expectedMessage = "Item not found";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));

		
	}
	@Test
	void testGetOrdersForUser() {
		Book book= new Book(1,7,"Rockstar","Keshav",BookGenre.ACTION);
		List<Order> orders = new ArrayList<Order>();
		Order order=new Order(1,book,1,LocalDateTime.now());
		orders.add(order);
		User user=new User(1,"keshav","ganesh","keshavg","xyz","keshavg@gmail.com",orders);
		List<Object> ordersAsObject = (List)orders;
		when(orderRepository.findAll()).thenReturn(ordersAsObject);
		assertEquals(ordersAsObject, orderingService.getOrdersForUser(user));
	}

		@Test
		void testGetOrdersForBook() {
		Book book= new Book(1,7,"Rockstar","Keshav",BookGenre.ACTION);
		List<Order> orders = new ArrayList<Order>();
		Order order=new Order(1,book,1,LocalDateTime.now());
		orders.add(order);
		User user=new User(1,"keshav","ganesh","keshavg","xyz","keshavg@gmail.com",orders);
		List<Object> ordersAsObject = (List)orders;
		when(orderRepository.findAll()).thenReturn(ordersAsObject);
		assertEquals(ordersAsObject, orderingService.getOrdersForBook(book));
		}

}
