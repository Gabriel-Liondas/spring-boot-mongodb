package com.gabrielliondas.workshopmongo.resources.exception;

import com.gabrielliondas.workshopmongo.exception.NonValidObjectException;
import com.gabrielliondas.workshopmongo.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "OBJECT NOT FOUND", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NonValidObjectException.class)
    public ResponseEntity<StandardError> nonValidObject(NonValidObjectException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "NON VALID OBJECT MISSING REQUIRED PARAMETERS", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
