package it.discovery.repository;

import it.discovery.model.Book;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Handles database-related book operations
 * 
 * @author morenets
 *
 */
@Profile("dev")
public class XMLBookRepository implements BookRepository {
	private final Map<Integer, Book> books = new HashMap<>();

	private int counter = 0;

	private String path;

	public void init() {
		System.out.println("Started xml repository with file path:" + path );
	}

	public void destroy() {
		System.out.println("Shutting down xml repository ... ");
	}


	public XMLBookRepository() {
		System.out.println("creating XML repository");
	}

	@Override
	public void saveBook(Book book) {
		if (book.getId() == 0) {
			counter++;
			book.setId(counter);
		}
		
		books.put(book.getId(), book);

		System.out.println("Saved book " + book);
	}
	
	@Override
	public Book findBookById(int id) {
		return books.get(id);
	}

	@Override
	public Future<List<Book>> findBooks() {
		return new AsyncResult<>(new ArrayList<>(books.values()));
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
