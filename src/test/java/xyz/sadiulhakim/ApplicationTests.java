package xyz.sadiulhakim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.sadiulhakim.repository.BookRepository;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void makeSureBooksAreCreated() {
        long books = bookRepository.count();
        Assertions.assertTrue(books > 0);
    }
}
