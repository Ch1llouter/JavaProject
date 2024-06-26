package com.example.management.model.mapper;

import com.example.management.model.domain.User;
import com.example.management.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        if (user != null) {
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setRole(user.getRole());
        }
        return dto;
    }

    public User toEntity(UserDto dto) {
        User user = new User();
        if (dto != null) {
            user.setUsername(dto.getUsername());
            user.setPassword(dto.getPassword());
        }
        return user;
    }
}
