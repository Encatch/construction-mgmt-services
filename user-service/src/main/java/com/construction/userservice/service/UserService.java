package com.construction.userservice.service;

import com.construction.userservice.dto.UserDto;
import com.construction.userservice.entity.User;

import java.util.Set;

public interface UserService {
    User createUser(UserDto userDto);
    void deleteUser(Long id);
    User updateUser(Long id, UserDto userDto);
    void changeUserRoles(Long userId, Set<String> roleNames);
    void resetPassword(String email);
}

