package com.doug.socialbooknetwork.payload.request;

import com.doug.socialbooknetwork.models.User;

public class UserMapper {

    public static RegistrationRequest toDTO(User savedUser) {
        return RegistrationRequest.builder()
                .firstname(savedUser.getFirstname())
                .lastname(savedUser.getLastname())
                .email(savedUser.getEmail())
                .roles(savedUser.getRoles())
                .build();
    }

    public static User toEntity(RegistrationRequest registrationRequest) {
        return User.builder()
                .firstname(registrationRequest.getFirstname())
                .lastname(registrationRequest.getLastname())
                .email(registrationRequest.getEmail())
                .roles(registrationRequest.getRoles())
                .password(registrationRequest.getPassword())
                .build();

    }
}
