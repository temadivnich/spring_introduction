package it.discovery.config;

import it.discovery.log.Logger;
import it.discovery.repository.BookRepository;
import it.discovery.repository.XMLBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

@Configuration
@ComponentScan("it.discovery")
@PropertySource("application.properties")
@EnableAsync
@Lazy
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
    public BookService bookService(BookRepository bookRepository,
                                   List<Logger> loggers) {
        return new BookServiceImpl(bookRepository, loggers);
    }

    @Bean
    public Logger fileLogger() {
        return message -> System.out.println("Logged to file:" + message);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public Logger inMemoryLogger() {
        return message -> System.out.println("Logged to memory:" +
                message);
    }



}
