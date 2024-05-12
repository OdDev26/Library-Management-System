package com.project.Library.Management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleCustomException(CustomException customException){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        customErrorResponse.setMessage(customException.getMessage());
        return new ResponseEntity<>(customErrorResponse,HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleMissingRequestParamException(MissingServletRequestParameterException customException){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        customErrorResponse.setMessage(customException.getMessage());
        return new ResponseEntity<>(customErrorResponse,HttpStatus.BAD_REQUEST);
    }

}
