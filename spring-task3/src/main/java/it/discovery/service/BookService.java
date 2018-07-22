package it.discovery.service;

import it.discovery.model.Book;

import java.util.List;

public interface BookService {
	public void saveBook(Book book);
	
	public Book findBookById(int id);

	public List<Book> findBooks();
}
