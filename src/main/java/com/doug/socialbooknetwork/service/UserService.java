package com.doug.socialbooknetwork.service;

import com.doug.socialbooknetwork.models.User;
import com.doug.socialbooknetwork.payload.request.AuthRequest;
import com.doug.socialbooknetwork.payload.request.RegistrationRequest;
import com.doug.socialbooknetwork.payload.response.AuthResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

public interface UserService  {


    void register(@Valid RegistrationRequest request) throws MessagingException;

    void sendValidationEmail(User user) throws MessagingException;

    String generateAndSaveActivationToken(User user);

    String generateActivationCode(int length);

    AuthResponse login(@Valid AuthRequest request) throws Exception;
}
