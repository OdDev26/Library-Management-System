package com.project.Library.Management.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CreateUserRequest {
    @NotNull(message = "firstName is required")
    @Size(min = 2,message = "first name must be at least 2 characters")
    private String firstName;
    @NotNull(message = "lastName is required")
    @Size(min = 2,message = "last name must be at least 2 characters")
    private String lastName;
    @NotNull(message = "email is required")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Email is not valid")
    private String email;
    @NotNull(message = "phone is required")
    @Size(min = 7,max = 15,message = "phone number must be between 7 and 15 characters")
    private String phone;
    @NotNull(message = "password is required")
    @Size(min = 7,message = "password must be at least 7 characters")
    private String password;
    @NotNull(message = "username is required")
    @Size(min = 5,message = "username must be at least 5 characters")
    private String username;
}
