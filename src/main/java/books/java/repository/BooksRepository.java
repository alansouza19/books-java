package books.java.repository;

import books.java.models.BooksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<BooksModel, Long> {

    BooksModel findById(long id);
}
