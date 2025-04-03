package org.pmlibrary.bookspring.repository;

import org.pmlibrary.bookspring.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity readBookByTitleIgnoreCase(String title);
}
