package com.edu.sena.Petcare.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
// clase para manejar errores
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
        public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            exception.getMessage(),
            HttpStatus.NOT_ACCEPTABLE.value(), 
            "Creacion invalida de Customer o Employee");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            exception.getMessage(),
            HttpStatus.NOT_ACCEPTABLE.value(), 
            "Violacion de Constraint Unique compuesta");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            exception.getMessage(),
            HttpStatus.NOT_FOUND.value(), 
            "Recurso no encontrado");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            exception.getMessage(),
            HttpStatus.BAD_REQUEST.value(), 
            "Solicitu incorrecta");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        String errorMessage = "Error de validacion en los campos: " + String.join(" , ", errors.keySet());
        ErrorResponse errorResponse = new ErrorResponse(
            errorMessage,
            HttpStatus.BAD_REQUEST.value(), 
            "Validacion fallida");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);       
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            exception.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Error de ejecución procesado por el servidor");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(org.springframework.dao.DataIntegrityViolationException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            "Conflict con datos existentes: El NIT o el Usuario ya están registrados.",
            HttpStatus.CONFLICT.value(), 
            "Violación de restricción de integridad de base de datos");
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            "Ocurrió un error inesperado en el sistema.",
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
