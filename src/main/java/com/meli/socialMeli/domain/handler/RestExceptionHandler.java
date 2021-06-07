package com.meli.socialMeli.domain.handler;

import com.meli.socialMeli.domain.error.ResourceNotFoundDetails;
import com.meli.socialMeli.domain.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception){
        var error = ResourceNotFoundDetails
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Resource not found")
                .detail(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
        return new ResponseEntity <>(error, HttpStatus.BAD_REQUEST);
    }
}
