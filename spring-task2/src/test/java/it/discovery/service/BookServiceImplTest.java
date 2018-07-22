package it.discovery.service;

import it.discovery.config.AppConfiguration;
import it.discovery.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(AppConfiguration.class)
@ActiveProfiles({"dev", "test"})
@TestPropertySource(properties = {"repository.type=xml"})
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void saveBook_validBook_savedSuccessfully() {
        Book book = new Book();
        book.setName("Introduction into Spring");
        book.setPages(100);
        book.setYear(2016);
        bookService.saveBook(book);

        Book newBook = bookService.findBookById(book.getId());
        assertNotNull(newBook);
        assertEquals(2016, newBook.getYear());
    }
}
