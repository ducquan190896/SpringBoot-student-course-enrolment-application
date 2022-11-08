package com.quan.gradepractice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.quan.gradepractice.Exception.ErrorResponse;
import com.quan.gradepractice.Exception.EntityNotFoundException;

@ControllerAdvice
public class ApiHandlingException {
    
    @ExceptionHandler(value = EntityNotFoundException.class) 
    public ResponseEntity<Object> handleApiException(EntityNotFoundException notFoundException) {
        ErrorResponse err = new ErrorResponse(notFoundException.getMessage(), LocalDateTime.now(), notFoundException);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class) 
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse err = new ErrorResponse(ex.getMessage(), LocalDateTime.now(), ex);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
