package com.project.Library.Management.exception;

import lombok.Data;

@Data
public class CustomErrorResponse {
    private int status;
    private String message;
}
