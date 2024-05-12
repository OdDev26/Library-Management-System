package com.project.Library.Management.controller;

import com.project.Library.Management.request.CreateUserRequest;
import com.project.Library.Management.request.UpdateUserRequest;
import com.project.Library.Management.validation.FieldErrorHandler;
import com.project.Library.Management.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PatronController {
    private final PatronService patronService;
    private final FieldErrorHandler fieldErrorHandler;
    @Autowired
    public PatronController(PatronService patronService, FieldErrorHandler fieldErrorHandler) {
        this.patronService = patronService;
        this.fieldErrorHandler = fieldErrorHandler;
    }
    @GetMapping("/patrons")
    public ResponseEntity<?> getPatrons(){
        return patronService.findAll();
    }
    @GetMapping("/patrons/{id}")
    public ResponseEntity<?> getPatron(@PathVariable Long id){
        return patronService.findById(id);
    }

    @PostMapping("/patrons")
    public ResponseEntity<?> addPatron(@Valid @RequestBody CreateUserRequest createUserRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(fieldErrorHandler.errorMessageMap(bindingResult),HttpStatus.BAD_REQUEST);
        }
        return patronService.create(createUserRequest);

    }
    @PutMapping("/patrons/{id}")
    public ResponseEntity<?> updatePatron(@RequestBody UpdateUserRequest updateUserRequest,@PathVariable Long id){
        return patronService.update(updateUserRequest,id);

    }
    @DeleteMapping("/patrons/{id}")
    public ResponseEntity<?> deletePatron(@PathVariable Long id){
        return patronService.deleteById(id);
    }
}
