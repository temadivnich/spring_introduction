package it.discovery.loader;

import java.util.Arrays;
import java.util.List;

import it.discovery.repository.BookRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.discovery.model.Book;
import it.discovery.service.BookService;

public class SpringStarter {
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml")) {
			
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
		}

	}

}
