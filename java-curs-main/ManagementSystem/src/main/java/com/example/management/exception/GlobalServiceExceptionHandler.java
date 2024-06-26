package com.example.management.exception;

import com.example.management.exception.model.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalServiceExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ServiceError> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(new ServiceError(HttpStatus.CONFLICT.value(), ex.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ServiceError> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ServiceError(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserRoleNotFoundException.class)
    public ResponseEntity<ServiceError> handleUserRoleNotFoundException(UserRoleNotFoundException ex) {
        return new ResponseEntity<>(new ServiceError(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<ServiceError> handleDocumentNotFoundException(DocumentNotFoundException ex) {
        return new ResponseEntity<>(new ServiceError(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApprovalNotFoundException.class)
    public ResponseEntity<ServiceError> handleApprovalNotFoundException(ApprovalNotFoundException ex) {
        return new ResponseEntity<>(new ServiceError(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServiceError> handleException(Exception ex) {
        return new ResponseEntity<>(new ServiceError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
