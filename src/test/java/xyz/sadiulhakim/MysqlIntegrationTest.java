package xyz.sadiulhakim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import xyz.sadiulhakim.repository.BookRepository;

@DataJpaTest
@ComponentScan(basePackages = {"xyz.sadiulhakim.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MysqlIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void test() {
        long countBefore = bookRepository.count();
        Assertions.assertEquals(2, countBefore);
    }
}
