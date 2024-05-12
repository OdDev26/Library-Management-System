package com.project.Library.Management.serviceImpl;

import com.project.Library.Management.entity.Book;
import com.project.Library.Management.entity.BorrowingRecord;
import com.project.Library.Management.entity.Patron;
import com.project.Library.Management.exception.CustomException;
import com.project.Library.Management.repository.BookRepository;
import com.project.Library.Management.repository.BorrowingRecordRepository;
import com.project.Library.Management.repository.PatronRepository;
import com.project.Library.Management.request.BookRequest;
import com.project.Library.Management.request.BorrowingRequest;
import com.project.Library.Management.response.GenericResponse;
import com.project.Library.Management.service.BookService;
import com.project.Library.Management.service.BorrowingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowingServiceImpl implements BorrowingService {
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    @Autowired
    public BorrowingServiceImpl(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<GenericResponse<?>> borrowBook(BorrowingRequest borrowingRequest, Long bookId, Long patronId) {
        try{
            // Book must exist
            Book book = bookRepository.findById(bookId).orElseThrow(()->new CustomException("Book with id not found"));
            // Patron must exist
            Patron patron = patronRepository.findById(patronId).orElseThrow(()->new CustomException("Patron not found"));
            patron.addBook(book);
            BorrowingRecord borrowingRecord = new BorrowingRecord(borrowingRequest.getReturnDate());
            borrowingRecord.setPatron(patron);
            borrowingRecord.setBook(book);
            // save patron
            borrowingRecordRepository.save(borrowingRecord);
            patronRepository.save(patron);
            return new ResponseEntity<>(new GenericResponse<>(200,"Book borrowed successfully",null),HttpStatus.OK);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> returnBook(Long bookId, Long patronId) {
        try {
            // Get the BorrowingRecord
            BorrowingRecord borrowingRecord = borrowingRecordRepository.getBorrowingRecord(bookId, patronId);
            if (borrowingRecord == null) throw new CustomException("Borrowing record not found");
            // Update a column isReturned to true
            borrowingRecord.setReturned(true);
            borrowingRecordRepository.save(borrowingRecord);
            return new ResponseEntity<>(new GenericResponse<>(200, "Return logged successfully", null), HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
