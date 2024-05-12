package com.project.Library.Management.service;

import com.project.Library.Management.request.BorrowingRequest;
import org.springframework.http.ResponseEntity;

public interface BorrowingService {
    ResponseEntity<?> borrowBook(BorrowingRequest borrowingRequest, Long bookId, Long patronId);

    ResponseEntity<?> returnBook(Long bookId, Long patronId);
}
