package com.project.Library.Management.serviceImpl;

import com.project.Library.Management.dto.PatronDto;
import com.project.Library.Management.entity.*;
import com.project.Library.Management.exception.CustomException;
import com.project.Library.Management.repository.*;
import com.project.Library.Management.request.CreateUserRequest;
import com.project.Library.Management.request.UpdateUserRequest;
import com.project.Library.Management.response.GenericResponse;
import com.project.Library.Management.service.PatronService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatronServiceImpl implements PatronService {

    private final PatronRepository patronRepository;
    private final BookRepository bookRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    @Autowired
    public PatronServiceImpl(PatronRepository patronRepository, BookRepository bookRepository, BorrowingRecordRepository borrowingRecordRepository, @Qualifier("passwordEncoder") PasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.patronRepository = patronRepository;
        this.bookRepository = bookRepository;
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public ResponseEntity<?> findAll() {
        try {
            List<PatronDto> patronList = patronRepository.findAllPatrons();
            System.out.println("Patron list: "+patronList);

            if(patronList.isEmpty()){
                return new ResponseEntity<>(new GenericResponse<>(404,"No patron found",null),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new GenericResponse<>(200,"Patrons returned successfully",patronList),HttpStatus.OK);

        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        try {
            PatronDto patron = patronRepository.findPatron(id);
            if(patron == null){
                return new ResponseEntity<>(new GenericResponse<>(404,"Patron not found",null),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new GenericResponse<>(200,"Patron returned successfully",patron),HttpStatus.OK);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(CreateUserRequest createUserRequest) {
        try {
            // Save patron into users and authorities table
            User user = new User(createUserRequest.getUsername(),bCryptPasswordEncoder.encode(createUserRequest.getPassword()),true);
            Authority authority = new Authority(user.getUsername(),"ROLE_PATRON");
            userRepository.save(user);
            authorityRepository.save(authority);
            Patron patron = new Patron(createUserRequest.getFirstName().trim(), createUserRequest.getLastName().trim(),
                    createUserRequest.getEmail().trim(), createUserRequest.getPhone().trim(), createUserRequest.getUsername().trim());
            patron = patronRepository.save(patron);
            return new ResponseEntity<>(new GenericResponse<>(201,"Patron created successfully",patron), HttpStatus.OK);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(UpdateUserRequest updateUserRequest, Long id) {
        try {
            Optional<Patron> optionalPatron = patronRepository.findById(id);
            if(optionalPatron.isEmpty()){
                return new ResponseEntity<>(new GenericResponse<>(404,"Patron not found",null),HttpStatus.NOT_FOUND);

            }
            Patron patron = new Patron();
            patron = optionalPatron.get();
            patron.setId(id);
            patron.setFirstName((updateUserRequest.getFirstName() == null || updateUserRequest.getFirstName().isEmpty()) ? patron.getFirstName() : updateUserRequest.getFirstName());
            patron.setLastName((updateUserRequest.getLastName() == null || updateUserRequest.getLastName().isEmpty()) ? patron.getLastName() : updateUserRequest.getLastName());
            patron.setEmail((updateUserRequest.getEmail() == null || updateUserRequest.getEmail().isEmpty()) ? patron.getEmail() : updateUserRequest.getEmail());
            patron.setPhone((updateUserRequest.getPhone() == null || updateUserRequest.getPhone().isEmpty()) ? patron.getPhone() : updateUserRequest.getPhone());
            patron.setUsername((updateUserRequest.getUsername() == null || updateUserRequest.getUsername().isEmpty()) ? patron.getUsername() : updateUserRequest.getUsername());
            patron = patronRepository.save(patron);
            return new ResponseEntity<>(new GenericResponse<>(200,"Patron updated successfully",patron),HttpStatus.OK);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(Long id) {
        try{
            Optional<Patron> optionalPatron = patronRepository.findById(id);
            if(optionalPatron.isEmpty()){
                return new ResponseEntity<>(new GenericResponse<>(404,"Patron not found",null),HttpStatus.OK);

            }
            patronRepository.deleteById(id);
            return new ResponseEntity<>(new GenericResponse<>(200,"Patron deleted successfully",null),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}
