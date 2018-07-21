package it.discovery.loader;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;

public class BootstrapLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        DBBookRepository bookRepository = new DBBookRepository();
        bookRepository.setDb("library");
        bookRepository.setServer("localhost");
        BookService service = new BookServiceImpl(bookRepository);
        Book book = new Book();
        book.setName("Introduction into Spring");
        book.setPages(100);
        book.setYear(2016);
        service.saveBook(book);

        List<Book> books = service.findBooks();
        System.out.println(books);

//        Class<?> clz = Class.forName("it.discovery.model.Book");
//        Object obj = clz.getDeclaredConstructors()[0].newInstance();
//        System.out.println(obj.getClass());
    }

}
