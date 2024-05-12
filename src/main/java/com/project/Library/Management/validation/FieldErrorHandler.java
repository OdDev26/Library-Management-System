package com.project.Library.Management.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class FieldErrorHandler {
    public Map<String,String> errorMessageMap(BindingResult bindingResult){
        Map<String,String> errorMap = new HashMap<>();
        for(FieldError fieldError : bindingResult.getFieldErrors()){
            errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        System.out.println("Error map: "+errorMap);
        return errorMap;
    }
}
