package com.example.management.exception;

public class ApprovalNotFoundException extends RuntimeException {

    public ApprovalNotFoundException(Long id) {
        super("Could not find approval. ID=" + id);
    }
}
