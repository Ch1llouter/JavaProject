package com.example.management.exception;

public class UserRoleNotFoundException extends RuntimeException {
    public UserRoleNotFoundException(Long roleId) {
        super("Could not find users Role. RoleId=" + roleId);
    }
}
