package com.project.Library.Management.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BookRequest {
    @NotNull(message = "author is required")
    @Size(min = 2,message = "author name must be at least 2 characters")
    private String author;
    @NotNull(message = "isbn is required")
    @Size(min = 13, max = 13,message = "isbn must be at least 13 characters")
    private String isbn;
    @NotNull(message = "publicationYear is required")
    @Size(min = 4,message = "publicationYear must be at least 4 characters")
    private String publicationYear;
    @NotNull(message = "title is required")
    @Size(min = 2,message = "title must be at least 2 characters")
    private String title;
}
