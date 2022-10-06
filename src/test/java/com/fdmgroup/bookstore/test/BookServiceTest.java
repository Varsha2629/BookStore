package com.fdmgroup.bookstore.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import com.fdmgroup.bookstore.service.BookService;
import com.fdmgroup.bookstore.service.ItemNotFoundException;

class BookServiceTest {
	
	BookService bookservice;
	List<Object> books_list;
	Book book1;
		
	@Mock
	BookRepository bookRepoMock;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		books_list = new ArrayList<>();
		
		bookRepoMock = mock(BookRepository.class);
		bookservice = new BookService(bookRepoMock);
		
		book1 = new Book(1,10.0,"newBook","me", BookGenre.CRIME);
		
		books_list.add(book1);
				
	}


	@Test
	public void testgetAllBooks() {
		
		when(bookRepoMock.findAll()).thenReturn(books_list);
		assertEquals(books_list, bookservice.getAllBooks());
			
	}
	
	@Test
	void testgetBooksOfGenre()  {
				
		assertEquals(List.of(),bookservice.getBooksOfGenre(BookGenre.CRIME));
	}
	
	@Test
	void testsearchBooksByTitle() {

		
		assertEquals(List.of(),bookservice.searchBooksByTitle(book1.getTitle()));	}
	
	@Test
	void searchBooksByAuthorName() {
		
				
		assertEquals(List.of(),bookservice.searchBooksByAuthoyName(book1.getAuthor()));
	}
	
	@Test
	void testfindBYId()  {

				
		assertThrows(ItemNotFoundException.class,() ->{
			Book byId = bookservice.findById(book1.getItemld());
		});

	}
	
	


}
