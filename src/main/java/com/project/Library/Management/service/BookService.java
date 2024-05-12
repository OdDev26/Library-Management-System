package com.project.Library.Management.service;



import com.project.Library.Management.entity.Book;
import com.project.Library.Management.request.BookRequest;
import com.project.Library.Management.response.GenericResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    ResponseEntity<GenericResponse<?>> findAll();
    ResponseEntity<GenericResponse<?>> findById(Long id);

    ResponseEntity<GenericResponse<?>> create(BookRequest bookRequest);

    ResponseEntity<GenericResponse<?>> update(BookRequest bookRequest,Long id);

    ResponseEntity<GenericResponse<?>> deleteById(Long id);
}
