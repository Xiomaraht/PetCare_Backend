package com.edu.sena.Petcare.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private Integer statusCode;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String errorDetails;

    public ErrorResponse(String message, Integer statusCode, String errorDetails){
        this.message = message;
        this.statusCode = statusCode;
        this.errorDetails = errorDetails;
    }
}
