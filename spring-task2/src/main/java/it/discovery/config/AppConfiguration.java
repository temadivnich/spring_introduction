package it.discovery.config;

import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.repository.XMLBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("it.discovery")
public class AppConfiguration {

//    @Qualifier("db")
//    @Bean
//    public BookRepository dbRepository() {
//        return new DBBookRepository();
//    }

    @Qualifier("xml")
    @Bean
    public BookRepository xmlRepository() {
        return new XMLBookRepository();
    }

    @Bean
    public BookService bookService() {
        return new BookServiceImpl(xmlRepository());
    }
}
