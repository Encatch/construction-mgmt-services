package com.construction.userservice.service;

import com.construction.userservice.dto.UserDto;
import com.construction.userservice.entity.Role;
import com.construction.userservice.entity.User;
import com.construction.userservice.repository.RoleRepository;
import com.construction.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setProfilePictureUrl(userDto.getProfilePictureUrl());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setIsActive(true);

        Set<Role> roles = new HashSet<>();
        for (String roleName : userDto.getRoles()) {
            roleRepository.findByRoleName(roleName).ifPresent(roles::add);
        }
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setProfilePictureUrl(userDto.getProfilePictureUrl());

        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    public void changeUserRoles(Long userId, Set<String> roleNames) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            roleRepository.findByRoleName(roleName).ifPresent(roles::add);
        }

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void resetPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String tempPassword = UUID.randomUUID().toString().substring(0, 8);
        user.setPassword(passwordEncoder.encode(tempPassword));
        userRepository.save(user);

        // TODO: Send tempPassword via email (we can integrate MailSender later) or
        // lets call other microservice to send email
        System.out.println("Temporary password for " + email + ": " + tempPassword);
    }
}
