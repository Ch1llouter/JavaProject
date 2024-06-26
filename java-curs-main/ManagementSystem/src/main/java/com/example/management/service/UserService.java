package com.example.management.service;

import com.example.management.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    UserDto create(UserDto user);
    UserDto getById(Long id);
    UserDto update(Long id, UserDto userDto);
    UserDto assignRole(Long id, Long roleId);
    void delete(Long id);
}
