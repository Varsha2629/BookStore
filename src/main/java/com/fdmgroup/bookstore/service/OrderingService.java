package com.fdmgroup.bookstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.data.OrderRepository;
import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;

public class OrderingService {
	
	private OrderRepository orderRepository;
	private BookRepository bookRepository;
	private UserRepository userRepository;
	static int num_orderId;	

	public OrderRepository<Order> getOrderRepository() {
		return orderRepository;
	}

	public void setOrderRepository(OrderRepository<Order> orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	
	public OrderingService(OrderRepository orderRepository, BookRepository bookRepository,UserRepository userRepository ){
		this.orderRepository = orderRepository;
		this.bookRepository = bookRepository;	
		this.userRepository = userRepository;
	}
	
	
	
	public static int getNum_orderId() {
		return num_orderId;
	}

	public static void setNum_orderId(int num_orderId) {
		OrderingService.num_orderId = num_orderId;
	}

	public Order placeOrder(Book book,User customer){
		
		if((bookRepository.findById(book.getItemld()) != null) && (userRepository.findById(customer.getUserId()) !=null)){
			Order order = new Order(num_orderId, book, customer.getUserId(), LocalDateTime.now());
			num_orderId++;
			orderRepository.save(order);
			
			return order;
		}
		else {
			return null;
		}
	}
	
	public List<Order> placeOrders(List<Book> books, User customer) throws ItemNotFoundException, UserNotFoundException{
		
		AuthenticationService authenticationService = new AuthenticationService(userRepository);
		BookService bookService = new BookService(bookRepository);
		
		ArrayList<Order> orders = new ArrayList<Order>();
		
		authenticationService.findById(customer.getUserId());
		
		for(Book book : books) {
			bookService.findById(book.getItemld());
			Order order = new Order(num_orderId, book, customer.getUserId(), LocalDateTime.now());
			num_orderId++;
			orderRepository.save(order);
			orders.add(order);
		} 
		return orders;
	}
	
	public List<Order> getOrdersForUser(User user){
		Order order = orderRepository.findById(user.getUserId());
		return user.getOrders();

	}
	
	public List<Order> getOrdersForBook(Book book){
		Order bookOrderd = orderRepository.findById(book.getItemld());
		List<Order> allOrders = orderRepository.findAll();
		List<Order> returnOrders = new ArrayList<>();
		
		for (Order o : allOrders) {
			if(o.getBookOrdered().getItemld() == book.getItemld()) {
				returnOrders.add(o); 
			}
		}
		
		return returnOrders;
		
	}	
	
	
}
