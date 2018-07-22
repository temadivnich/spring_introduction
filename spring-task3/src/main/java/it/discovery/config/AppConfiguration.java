package it.discovery.config;

import it.discovery.log.Logger;
import it.discovery.repository.BookRepository;
import it.discovery.repository.XMLBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan("it.discovery")
@EnableAsync
//@Lazy
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
                                   ApplicationEventPublisher eventPublisher) {
        return new BookServiceImpl(bookRepository, eventPublisher);
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

//    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public Logger verySlowLogger() {
        return message -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }



}
