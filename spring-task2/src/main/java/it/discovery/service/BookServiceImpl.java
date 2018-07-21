package it.discovery.service;

import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
        System.out.println("Using repository " + repository.getClass().getSimpleName());
    }

    @Override
    public void saveBook(Book book) {
        repository.saveBook(book);
    }

    @Override
    public Book findBookById(int id) {
        return repository.findBookById(id);
    }

    @Override
    public List<Book> findBooks() {
        return repository.findBooks();
    }
}
