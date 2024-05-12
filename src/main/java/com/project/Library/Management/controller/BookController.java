package com.project.Library.Management.controller;

import com.project.Library.Management.validation.FieldErrorHandler;
import com.project.Library.Management.request.BookRequest;
import com.project.Library.Management.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    private final BookService bookService;
    private final FieldErrorHandler fieldErrorHandler;
    @Autowired
    public BookController(BookService bookService,FieldErrorHandler fieldErrorHandler) {
        this.bookService = bookService;
        this.fieldErrorHandler = fieldErrorHandler;
    }
    @GetMapping("/books")
    public ResponseEntity<?> getBooks(){
        return bookService.findAll();
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id){
        return bookService.findById(id);
    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookRequest bookRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(fieldErrorHandler.errorMessageMap(bindingResult),HttpStatus.BAD_REQUEST);
        }
        return bookService.create(bookRequest);

    }
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookRequest bookRequest, @PathVariable Long id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(fieldErrorHandler.errorMessageMap(bindingResult),HttpStatus.BAD_REQUEST);
        }
        return bookService.update(bookRequest,id);

    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return bookService.deleteById(id);
    }
}
