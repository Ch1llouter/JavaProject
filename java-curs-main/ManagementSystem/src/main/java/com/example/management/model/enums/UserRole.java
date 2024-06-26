package com.example.management.model.enums;

public enum UserRole {
    ANALYST(0),
    USER(1),
    SIGNATOR(2), // підписант
    ADMINISTRATOR(3);
    private final int value;
    UserRole(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
