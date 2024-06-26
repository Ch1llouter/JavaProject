package com.example.management.exception;

public class DocumentNotFoundException extends RuntimeException {

    public DocumentNotFoundException(Long id) {
        super("Could not find Document. ID=" + id);
    }
}
