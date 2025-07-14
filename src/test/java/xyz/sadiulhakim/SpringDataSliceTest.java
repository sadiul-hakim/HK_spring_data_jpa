package xyz.sadiulhakim;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;
import xyz.sadiulhakim.domain.Book;
import xyz.sadiulhakim.repository.BookRepository;

@ComponentScan(basePackages = {"xyz.sadiulhakim.bootstrap"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class SpringDataSliceTest {

    @Autowired
    private BookRepository bookRepository;

    @Commit
    @Order(1)
    @Test
    void test() {
        long countBefore = bookRepository.count();
        bookRepository.save(new Book("My Book", "1234", "Self"));
        long countAfter = bookRepository.count();

        Assertions.assertTrue(countAfter > countBefore);
    }

    @Order(2)
    @Test
    void test2() {
        long countBefore = bookRepository.count();
        Assertions.assertEquals(3, countBefore);
    }
}
