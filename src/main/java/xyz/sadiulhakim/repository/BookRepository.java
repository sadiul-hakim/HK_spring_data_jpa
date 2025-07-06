package xyz.sadiulhakim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.sadiulhakim.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
