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
import org.springframework.context.annotation.PropertySource;

import java.lang.annotation.*;

@Configuration
@ComponentScan("it.discovery")
@PropertySource("application.properties")
public class AppConfiguration {

//    @Qualifier("db")
//    @Bean
//    public BookRepository dbRepository() {
//        return new DBBookRepository();
//    }

    @XmlQualifier
    @Bean
    @ConditionalRepositoryType("xml")
    public BookRepository xmlRepository() {
        return new XMLBookRepository();
    }

    @Bean
    public BookService bookService(BookRepository bookRepository) {
        return new BookServiceImpl(bookRepository);
    }
}
