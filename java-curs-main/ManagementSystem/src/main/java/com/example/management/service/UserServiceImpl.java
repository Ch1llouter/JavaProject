package com.example.management.service;

import com.example.management.exception.UserAlreadyExistsException;
import com.example.management.exception.UserNotFoundException;
import com.example.management.exception.UserRoleNotFoundException;
import com.example.management.model.domain.User;
import com.example.management.model.dto.UserDto;
import com.example.management.model.enums.Action;
import com.example.management.model.enums.UserRole;
import com.example.management.model.mapper.UserMapper;
import com.example.management.repository.UserRepository;
import com.example.management.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final ReportService reportService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Utils utils;

    @Autowired
    public UserServiceImpl(ReportService reportService, UserRepository userRepository,
                           UserMapper userMapper, Utils utils) {
        this.reportService = reportService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.utils = utils;
    }

    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(userDto.getUsername());
        }
        User user = userMapper.toEntity(userDto);
        user.setRole(UserRole.USER);
        User saved = userRepository.save(user);
        reportService.saveHistory(saved.getId(), Action.USER_CREATE, null, null);
        return userMapper.toDto(saved);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserDto update(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        User saved = userRepository.save(user);
        reportService.saveHistory(saved.getId(), Action.USER_UPDATE, null, null);
        return userMapper.toDto(saved);
    }

    @Transactional
    @Override
    public UserDto assignRole(Long id, Long roleId) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        existingUser.setRole(utils.getRoleById(roleId).orElseThrow(() -> new UserRoleNotFoundException(roleId)));
        User saved = userRepository.save(existingUser);
        reportService.saveHistory(saved.getId(), Action.ASSIGN_USER_ROLE, null, null);
        return userMapper.toDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
        reportService.saveHistory(id, Action.USER_DELETE, null, null);
    }
}
