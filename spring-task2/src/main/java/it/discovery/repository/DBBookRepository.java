package it.discovery.repository;

import it.discovery.config.ConditionalRepositoryType;
import it.discovery.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles database-related book operations
 * 
 * @author morenets
 *
 */
@Named
@Profile("test")
@ConditionalRepositoryType("db")
public class DBBookRepository implements BookRepository {
	private final Map<Integer, Book> books = new HashMap<>();

	private int counter = 0;

	private final String server;

	private final String db;

	public DBBookRepository(@Value("${repository.db.server}") String server, @Value("${repository.db.db}") String db) {
		this.server = server;
		this.db = db;
	}

	@PostConstruct
	public void init() {
		System.out.println("Started db repository with server:" + server + " and database: " + db );
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Shutting down repository ... ");
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
	public List<Book> findBooks() {
		return new ArrayList<>(books.values());
	}

	public String getServer() {
		return server;
	}

	public String getDb() {
		return db;
	}
}
