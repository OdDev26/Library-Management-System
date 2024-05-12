package com.project.Library.Management.serviceImpl;
import com.project.Library.Management.dto.BookDto;
import com.project.Library.Management.entity.Book;
import com.project.Library.Management.exception.CustomException;
import com.project.Library.Management.repository.BookRepository;
import com.project.Library.Management.request.BookRequest;
import com.project.Library.Management.response.GenericResponse;
import com.project.Library.Management.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public ResponseEntity<GenericResponse<?>> findAll() {
        try {
            List<BookDto> bookList = bookRepository.findAllBooks();
            if(bookList.isEmpty()){
                return new ResponseEntity<>(new GenericResponse<>(404,"No book found",null),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new GenericResponse<>(200,"Books returned successfully",bookList),HttpStatus.OK);

        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<GenericResponse<?>> findById(Long id) {
        try {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if(optionalBook.isEmpty()){
                return new ResponseEntity<>(new GenericResponse<>(404,"No book found",null),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new GenericResponse<>(200,"Book returned successfully",optionalBook.get()),HttpStatus.OK);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<GenericResponse<?>> create(BookRequest bookRequest) {
        try {
            Book book = new Book(bookRequest.getTitle().trim(), bookRequest.getAuthor().trim(),bookRequest.getPublicationYear().trim(),
                    bookRequest.getIsbn().trim());
            book = bookRepository.save(book);
            return new ResponseEntity<>(new GenericResponse<>(201,"Book created successfully",book),HttpStatus.OK);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<GenericResponse<?>> update(BookRequest bookRequest, Long id) {
        try {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if(optionalBook.isEmpty()){
                return new ResponseEntity<>(new GenericResponse<>(404,"Book not found",null),HttpStatus.NOT_FOUND);

            }
            Book book = new Book(id,bookRequest.getTitle().trim(), bookRequest.getAuthor().trim(),bookRequest.getPublicationYear().trim(),
                    bookRequest.getIsbn().trim());
            book = bookRepository.save(book);
            return new ResponseEntity<>(new GenericResponse<>(200,"Book updated successfully",book),HttpStatus.OK);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<GenericResponse<?>> deleteById(Long id) {
        try{
            Optional<Book> optionalBook = bookRepository.findById(id);
            if(optionalBook.isEmpty()){
                return new ResponseEntity<>(new GenericResponse<>(404,"Book not found",null),HttpStatus.OK);

            }
            bookRepository.deleteById(id);
            return new ResponseEntity<>(new GenericResponse<>(200,"Book deleted successfully",null),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}
