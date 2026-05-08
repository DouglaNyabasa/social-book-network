package com.doug.socialbooknetwork.service;

import com.doug.socialbooknetwork.models.User;
import com.doug.socialbooknetwork.payload.request.RegistrationRequest;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  {


    void register(@Valid RegistrationRequest request) throws MessagingException;

    void sendValidationEmail(User user) throws MessagingException;

    String generateAndSaveActivationToken(User user);

    String generateActivationCode(int length);
}
