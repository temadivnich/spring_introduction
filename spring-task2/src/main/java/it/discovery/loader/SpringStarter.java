package it.discovery.loader;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import it.discovery.repository.BookRepository;
import it.discovery.service.BookServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.discovery.model.Book;
import it.discovery.service.BookService;
import org.springframework.stereotype.Service;

public class SpringStarter {
	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context =
					 new AnnotationConfigApplicationContext("it.discovery")) {
			
			BookService service = context.getBean(BookService.class);
			
			Book book = new Book();
			book.setName("Introduction into Spring");
			book.setPages(100);
			book.setYear(2016);
			service.saveBook(book);

			List<Book> books = service.findBooks();
			System.out.println(books);
			System.out.println("Total bean count: " + context.getBeanDefinitionCount());
			System.out.println("Beans: " + Arrays.asList(context.getBeanDefinitionNames()));
			System.out.println("BookRepository beans: " + context.getBeansOfType(BookRepository.class));

//			Class<BookServiceImpl> clz = BookServiceImpl.class;
//			Service service1 = clz.getDeclaredAnnotation(Service.class);
//			System.out.println(service1);
		}
	}
}
