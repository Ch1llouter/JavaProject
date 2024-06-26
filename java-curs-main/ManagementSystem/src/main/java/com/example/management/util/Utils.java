package com.example.management.util;

import com.example.management.model.enums.UserRole;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Utils {

    public Optional<UserRole> getRoleById(Long roleId) {
        for (UserRole role : UserRole.values()) {
            if (role.getValue() == roleId) {
                return Optional.of(role);
            }
        }
        return Optional.empty();
    }
}
