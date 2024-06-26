package com.example.management.controller;

import com.example.management.model.dto.UserDto;
import com.example.management.model.enums.UserRole;
import com.example.management.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDto userDto;
    private UserDto expectedUserDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUsername("user-01");
        userDto.setPassword("psw-01");

        expectedUserDto = new UserDto();
        expectedUserDto.setId(1L);
        expectedUserDto.setRole(UserRole.USER);
        expectedUserDto.setUsername("user-01");
        expectedUserDto.setPassword("psw-01");
    }

    @Test
    void register() {
        when(userService.create(any())).thenReturn(expectedUserDto);
        ResponseEntity<UserDto> response = userController.register(userDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedUserDto.getId(), response.getBody().getId());
        assertEquals(expectedUserDto.getRole(), response.getBody().getRole());
    }

    @Test
    void getById() {
        when(userService.getById(anyLong())).thenReturn(expectedUserDto);
        ResponseEntity<? extends UserDto> response = userController.getById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedUserDto.getId(), response.getBody().getId());
        assertEquals(expectedUserDto.getRole(), response.getBody().getRole());
    }

    @Test
    void updateUser() {
        when(userService.update(anyLong(), any(UserDto.class))).thenReturn(expectedUserDto);
        ResponseEntity<? extends UserDto> response = userController.updateUser(1L, userDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedUserDto.getId(), response.getBody().getId());
        assertEquals(expectedUserDto.getRole(), response.getBody().getRole());
    }

    @Test
    void assignRole() {
        when(userService.assignRole(anyLong(), anyLong())).thenReturn(expectedUserDto);
        ResponseEntity<? extends UserDto> response = userController.assignRole(1L, 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedUserDto.getId(), response.getBody().getId());
        assertEquals(expectedUserDto.getRole(), response.getBody().getRole());
    }

    @Test
    void delete() {
        doNothing().when(userService).delete(anyLong());
        ResponseEntity<Void> response = userController.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}