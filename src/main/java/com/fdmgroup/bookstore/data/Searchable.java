package com.fdmgroup.bookstore.data;

import java.util.List;

public interface Searchable {
	
	public <T> T findById(int id);
	 public <T> List<T> findAll();

}
