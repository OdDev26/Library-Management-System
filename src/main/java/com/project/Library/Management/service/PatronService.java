package com.project.Library.Management.service;

import com.project.Library.Management.request.CreateUserRequest;
import com.project.Library.Management.request.UpdateUserRequest;
import org.springframework.http.ResponseEntity;

public interface PatronService {
    public ResponseEntity<?> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> create(CreateUserRequest bookRequest);

    public ResponseEntity<?> update(UpdateUserRequest bookRequest, Long id);

    public ResponseEntity<?> deleteById(Long id);
}
