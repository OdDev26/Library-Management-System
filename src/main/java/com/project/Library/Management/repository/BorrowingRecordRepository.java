package com.project.Library.Management.repository;

import com.project.Library.Management.entity.Book;
import com.project.Library.Management.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {
    @Query(value = "SELECT * from borrowing_records where book_id = ?1 and patron_id = ?2",nativeQuery = true)
    BorrowingRecord getBorrowingRecord(Long bookId, Long patronId);
}
