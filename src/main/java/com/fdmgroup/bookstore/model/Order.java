package com.fdmgroup.bookstore.model;

import java.time.LocalDateTime;

public class Order {

	private int orderId;
	private Book bookOrdered;
	private int userId;
	private LocalDateTime orderDateTime;
	
	public Order(int orderId, Book bookOrdered, int user, LocalDateTime orderDateTime) {
		super();
		this.orderId = orderId;
		this.bookOrdered = bookOrdered;
		this.userId = user;
		this.orderDateTime = orderDateTime;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Book getBookOrdered() {
		return bookOrdered;
	}

	public void setBookOrdered(Book bookOrdered) {
		this.bookOrdered = bookOrdered;
	}

	public int getUser() {
		return userId;
	}

	public void setUser(int user) {
		this.userId = user;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	
	
	
	
}
