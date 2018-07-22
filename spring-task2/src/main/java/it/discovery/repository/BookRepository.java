package it.discovery.repository;

import it.discovery.model.Book;

import java.util.List;
import java.util.concurrent.Future;

public interface BookRepository {
    void saveBook(Book book);

    Book findBookById(int id);

    Future<List<Book>> findBooks();
}
