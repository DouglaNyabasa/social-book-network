package com.doug.socialbooknetwork.controller;

import com.doug.socialbooknetwork.payload.request.AuthRequest;
import com.doug.socialbooknetwork.payload.request.RegistrationRequest;
import com.doug.socialbooknetwork.payload.response.ApiResponse;
import com.doug.socialbooknetwork.payload.response.AuthResponse;
import com.doug.socialbooknetwork.service.UserService;
import com.doug.socialbooknetwork.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final UserServiceImpl userServiceImpl;
    private final UserService userService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
            userServiceImpl.register(request);
            return ResponseEntity.accepted().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody @Valid AuthRequest request){
        return ResponseEntity.ok(userService.authenticate(request));
    }





}
