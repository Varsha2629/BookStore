package com.fdmgroup.bookstore.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import com.fdmgroup.bookstore.service.BookService;
import com.fdmgroup.bookstore.service.ItemNotFoundException;

class BookServiceTest {

	BookRepository bookRepository;
	BookService bookService;
	
	
	@BeforeEach
	public void setup() {
		bookRepository = mock(BookRepository.class);
		bookService =new  BookService(bookRepository);
	}
	 
	@Test
	void testFindById() throws ItemNotFoundException {
		Book book= new Book(1,7,"Rockstar","Keshav",BookGenre.ACTION);
		when(this.bookRepository.findById(1)).thenReturn(book);
		assertEquals(this.bookService.findById(1),book);
	}
	
	 
	@Test
	void testFindById_ItemNotFoundException() throws ItemNotFoundException {
		Exception exception = assertThrows(ItemNotFoundException.class, () -> {
			this.bookService.findById(1);
	    }); 
	
	    String expectedMessage = "Item not Found";
	    String actualMessage = exception.getMessage();
	
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	
	
	@Test
	void testGetAllBooks() throws ItemNotFoundException {
		
		List<Object> books=new ArrayList<>();
		books.add(new Book(1,7,"Rockstar","Keshav",BookGenre.ACTION));
		books.add(new Book(2,10,"Peter","Chang",BookGenre.ACTION));
		when(this.bookRepository.findAll()).thenReturn(books);
		List<Book> actual =this.bookService.getAllBooks();
		assertEquals(books,actual);
	}

	@Test
	void testGetBooksOfGenre() throws ItemNotFoundException {
		List<Object> books=new ArrayList<>();
		books.add(new Book(1,7,"Rockstar","Keshav",BookGenre.ACTION));
		books.add(new Book(2,10,"Peter","Chang",BookGenre.ACTION));
		when(this.bookRepository.findAll()).thenReturn(books);
		List<Book> actual=this.bookService.getBooksOfGenre(BookGenre.ACTION);
		assertEquals(books, actual);
	}
	
	@Test
	void testSearchBooksByTitle() {
		List<Object> books=new ArrayList<>();
		books.add(new Book(1,7,"Rockstar","Keshav",BookGenre.ACTION));
		books.add(new Book(2,10,"Rockstar","Chang",BookGenre.ACTION));
		when(this.bookRepository.findAll()).thenReturn(books);
		List<Book> actual=this.bookService.searchBooksByTitle("Rockstar");
		assertEquals(books, actual);
	}
	
	
	
	
	@Test
	void testSearchBooksByAuthorName() {
		List<Object> books=new ArrayList<>();
		books.add(new Book(1,7,"Rockstar","Keshav Ganesh",BookGenre.ACTION));
		books.add(new Book(2,10,"Rockstar","keshav",BookGenre.ACTION));
		when(this.bookRepository.findAll()).thenReturn(books);
		List<Book> actual=this.bookService.searchBooksByAuthorName("KESHAV");
		assertEquals(books, actual);
	}

	
	@Test
	void testfordeleteBookByTitle() {
		List<Object> books=new ArrayList<>();
		books.add(new Book(1,7,"Rockstar","Keshav Ganesh",BookGenre.ACTION));
		books.add(new Book(2,10,"Rockstar","keshav",BookGenre.ACTION));
		when(this.bookRepository.findAll()).thenReturn(books);
		verify(this.bookRepository,times(1)).delete(new Book(1,7,"Rockstar","Keshav Ganesh",BookGenre.ACTION) );
	}
}
