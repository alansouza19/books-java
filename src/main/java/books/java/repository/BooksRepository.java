package books.java.repository;

import books.java.models.BooksModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<BooksModel, Long> {
}
