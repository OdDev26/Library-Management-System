package com.project.Library.Management.repository;

import com.project.Library.Management.dto.BookDto;
import com.project.Library.Management.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query(value = "select id, author, isbn, publication_year as publicationYear, title from books",nativeQuery = true)
    List<BookDto> findAllBooks();

}
