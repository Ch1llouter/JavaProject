package com.example.management.controller;

import com.example.management.model.dto.UserDto;
import com.example.management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Управління користувачами")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(description = "Реєстрація нового користувача",
            summary = "Створення нового користувача"
    )
    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.create(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @Operation(description = "Отримати інформацію про користувача",
            summary = "Отримання інформації про конкретного користувача"
    )
    @GetMapping("/{id}")
    public ResponseEntity<? extends UserDto> getById(@PathVariable Long id) {
        UserDto userDTO = userService.getById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Operation(description = "Оновити інформацію про користувача",
            summary = "Оновлення інформації про користувача"
    )
    @PutMapping("/{id}")
    public ResponseEntity<? extends UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.update(id, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(description = "Назначити роль для користувача",
            summary = "Назначити визначену роль для визначеного користувача"
    )
    @PostMapping("/{id}/assign/{roleId}")
    public ResponseEntity<? extends UserDto> assignRole(@PathVariable Long id, @PathVariable Long roleId) {
        UserDto updatedUser = userService.assignRole(id, roleId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(description = "Видалити користувача",
            summary = "Видалити користувача з системи"
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
