package com.hostbooks.crud.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    private LocalDateTime ldt;
    private String message;
    private String details;
}
