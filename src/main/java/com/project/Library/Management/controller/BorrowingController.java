package com.project.Library.Management.controller;

import com.project.Library.Management.request.BorrowingRequest;
import com.project.Library.Management.service.BorrowingService;
import com.project.Library.Management.validation.FieldErrorHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class BorrowingController {
    private final BorrowingService borrowingService;
    private final FieldErrorHandler fieldErrorHandler;
    @Autowired
    public BorrowingController(BorrowingService borrowingService, FieldErrorHandler fieldErrorHandler) {
        this.borrowingService = borrowingService;
        this.fieldErrorHandler = fieldErrorHandler;
    }

    @PostMapping("borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<?> borrowBook(@Valid @RequestBody BorrowingRequest borrowingRequest, BindingResult bindingResult, @PathVariable Long bookId,
                                        @PathVariable Long patronId){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(fieldErrorHandler.errorMessageMap(bindingResult), HttpStatus.BAD_REQUEST);
        }
        return borrowingService.borrowBook(borrowingRequest,bookId,patronId);

    }
    @PostMapping("return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId,
                                        @PathVariable Long patronId){
        return borrowingService.returnBook(bookId,patronId);

    }
}
