package com.project.Library.Management.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@Component
public class BorrowingRequest {
    @NotNull(message = "returnDate is required")
    private Date returnDate;
}
