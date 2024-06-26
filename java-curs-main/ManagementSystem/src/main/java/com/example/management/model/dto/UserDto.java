package com.example.management.model.dto;

import com.example.management.model.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private UserRole role;
}
