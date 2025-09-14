package com.construction.userservice.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String profilePictureUrl;
    private Set<String> roles;
}

