package org.pmlibrary.bookspring.repository;

import org.pmlibrary.bookspring.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    AuthorEntity readAuthorByFirstNameOrLastNameIgnoreCase(String first, String last);
}
