package it.discovery.service;

import it.discovery.event.EventBus;
import it.discovery.event.LogEvent;
import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    private final ApplicationEventPublisher eventPublisher;

    public BookServiceImpl(BookRepository repository,
                           ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        System.out.println("Using repository " + repository.getClass().getSimpleName());
    }

    @Override
    public void saveBook(Book book) {
        repository.saveBook(book);

        eventPublisher.publishEvent(new LogEvent(this, "Book saved"));
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
