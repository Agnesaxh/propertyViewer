package com.propertyviewerproject.propertyviewer.error;

import com.propertyviewerproject.propertyviewer.exception.NotFoundException;
import com.propertyviewerproject.propertyviewer.exception.ResourceExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("Resource not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ResourceExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExists(ResourceExistsException e) {
        return new ResponseEntity<>(new ErrorResponse("Resource already exist"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception on handler", e);
        return new ResponseEntity<>(new ErrorResponse("Something went wrong, please try again"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
