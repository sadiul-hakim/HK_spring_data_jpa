package xyz.sadiulhakim.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.sadiulhakim.domain.Book;
import xyz.sadiulhakim.repository.BookRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();

        var javaProgramming = new Book("Java Programming", "A N M Bazlur Rahman", "1234");
        var javaThreadProgramming = new Book("Java Thread Programming", "A N M Bazlur Rahman", "12345");
        Book savedJavaBook = bookRepository.save(javaProgramming);
        Book savedJavaThreadBook = bookRepository.save(javaThreadProgramming);

        System.out.printf("Id : %s\n", savedJavaBook.getId());
        System.out.printf("Id : %s\n", savedJavaThreadBook.getId());
    }
}
