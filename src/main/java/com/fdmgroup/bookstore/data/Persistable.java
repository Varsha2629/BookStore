package com.fdmgroup.bookstore.data;

public interface Persistable {
	
	public <T> T save(T t);
	
	
}
