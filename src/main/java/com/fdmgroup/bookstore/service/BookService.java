package com.fdmgroup.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;

public class BookService {
	private BookRepository bookRepository;	
	
	ArrayList<Book> books = new ArrayList<Book>();
		
	public BookService(BookRepository bookRepository) {
		
		this.bookRepository = bookRepository;
	}
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
		
	}
	
	public List<Book> getBooksOfGenre(BookGenre bookGenre) {
		
		for(Book book: books) {
			if(book.getBookGenre().equals(bookGenre)) {
				books.add(book);
			}			
		}
		return books;
	}
	
	public List<Book> searchBooksByTitle(String title){
		
		for(Book book: books) {
			if(book.getTitle().equals(title)) {
				 books.add(book);
				 
			}					}
		return books;
		
	}
	
	public List<Book> searchBooksByAuthoyName(String bookAuthorNameToSearch){
		
		for(Book book: books) {
			if(book.getAuthor().contains(bookAuthorNameToSearch)){
				 books.add(book);				 
			}			
		}
		return books;
	
	}
	
	public Book findById(int bId) throws ItemNotFoundException {
		
		if(bookRepository.findById(bId) != null) {
			return (Book) bookRepository.findById(bId);

		}else {
			
			throw new ItemNotFoundException("Message");	
		}
		
	}
	

}
