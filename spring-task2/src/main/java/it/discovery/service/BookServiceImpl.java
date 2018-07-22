package it.discovery.service;

import it.discovery.log.Logger;
import it.discovery.model.Book;
import it.discovery.repository.BookRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    private final List<Logger> loggers;

    public BookServiceImpl(BookRepository repository, List<Logger> loggers) {
        this.repository = repository;
        this.loggers = loggers;
        System.out.println("Using repository " + repository.getClass().getSimpleName());
    }

    @Override
    public void saveBook(Book book) {
        repository.saveBook(book);

        loggers.forEach(logger -> logger.log("Book saved"));
    }

    @Override
    public Book findBookById(int id) {
        return repository.findBookById(id);
    }

    @Override
    public List<Book> findBooks() {
        Future<List<Book>> future = repository.findBooks();

        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
