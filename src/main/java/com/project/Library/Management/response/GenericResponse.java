package com.project.Library.Management.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenericResponse<T> {
    private T data;
    private int status;
    private String message;

    public GenericResponse(int status, String message,T data) {
        this.status = status;
        this.message = message;
        this.data = data;

    }
}
