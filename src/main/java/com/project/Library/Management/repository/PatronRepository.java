package com.project.Library.Management.repository;

import com.project.Library.Management.dto.PatronDto;
import com.project.Library.Management.entity.Book;
import com.project.Library.Management.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatronRepository extends JpaRepository<Patron,Long> {
    @Query(value = "SELECT id,email,first_name as firstName, last_name as lastName, phone, username FROM patrons",nativeQuery = true)
    List<PatronDto> findAllPatrons();
    @Query(value = "SELECT id,email,first_name as firstName, last_name as lastName, phone, username FROM patrons where id = ?",nativeQuery = true)
    PatronDto findPatron(Long id);
}
